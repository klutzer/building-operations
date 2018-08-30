package com.buildingapp.repository;

import java.util.List;

import org.mentabean.BeanSession;
import org.mentabean.jdbc.AnsiSQLBeanSession;

public class GenericRepository<T> {

	protected BeanSession session;

	public BeanSession getSession() {
		return session;
	}

	public void setSession(BeanSession session) {
		this.session = session;
	}

	public T add(T bean) {

		session.insert(bean);
		return bean;
	}

	public T save(T bean) {

		session.save(bean);
		return bean;
	}

	public T update(T bean) {

		if (session.update(bean) > 0) {
			return bean;
		}

		return null;
	}

	public boolean delete(T bean) {

		return session.delete(bean);
	}

	public T getById(T bean) {

		if (!session.load(bean)) {
			return null;
		}

		return bean;
	}

	public T getByExample(T proto) {

		return session.loadUnique(proto);
	}

	public List<T> listByExample(T proto) {

		AnsiSQLBeanSession.debugNativeSql(true);
		return session.loadList(proto);
	}

	public int deleteAll(T proto) {

		return session.deleteAll(proto);
	}

}
