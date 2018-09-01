package com.buildingapp.config;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.buildingapp.RestApplication;

@WebListener
public class ContextListener implements ServletContextListener {

	private static final Logger LOGGER = Logger.getLogger(ContextListener.class.getSimpleName());
	
	@Override
	public void contextInitialized(ServletContextEvent ev) {
		LOGGER.info("Initializing servlet...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent ev) {
		LOGGER.info("Stopping servlet...");
		RestApplication.releaseAndShutdown();
	}

}