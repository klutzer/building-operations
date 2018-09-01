package com.buildingapp.config.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import org.mentabean.BeanException;
import org.mentabean.BeanSession;
import org.mentabean.util.SQLUtils;
import org.mentacontainer.Factory;
import org.mentacontainer.Interceptor;

import com.zaxxer.hikari.HikariDataSource;

public abstract class ConnectionManager implements Factory<Connection>, Interceptor<Connection> {

	private final HikariDataSource pool;

	public ConnectionManager() {
		pool = createPool();
	}

	/**
	 * Here you can configure your database settings...<br>
	 * Take a look at HikariCP (https://github.com/brettwooldridge/HikariCP)
	 */
	public abstract HikariDataSource createPool();

	public abstract Class<? extends BeanSession> getSessionClass();

	public void preRun(BeanSession session) {

	}

	public Connection getConnection() {
		try {
			return pool.getConnection();
		} catch (Exception e) {
			throw new BeanException("Error getting database connection from the pool!", e);
		}
	}

	public void commitAndClose(Connection conn) {
		try {
			if (!conn.isClosed() && !conn.getAutoCommit()) {
				SQLUtils.commitTransaction(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			throw new BeanException(e);
		} finally {
			SQLUtils.close(conn);
		}
	}

	public void rollback(Connection conn) {
		try {
			if (!conn.isClosed() && !conn.getAutoCommit()) {
				SQLUtils.rollbackTransaction(conn);
			}
		} catch (Exception e) {
			throw new BeanException(e);
		}
	}

	@Override
	public Connection getInstance() {
		return getConnection();
	}

	@Override
	public Class<? extends Connection> getType() {
		return Connection.class;
	}

	@Override
	public void onCreated(Connection connection) {
		// nothing to do here...
	}

	@Override
	public void onCleared(Connection connection) {
		commitAndClose(connection);
	}

	public void shutdown() {
		pool.close();
		deregisterDrivers();
	}

	private static void deregisterDrivers() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
			} catch (Exception e) {
				throw new BeanException(e);
			}
		}
	}
}