package com.buildingapp;

import static org.mentacontainer.impl.SingletonFactory.singleton;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
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

	private static final Logger LOGGER = Logger.getLogger("RestApplication");
	private static RestApplication instance;
	private final Container container = new MentaContainer();
	private final ConnectionManager connectionManager = new H2MemoryConnectionManager();

	public RestApplication() {
		instance = this;
		LOGGER.info("Starting RestApplication...");
		register(JacksonFeature.class);
		packages(true, getClass().getPackage().getName());
		registerClasses(ApiListingResource.class, SwaggerSerializers.class);
		register(new LoggingFeature(Logger.getLogger(getClass().getName()), Level.INFO,
				LoggingFeature.Verbosity.PAYLOAD_TEXT, 8192));
		configureIoC();
		configureSwagger();
	}

	public static RestApplication getInstance() {
		return instance;
	}

	public static <E> E get(Object key) {
		return getInstance().container.get(key);
	}

	public static void clearThreadScope() {
		getInstance().container.clear(Scope.THREAD);
	}

	public static void releaseAndShutdown() {
		LOGGER.info("Stopping RestApplication...");
		clearThreadScope();
		getInstance().container.clear(Scope.SINGLETON);
		getInstance().connectionManager.shutdown();
		instance = null;
		LOGGER.info("RestApplication stopped");
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
		container.ioc(ConnectionManager.class, singleton(connectionManager));
		container.ioc(BeanManager.class, singleton(new DatabaseMappings().configure()));
		container.ioc(Connection.class, connectionManager, Scope.THREAD);
		container.ioc(BeanSession.class, connectionManager.getSessionClass(), Scope.THREAD)
				.addConstructorDependency(BeanManager.class).addConstructorDependency(Connection.class);
		container.autowire(BeanSession.class, "session");
		initializeData(connectionManager);
	}

	private void initializeData(ConnectionManager connectionManager) {
		connectionManager.preRun(get(BeanSession.class));
		clearThreadScope();
	}
}
