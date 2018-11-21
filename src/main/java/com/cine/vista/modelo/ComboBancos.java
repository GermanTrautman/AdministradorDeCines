package com.cine.vista.modelo;

import com.cine.utilidades.Banco;

public class ComboBancos {
	
	private Banco banco;
	private Integer id;

	public ComboBancos(Banco banco, Integer id) {

		this.banco = banco;
		this.id = id;
	}

	public Banco getBanco() {
		return banco;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return banco.getLabel();
	}
}
