package com.buildingapp;

import static org.mentacontainer.impl.SingletonFactory.singleton;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.mentabean.BeanManager;
import org.mentabean.BeanSession;
import org.mentacontainer.Container;
import org.mentacontainer.Scope;
import org.mentacontainer.impl.MentaContainer;

import com.buildingapp.config.db.ConnectionManager;
import com.buildingapp.config.db.DatabaseMappings;
import com.buildingapp.config.db.H2MemoryConnectionManager;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("api")
public class RestApplication extends ResourceConfig {

	private static final Container CONTAINER = new MentaContainer();

	public RestApplication() {
		System.out.println("Starting RestApplication...");
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		packages(true, getClass().getPackage().getName());
		registerClasses(ApiListingResource.class, SwaggerSerializers.class);
		register(new LoggingFeature(Logger.getLogger(getClass().getName()), Level.INFO,
				LoggingFeature.Verbosity.PAYLOAD_TEXT, 8192));
		configureIoC();
		configureSwagger();
		initializeData();
	}

	public static <E> E get(Object key) {
		return CONTAINER.get(key);
	}

	public static void clearThreadScope() {
		CONTAINER.clear(Scope.THREAD);
	}

	private void configureSwagger() {
		BeanConfig conf = new BeanConfig();
		conf.setTitle("API REST Building Operations");
		conf.setDescription("Documentação interativa de exemplo da API");
		conf.setVersion("1");
		conf.setBasePath("/api");
		conf.setSchemes(new String[] { "http" });
		conf.setResourcePackage("com.buildingapp");
		conf.setScan(true);
	}

	private void configureIoC() {
		ConnectionManager connectionManager = new H2MemoryConnectionManager();
		CONTAINER.ioc(ConnectionManager.class, singleton(connectionManager));
		CONTAINER.ioc(BeanManager.class, singleton(new DatabaseMappings().configure()));
		CONTAINER.ioc(Connection.class, connectionManager, Scope.THREAD);
		CONTAINER.ioc(BeanSession.class, connectionManager.getSessionClass(), Scope.THREAD)
				.addConstructorDependency(BeanManager.class).addConstructorDependency(Connection.class);
		CONTAINER.autowire(BeanSession.class, "session");
	}

	private void initializeData() {
		BeanSession session = get(BeanSession.class);
		session.createTables();
		clearThreadScope();
	}
}
