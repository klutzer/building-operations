package com.buildingapp.resource;

import java.math.BigDecimal;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.buildingapp.data.TransportCost;
import com.buildingapp.data.Vehicle;
import com.buildingapp.resource.entity.ResultResponse;

public class TransportCostResourceTest extends RestTest {

	@Test
	public void testCalculateSuccessfully() {
		TransportCost cost = new TransportCost()
				.withCargoCarried(5)
				.withDistancePaved(1)
				.withDistanceNotPaved(0)
				.withVehicle(new Vehicle("Test", BigDecimal.ONE));
		ResultResponse response = target().path("custoTransporte").request().post(Entity.json(cost), ResultResponse.class);
		MatcherAssert.assertThat(response.getResult().doubleValue(), Matchers.equalTo(0.54));
	}

	@Test
	public void testCalculateError() {
		TransportCost cost = new TransportCost()
				.withCargoCarried(5)
				.withDistancePaved(1)
				.withDistanceNotPaved(0)
				.withVehicle(null);
		Response response = target().path("custoTransporte").request().post(Entity.json(cost));
		MatcherAssert.assertThat(response.getStatus(), Matchers.equalTo(400));
		MatcherAssert.assertThat(response.readEntity(String.class), Matchers.containsString("Necessário um veículo para calcular"));
	}
}
