package com.buildingapp.data;

import static java.math.BigDecimal.valueOf;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CustoTransporteTest {

	private Map<String, Veiculo> veiculos;

	@Before
	public void definirDadosPadrao() {
		veiculos.put("Caminhão baú", new Veiculo("Caminhão baú", valueOf(1)));
		veiculos.put("Caminhão caçamba", new Veiculo("Caminhão caçamba", valueOf(1.05)));
		veiculos.put("Carreta", new Veiculo("Carreta", valueOf(1.12)));
	}

	@Test
	public void testCenario1() {
		CustoTransporte custo = new CustoTransporte();
	}

}
