package com.cine.modelo;

import com.cine.dao.SalaPersistente;
import com.cine.utilidades.Estado;

public class Sala {

    private String nombre;
    private Integer capacidad;
    private Establecimiento establecimiento;
    private Estado estado;

    public Sala(String nombre, Integer capacidad, Establecimiento establecimiento, Estado estado) {
    	
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.establecimiento = establecimiento;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
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

	public void actualizar(String nombre, Integer capacidad, Establecimiento establecimiento, Estado estado) {

		Sala sala = new Sala(nombre, capacidad, establecimiento, estado);
		
		SalaPersistente salaPersistente = new SalaPersistente();
		salaPersistente.actualizar(sala);
	}
}