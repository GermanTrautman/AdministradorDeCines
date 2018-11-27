package com.cine.modelo;

import java.util.Date;

import com.cine.dao.TarjetaPersistente;
import com.cine.utilidades.Banco;

public class Tarjeta {

	private Integer id;
	private Banco banco;
	private Long numero;
	private Date mesYAnioDeVencimiento;
	
	public Tarjeta(Banco banco, Long numero, Date mesYAnioDeVencimiento) {
		
		super();
		this.banco = banco;
		this.numero = numero;
		this.mesYAnioDeVencimiento = mesYAnioDeVencimiento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getMesYAnioDeVencimiento() {
		return mesYAnioDeVencimiento;
	}

	public void setMesYAnioDeVencimiento(Date mesYAnioDeVencimiento) {
		this.mesYAnioDeVencimiento = mesYAnioDeVencimiento;
	}
	
    public Tarjeta buscar(Long numero) {
        return (Tarjeta) TarjetaPersistente.getInstance().buscar(numero);
     }

	public void insertar() {
		 TarjetaPersistente.getInstance().insertar(this);
	}
}
