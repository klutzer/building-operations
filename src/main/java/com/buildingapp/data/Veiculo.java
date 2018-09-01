package com.buildingapp.data;

import java.math.BigDecimal;

public class Veiculo extends BasicEntity {

	private String descricao;
	private BigDecimal fatorMultiplicador;

	public Veiculo() {
		super();
	}

	public Veiculo(String descricao, BigDecimal fatorMultiplicador) {
		super();
		this.descricao = descricao;
		this.fatorMultiplicador = fatorMultiplicador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getFatorMultiplicador() {
		return fatorMultiplicador;
	}

	public void setFatorMultiplicador(BigDecimal fatorMultiplicador) {
		this.fatorMultiplicador = fatorMultiplicador;
	}
}
