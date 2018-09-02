package com.example.resources;

import static java.math.BigDecimal.valueOf;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.buildingapp.data.Vehicle;

public class VehicleResourceTest extends RestTest {

	@Test
	public void testListAllVehicles() {
		List<Vehicle> vehicles = target().path("veiculo").request().get(new GenericType<List<Vehicle>>() {});
		MatcherAssert.assertThat(vehicles, Matchers.hasSize(3));
	}

	@Test
	public void testAddVehicle() {
		Vehicle vehicle = new Vehicle("Veículo Teste", valueOf(3.3));
		Vehicle savedVehicle = target().path("veiculo").request().post(Entity.json(vehicle), Vehicle.class);
		MatcherAssert.assertThat(savedVehicle.getId(), Matchers.equalTo(4L));
	}

	@Test
	public void testEditVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(3L);
		vehicle.setMultiplier(valueOf(99));
		Vehicle veiculoSalvo = target().path("veiculo").request().put(Entity.json(vehicle), Vehicle.class);
		MatcherAssert.assertThat(veiculoSalvo.getId(), Matchers.equalTo(3L));
		MatcherAssert.assertThat(veiculoSalvo.getName(), Matchers.equalTo("Carreta"));
		MatcherAssert.assertThat(veiculoSalvo.getMultiplier().doubleValue(), Matchers.equalTo(99d));
	}

}
