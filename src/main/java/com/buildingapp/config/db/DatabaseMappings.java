package com.buildingapp.config.db;

import org.mentabean.BeanConfig;
import org.mentabean.BeanManager;
import org.mentabean.DBTypes;
import org.mentabean.util.PropertiesProxy;

import com.buildingapp.data.Vehicle;

public final class DatabaseMappings {

	private BeanManager manager = new BeanManager();

	public BeanManager configure() {
		configureVeiculo();
		return manager;
	}

	private void configureVeiculo() {
		Vehicle proxy = PropertiesProxy.create(Vehicle.class);
		manager.addBeanConfig(new BeanConfig(Vehicle.class, "veiculos")
				.pk(proxy.getId(), DBTypes.AUTOINCREMENT)
				.field(proxy.getName(), DBTypes.STRING)
				.field(proxy.getMultiplier(), DBTypes.BIGDECIMAL)
				);
	}

}
