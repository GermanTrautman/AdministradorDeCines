package com.cine.utilidades;

public enum EstadoVirtual {
	LIBRE("Libre"), OCUPADO("Ocupado");

	private String estadoVirtual;

	EstadoVirtual(String estado) {
		this.estadoVirtual = estado;
	}

	public String estado() {
		return estadoVirtual;
	}
}
