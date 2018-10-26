package com.cine.vista.modelo;

public class ComboEstablecimiento {

	private String nombre;
	private Integer id;

	public ComboEstablecimiento(Integer id, String nombre) {
		this.nombre = nombre;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
