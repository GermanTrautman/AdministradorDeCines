package com.cine.utilidades;

public enum FormaDePago {

	EFECTIVO("Efectivo"),
    TARJETA("Tarjeta");
	
	private String formaDePago;
	
	FormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	public String getLabel() {
		return formaDePago;
	}
}
