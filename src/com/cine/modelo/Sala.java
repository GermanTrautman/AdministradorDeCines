package com.cine.modelo;

import com.cine.utilidades.Estado;

public class Sala {

    private Integer numero;
    private String nombre;
    private Integer capacidad;
    private Establecimiento establecimiento;
    private Estado estado;


    public Sala(Integer numero, String nombre, Integer capacidad, Establecimiento establecimiento, Estado estado) {
        this.numero = numero;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.establecimiento = establecimiento;
        this.estado = estado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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
}
