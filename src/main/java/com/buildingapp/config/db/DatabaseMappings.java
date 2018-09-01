package com.buildingapp.config.db;

import org.mentabean.BeanConfig;
import org.mentabean.BeanManager;
import org.mentabean.DBTypes;
import org.mentabean.util.PropertiesProxy;

import com.buildingapp.data.Veiculo;

public final class DatabaseMappings {

	private BeanManager manager = new BeanManager();

	public BeanManager configure() {
		configureVeiculo();
		return manager;
	}

	private void configureVeiculo() {
		Veiculo proxy = PropertiesProxy.create(Veiculo.class);
		manager.addBeanConfig(new BeanConfig(Veiculo.class, "veiculos")
				.pk(proxy.getId(), DBTypes.AUTOINCREMENT)
				.field(proxy.getDescricao(), DBTypes.STRING)
				.field(proxy.getFatorMultiplicador(), DBTypes.BIGDECIMAL)
				);
	}

}
