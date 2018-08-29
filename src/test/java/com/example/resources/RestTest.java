package com.example.resources;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;

import com.buildingapp.RestApplication;

public abstract class RestTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new RestApplication();
	}
}
