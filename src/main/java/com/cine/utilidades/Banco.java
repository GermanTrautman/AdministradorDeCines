package com.cine.utilidades;

public enum Banco {

	SANTANDER_RIO("Santander Rio"),
    FRANCES("BBVA Frances"),
    BANCO_GALICIA("Banco Galicia"),
    ICBC("ICBC"),
	HSBC("HSBC"),
    BANCO_PATAGONIA("Banco Patagonia"),
    MACRO("Macro"),
	ITAU("Itau"),
	BANCO_NACION("Banco Nacion"),
	BANCO_PROVINCIA("Banco Provincia"),
	BANCO_CIUDAD("Banco Ciudad");
	
	private String banco;
	
	Banco(String banco) {
		this.banco = banco;
	}
	
	public String getLabel() {
		return banco;
	}
}
