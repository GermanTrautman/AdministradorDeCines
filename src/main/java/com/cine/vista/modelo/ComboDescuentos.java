package com.cine.vista.modelo;

import com.cine.modelo.Descuento;

public class ComboDescuentos {

	private Descuento descuentoProvider;
	private Integer id;

	public ComboDescuentos(Descuento descuentoProvider, Integer id) {

		this.descuentoProvider = descuentoProvider;
		this.id = id;
	}

	public Descuento getDescuentoProvider() {
		return descuentoProvider;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return descuentoProvider.getNombre();
	}
}
