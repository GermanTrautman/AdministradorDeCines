package com.cine.vista.modelo;

import com.cine.utilidades.FormaDePago;

public class ComboFormaDePago {

	private FormaDePago formaDePago;
	private Integer id;

	public ComboFormaDePago(FormaDePago formaDePago, Integer id) {

		this.formaDePago = formaDePago;
		this.id = id;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return formaDePago.getLabel();
	}
}
