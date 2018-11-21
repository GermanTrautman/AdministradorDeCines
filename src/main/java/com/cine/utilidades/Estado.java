package com.cine.utilidades;

public enum Estado {
	
	ACTIVO("Activo"),
    INACTIVO("Inactivo");
	
	private String estado;
	
	Estado(String estado) {
		this.estado = estado;
	}
	
	public String getLabel() {
		return estado;
	}
}