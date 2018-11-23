package com.cine.utilidades;

public enum TipoVenta {

	BOLETARIA("Boleteria"),
    WEB("Web");
	
	private String tipoVenta;
	
	TipoVenta(String tipoVenta) {
		this.tipoVenta = tipoVenta;
	}
	
	public String getLabel() {
		return tipoVenta;
	}
}
