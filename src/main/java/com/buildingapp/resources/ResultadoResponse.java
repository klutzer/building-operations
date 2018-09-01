package com.buildingapp.resources;

import java.math.BigDecimal;

public class ResultadoResponse {

	private BigDecimal resultado;

	public ResultadoResponse() {

	}

	public ResultadoResponse(BigDecimal resultado) {
		this.resultado = resultado;
	}

	public BigDecimal getResultado() {
		return resultado;
	}

	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}
}
