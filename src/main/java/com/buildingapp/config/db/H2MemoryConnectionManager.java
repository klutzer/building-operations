package com.buildingapp.config.db;

import org.mentabean.BeanSession;
import org.mentabean.jdbc.H2BeanSession;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class H2MemoryConnectionManager extends ConnectionManager {

	@Override
	public HikariDataSource createPool() {
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
		config.addDataSourceProperty("URL", "jdbc:h2:mem:test" + System.nanoTime() + ";MODE=PostgreSQL");
		config.setConnectionTimeout(5000);
		config.setAutoCommit(false);
		return new HikariDataSource(config);
	}

	@Override
	public Class<? extends BeanSession> getSessionClass() {
		return H2BeanSession.class;
	}

}