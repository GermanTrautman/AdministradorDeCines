package com.cine.vista.modelo;

import com.cine.utilidades.Estado;

public class ComboRol {

	private String nombre;
	private Estado  estado;

	public ComboRol(String nombre, Estado estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
