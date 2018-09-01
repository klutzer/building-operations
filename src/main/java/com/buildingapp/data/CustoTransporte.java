package com.buildingapp.data;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;

public class CustoTransporte {

	private BigDecimal distanciaPavimentada;
	private BigDecimal distanciaNaoPavimentada;
	private Veiculo veiculo;
	private Integer cargaTransportada;

	public CustoTransporte() {

	}

	public CustoTransporte(double distanciaPavimentada, double distanciaNaoPavimentada, Veiculo veiculo,
			int cargaTransportada) {
		this.distanciaPavimentada = valueOf(distanciaPavimentada);
		this.distanciaNaoPavimentada = valueOf(distanciaNaoPavimentada);
		this.veiculo = veiculo;
		this.cargaTransportada = cargaTransportada;
	}

	public BigDecimal getDistanciaPavimentada() {
		return distanciaPavimentada;
	}

	public void setDistanciaPavimentada(BigDecimal distanciaPavimentada) {
		this.distanciaPavimentada = distanciaPavimentada == null ? ZERO : distanciaPavimentada;
	}

	public BigDecimal getDistanciaNaoPavimentada() {
		return distanciaNaoPavimentada;
	}

	public void setDistanciaNaoPavimentada(BigDecimal distanciaNaoPavimentada) {
		this.distanciaNaoPavimentada = distanciaNaoPavimentada == null ? ZERO : distanciaNaoPavimentada;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Integer getCargaTransportada() {
		return cargaTransportada;
	}

	public void setCargaTransportada(Integer cargaTransportada) {
		this.cargaTransportada = cargaTransportada;
	}

	public BigDecimal calcular() {
		validarDados();
		return BigDecimal.valueOf(44);
	}

	private void validarDados() {
		if (cargaTransportada <= 0) {
			throw new IllegalArgumentException("Carga transporada é inválida!");
		}
		if (veiculo == null) {
			throw new IllegalArgumentException("Necessário um veículo para calcular!");
		}
		if (distanciaNaoPavimentada.signum() <= 0 && distanciaPavimentada.signum() <= 0) {
			throw new IllegalArgumentException("Necessário ao menos uma das distâncias");
		}
	}

}