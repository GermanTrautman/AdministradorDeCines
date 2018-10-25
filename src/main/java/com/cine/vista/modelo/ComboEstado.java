package com.cine.vista.modelo;

public class ComboEstado {

	private String estado;
	private Integer id;

	public ComboEstado(String estado, Integer id) {

		this.id = id;
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return estado;
	}
}
