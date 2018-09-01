package com.example.resources;

import static java.math.BigDecimal.valueOf;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.buildingapp.data.Veiculo;

public class VeiculoResourceTest extends RestTest {

	@Test
	public void testListAllVehicles() {
		List<Veiculo> vehicles = target().path("veiculo").request().get(new GenericType<List<Veiculo>>() {});
		MatcherAssert.assertThat(vehicles, Matchers.hasSize(3));
	}

	@Test
	public void testAddVehicle() {
		Veiculo veiculo = new Veiculo("Veículo Teste", valueOf(3.3));
		Veiculo veiculoSalvo = target().path("veiculo").request().post(Entity.json(veiculo), Veiculo.class);
		MatcherAssert.assertThat(veiculoSalvo.getId(), Matchers.equalTo(4L));
	}

}
