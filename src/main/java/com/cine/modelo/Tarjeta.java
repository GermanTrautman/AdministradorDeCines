package com.cine.modelo;

import java.util.Date;

import com.cine.utilidades.Banco;

public class Tarjeta {

	private Banco banco;
	private float numero;
	private Date mesYAnioDeVencimiento;
	
	public Tarjeta(Banco banco, float numero, Date mesYAnioDeVencimiento) {
		
		super();
		this.banco = banco;
		this.numero = numero;
		this.mesYAnioDeVencimiento = mesYAnioDeVencimiento;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public float getNumero() {
		return numero;
	}

	public void setNumero(float numero) {
		this.numero = numero;
	}

	public Date getMesYAnioDeVencimiento() {
		return mesYAnioDeVencimiento;
	}

	public void setMesYAnioDeVencimiento(Date mesYAnioDeVencimiento) {
		this.mesYAnioDeVencimiento = mesYAnioDeVencimiento;
	}
}
