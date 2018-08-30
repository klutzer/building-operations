package com.buildingapp.config.db;

import org.mentabean.BeanConfig;
import org.mentabean.BeanManager;
import org.mentabean.DBTypes;
import org.mentabean.util.PropertiesProxy;

import com.buildingapp.bean.Customer;

public final class DatabaseMappings {

	private BeanManager manager = new BeanManager();

	public BeanManager configure() {
		configureCustomer();
		return manager;
	}

	private void configureCustomer() {
		Customer proxy = PropertiesProxy.create(Customer.class);
		manager.addBeanConfig(new BeanConfig(Customer.class, "customers")
				.pk(proxy.getId(), DBTypes.AUTOINCREMENT)
				.field(proxy.getName(), DBTypes.STRING));
	}

}
