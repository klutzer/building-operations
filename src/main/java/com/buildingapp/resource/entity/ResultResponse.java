package com.buildingapp.resource.entity;

import java.math.BigDecimal;

public class ResultResponse {

	private BigDecimal result;

	public ResultResponse() {

	}

	public ResultResponse(BigDecimal result) {
		this.result = result;
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}
}
