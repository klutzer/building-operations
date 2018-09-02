package com.buildingapp.data;

import java.math.BigDecimal;

public enum RoadType {

	PAVED (0.54),
	NOT_PAVED (0.62);

	BigDecimal costPerKm;

	RoadType(double costPerKm) {
		this.costPerKm = BigDecimal.valueOf(costPerKm);
	}

	public BigDecimal getCostPerKm() {
		return costPerKm;
	}
}
