package com.cine.modelo;

import java.util.List;

import com.cine.utilidades.FormaDePago;

public class Venta {
	
	private Usuario usuario;
	private Funcion funcion;
	private List<AsientoVirtual> asientosAdquiridos;
	private Double monto;
	private FormaDePago formaDePago;
	private Tarjeta tarjeta;

	public Venta(Usuario usuario, Funcion funcion, List<AsientoVirtual> asientosAdquiridos, Double monto, FormaDePago formaDePago) {
		
		super();
		this.usuario = usuario;
		this.funcion = funcion;
		this.asientosAdquiridos = asientosAdquiridos;
		this.monto = monto;
		this.formaDePago = formaDePago;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public List<AsientoVirtual> getAsientosAdquiridos() {
		return asientosAdquiridos;
	}

	public void setAsientosAdquiridos(List<AsientoVirtual> asientosAdquiridos) {
		this.asientosAdquiridos = asientosAdquiridos;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
}
