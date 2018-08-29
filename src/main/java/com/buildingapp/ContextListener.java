package com.buildingapp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent ev) {
		System.out.println("INICIANDO!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent ev) {
		System.out.println("PARANDO!");
	}

}