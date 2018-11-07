package com.cine.modelo;

import java.util.HashSet;
import java.util.Set;

import com.cine.dao.SalaPersistente;
import com.cine.utilidades.Estado;

public class Sala {

	private String nombre;
	private Set<AsientoFisico> asientos = new HashSet<>(); 
	private Establecimiento establecimiento;
	private Estado estado;

	public Sala(String nombre, Establecimiento establecimiento, Estado estado) {
		super();
		this.nombre = nombre;
		this.establecimiento = establecimiento;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<AsientoFisico> getAsientos() {
		return asientos;
	}

	public void setAsientos(Set<AsientoFisico> asientos) {
		this.asientos = asientos;
	}

	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void insertar() {

		SalaPersistente salaPersistente = new SalaPersistente();
		salaPersistente.insertar(this);
	}

	public void borrar() {

		SalaPersistente salaPersistente = new SalaPersistente();
		salaPersistente.borrar(this);
	}

	public void actualizar(String nombre, Establecimiento establecimiento, Estado estado) {

		Sala sala = new Sala(nombre, establecimiento, estado);

		SalaPersistente salaPersistente = new SalaPersistente();
		salaPersistente.actualizar(sala);
	}
}