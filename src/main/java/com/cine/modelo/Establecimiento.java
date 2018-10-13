package com.cine.modelo;

public class Establecimiento {

    private Integer cuit;
    private String nombre;
    private String domicilio;
    private Integer cantidadSalas;
    private Integer capacidad;

    public Establecimiento(Integer cuit, String nombre, String domicilio, Integer cantidadSalas, Integer capacidad) {
        this.cuit = cuit;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.cantidadSalas = cantidadSalas;
        this.capacidad = capacidad;
    }


    public Integer getCuit() {
        return cuit;
    }

    public void setCuit(Integer cuit) {
        this.cuit = cuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getCantidadSalas() {
        return cantidadSalas;
    }

    public void setCantidadSalas(Integer cantidadSalas) {
        this.cantidadSalas = cantidadSalas;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}
