package com.buildingapp;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("api")
public class RestApplication extends ResourceConfig {

	public RestApplication() {
		System.out.println("INICIANDO REST APPLICATION....");
		register(JacksonFeature.class);
		register(MultiPartFeature.class);
		packages(true, getClass().getPackage().getName());
		registerClasses(ApiListingResource.class, SwaggerSerializers.class);
		register(new LoggingFeature(Logger.getLogger(getClass().getName()), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_TEXT, 8192));
		configureSwagger();
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
}
