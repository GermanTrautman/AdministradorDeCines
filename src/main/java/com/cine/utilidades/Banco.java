package com.cine.utilidades;

public enum Banco {

    FRANCES("BBVA"),
    ICBC("ICBC"),
	HSBC("HSBC"),
    MACRO("Macro"),
	ITAU("Itau");

	private String banco;
	
	Banco(String banco) {
		this.banco = banco;
	}
	
	public String getLabel() {
		return banco;
	}
}
