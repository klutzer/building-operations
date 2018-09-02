package com.buildingapp.data;

import java.math.BigDecimal;

public class Vehicle {

	private Long id;
	private String name;
	private BigDecimal multiplier;

	public Vehicle() {
		super();
	}

	public Vehicle(String name, BigDecimal multiplier) {
		super();
		this.name = name;
		this.multiplier = multiplier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}

	public BigDecimal applyMultiplier(BigDecimal cost) {
		return cost.multiply(multiplier);
	}
}
