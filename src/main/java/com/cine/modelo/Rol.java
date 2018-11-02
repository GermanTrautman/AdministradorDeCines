package com.cine.modelo;

import com.cine.utilidades.Estado;

public abstract class Rol {

    private String nombre;
    private Estado estado;

    public Rol(String nombre, Estado estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
