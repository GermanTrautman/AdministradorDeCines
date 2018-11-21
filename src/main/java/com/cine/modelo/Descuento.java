package com.cine.modelo;

import com.cine.dao.DescuentoPersistente;

public class Descuento {

    private Integer idDescuento;
    private String nombre;
    private Integer porcentaje;
    private Integer cantidad;
    private DescuentoTipo tipo;

    public Descuento(String nombre, Integer porcentaje, Integer cantidad, DescuentoTipo tipo) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public Descuento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public DescuentoTipo getTipo() {
        return tipo;
    }

    public void setTipo(DescuentoTipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Descuento{" +
                "nombre='" + nombre + '\'' +
                ", porcentaje=" + porcentaje +
                ", cantidad=" + cantidad +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public void insertarDescuento(Descuento descuento) {
        DescuentoPersistente.getInstance().insertar(descuento);

    }

    public void borrarDescuento() {

    }

    public void actualizarDescuento(Descuento descuento) {

    }

    public Descuento buscarDescuento(String  nombre) {
        return (Descuento) DescuentoPersistente.getInstance().buscar(nombre);
    }

    public Integer getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Integer idDescuento) {
        this.idDescuento = idDescuento;
    }
}
