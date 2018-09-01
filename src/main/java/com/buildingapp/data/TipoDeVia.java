package com.buildingapp.data;

import java.math.BigDecimal;

public enum TipoDeVia {

	PAVIMENTADA (0.54),
	NAO_PAVIMENTADA (0.62);

	BigDecimal custoPorKm;

	TipoDeVia(double custoPorKm) {
		this.custoPorKm = BigDecimal.valueOf(custoPorKm);
	}

	public BigDecimal getCustoPorKm() {
		return custoPorKm;
	}
}
