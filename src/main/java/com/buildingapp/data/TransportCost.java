package com.buildingapp.data;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransportCost {

	private static final int LIMIT_CARGO_FOR_ADDITION = 5;
	private static final BigDecimal ADDITION = valueOf(0.02);

	private BigDecimal distancePaved = ZERO;
	private BigDecimal distanceNotPaved = ZERO;
	private Vehicle vehicle;
	private int cargoCarried;

	public BigDecimal getDistancePaved() {
		return distancePaved;
	}

	public void setDistancePaved(BigDecimal distancePaved) {
		this.distancePaved = distancePaved == null ? ZERO : distancePaved;
	}

	public BigDecimal getDistanceNotPaved() {
		return distanceNotPaved;
	}

	public void setDistanceNotPaved(BigDecimal distanceNotPaved) {
		this.distanceNotPaved = distanceNotPaved == null ? ZERO : distanceNotPaved;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVeiculo(Vehicle veiculo) {
		this.vehicle = veiculo;
	}

	public int getCargoCarried() {
		return cargoCarried;
	}

	public void setCargoCarried(int cargaTransportada) {
		this.cargoCarried = cargaTransportada;
	}

	public TransportCost withDistancePaved(double distancePaved) {
		this.distancePaved = valueOf(distancePaved);
		return this;
	}

	public TransportCost withDistanceNotPaved(double distanceNotPaved) {
		this.distanceNotPaved = valueOf(distanceNotPaved);
		return this;
	}

	public TransportCost withVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		return this;
	}

	public TransportCost withCargoCarried(int cargoCarried) {
		this.cargoCarried = cargoCarried;
		return this;
	}

	/**
	 * Realiza o cálculo do custo do transporte.<br>
	 * O cálculo é feito internamente utilizando BigDecimal para evitar o problema do
	 * cálculo binário realizado pelo Java, que gera situações inusitadas na multiplicação
	 * nativa de tipos double.<br>
	 * Mais detalhes em https://www.devmedia.com.br/java-bigdecimal-trabalhando-com-mais-precisao/30286
	 * @return custo calculado
	 */
	public BigDecimal calculate() {
		validateData();
		BigDecimal custo = distancePaved.multiply(RoadType.PAVED.getCostPerKm())
				.add(distanceNotPaved.multiply(RoadType.NOT_PAVED.getCostPerKm()));
		custo = vehicle.applyMultiplier(custo);
		return custo.add(calculateAddition()).setScale(2, RoundingMode.HALF_DOWN);
	}

	private BigDecimal calculateAddition() {
		if (cargoCarried > LIMIT_CARGO_FOR_ADDITION) {
			return valueOf(cargoCarried - LIMIT_CARGO_FOR_ADDITION)
					.multiply(ADDITION)
					.multiply(distancePaved.add(distanceNotPaved));
		}
		return ZERO;
	}

	private void validateData() {
		if (cargoCarried <= 0) {
			throw new IllegalArgumentException("Carga transportada é inválida");
		}
		if (vehicle == null) {
			throw new IllegalArgumentException("Necessário um veículo para calcular");
		}
		if (distanceNotPaved.signum() <= 0 && distancePaved.signum() <= 0) {
			throw new IllegalArgumentException("Necessário ao menos uma das distâncias");
		}
	}

}