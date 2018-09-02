package com.buildingapp.data;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TransportCostTest {

	private final Map<String, Vehicle> vehicles = new HashMap<>();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void definirDadosPadrao() {
		vehicles.put("Caminhão baú", new Vehicle("Caminhão baú", valueOf(1)));
		vehicles.put("Caminhão caçamba", new Vehicle("Caminhão caçamba", valueOf(1.05)));
		vehicles.put("Carreta", new Vehicle("Carreta", valueOf(1.12)));
	}

	@Test
	public void testCenario1() {
		TransportCost cost = new TransportCost()
				.withDistancePaved(100)
				.withDistanceNotPaved(0)
				.withVehicle(vehicles.get("Caminhão caçamba"))
				.withCargoCarried(8);
		assertThat(cost.calculate().doubleValue(), equalTo(62.70));
	}

	@Test
	public void testCenario2() {
		TransportCost cost = new TransportCost()
				.withDistancePaved(0)
				.withDistanceNotPaved(60)
				.withVehicle(vehicles.get("Caminhão baú"))
				.withCargoCarried(4);
		assertThat(cost.calculate().doubleValue(), equalTo(37.20));
	}

	@Test
	public void testCenario3() {
		TransportCost cost = new TransportCost()
				.withDistancePaved(0)
				.withDistanceNotPaved(180)
				.withVehicle(vehicles.get("Carreta"))
				.withCargoCarried(12);
		assertThat(cost.calculate().doubleValue(), equalTo(150.19));
	}

	@Test
	public void testCenario4() {
		TransportCost cost = new TransportCost()
				.withDistancePaved(80)
				.withDistanceNotPaved(20)
				.withVehicle(vehicles.get("Caminhão baú"))
				.withCargoCarried(6);
		assertThat(cost.calculate().doubleValue(), equalTo(57.60));
	}

	@Test
	public void testCenario5() {
		TransportCost cost = new TransportCost()
				.withDistancePaved(50)
				.withDistanceNotPaved(30)
				.withVehicle(vehicles.get("Caminhão caçamba"))
				.withCargoCarried(5);
		assertThat(cost.calculate().doubleValue(), equalTo(47.88));
	}

	@Test
	public void testInvalidDistance() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Necessário ao menos uma das distâncias");
		new TransportCost()
			.withDistancePaved(0)
			.withDistanceNotPaved(0)
			.withVehicle(vehicles.get("Caminhão caçamba"))
			.withCargoCarried(5)
			.calculate();
	}

	@Test
	public void testInvalidCargo() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Carga transportada é inválida");
		new TransportCost()
			.withDistancePaved(0)
			.withDistanceNotPaved(30)
			.withVehicle(vehicles.get("Caminhão caçamba"))
			.withCargoCarried(0)
			.calculate();
	}

	@Test
	public void testInvalidVehicle() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Necessário um veículo para calcular");
		new TransportCost()
			.withDistancePaved(0)
			.withDistanceNotPaved(0)
			.withVehicle(null)
			.withCargoCarried(5)
			.calculate();
	}

}
