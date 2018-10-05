package com.cine.modelo;

import com.cine.utilidades.Estado;

import java.util.Date;
import java.util.List;

public class Funcion {

    private Date fecha;
    private Sala sala;
    private Pelicula pelicula;
    private List<AsientoVirtual> asientoVirtual;
    private Estado estado;

    public Funcion(Date fecha, Sala sala, Pelicula pelicula, List<AsientoVirtual> asientoVirtual, Estado estado) {
        this.fecha = fecha;
        this.sala = sala;
        this.pelicula = pelicula;
        this.asientoVirtual = asientoVirtual;
        this.estado = estado;
    }

    public List<AsientoVirtual> mostrarAsientos(){
        return null;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public List<AsientoVirtual> getAsientoVirtual() {
        return asientoVirtual;
    }

    public void setAsientoVirtual(List<AsientoVirtual> asientoVirtual) {
        this.asientoVirtual = asientoVirtual;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
