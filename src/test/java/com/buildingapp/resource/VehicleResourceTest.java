package com.buildingapp.resource;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import com.buildingapp.data.Vehicle;

public class VehicleResourceTest extends RestTest {

	@Test
	public void testListAllVehicles() {
		List<Vehicle> vehicles = target().path("veiculo").request().get(new GenericType<List<Vehicle>>() {});
		assertThat(vehicles, hasSize(3));
	}

	@Test
	public void testAddVehicle() {
		Vehicle vehicle = new Vehicle("Veículo Teste", valueOf(3.3));
		Vehicle savedVehicle = target().path("veiculo").request().post(Entity.json(vehicle), Vehicle.class);
		assertThat(savedVehicle.getId(), equalTo(4L));
	}

	@Test
	public void testEditVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(3L);
		vehicle.setMultiplier(valueOf(99));
		Vehicle savedVehicle = target().path("veiculo").request().put(Entity.json(vehicle), Vehicle.class);
		assertThat(savedVehicle.getId(), equalTo(3L));
		assertThat(savedVehicle.getName(), equalTo("Carreta"));
		assertThat(savedVehicle.getMultiplier().doubleValue(), equalTo(99d));
	}

	@Test
	public void testInexistentVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(99L);
		vehicle.setName("Test");
		Response response = target().path("veiculo").request().put(Entity.json(vehicle));
		assertThat(response.getStatusInfo(), equalTo(Status.NO_CONTENT));
		assertThat(response.readEntity(Vehicle.class), nullValue());
	}

}
