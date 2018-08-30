package com.example.resources;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerException;
import org.glassfish.jersey.test.spi.TestContainerFactory;

import com.buildingapp.RestApplication;
import com.buildingapp.config.ContextListener;
import com.buildingapp.config.JsonProvider;

public abstract class RestTest extends JerseyTest {

	@Override
	protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {

		// for parallel tests
		forceSet(TestProperties.CONTAINER_PORT, "0");

		return ServletDeploymentContext.forServlet(new ServletContainer(new RestApplication()))
				.addListener(ContextListener.class).build();
	}

	@Override
	protected void configureClient(ClientConfig config) {
		config.register(JsonProvider.class);
	}
}
