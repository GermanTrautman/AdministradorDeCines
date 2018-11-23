package com.cine.controlador;

import com.cine.dao.Cache;
import com.cine.modelo.Descuento;
import com.cine.modelo.DescuentoTipo;
import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.List;

public class ControladorVenta implements Cache {

    public List<Descuento> descuentoList;
    private static ControladorVenta instancia;

    public ControladorVenta() {
        this.descuentoList = new ArrayList<>();
    }

    public static ControladorVenta getInstance() {
        if (ControladorVenta.instancia == null) {
            ControladorVenta.instancia = new ControladorVenta();
        }
        return ControladorVenta.instancia;
    }

    public void altaDescuento(String nombre, Integer porcentaje, Integer cantidad, DescuentoTipo tipo){

        if (buscarEnCache(nombre) == null) {

            Descuento descuento = new Descuento(nombre, porcentaje,cantidad, tipo);
            agregarACache(descuento);
            descuento.insertarDescuento(descuento);

        } else {
            throw new RuntimeException("No se permiten usuarios duplicados");
        }
    }

    public void bajaDescuento(){

    }

    public void modificarDescuento(){

    }

    public Descuento buscarDescuentoPorNombre(String nombre){
        Descuento descuento = new Descuento();
        descuento = descuento.buscarDescuento(nombre);
        actualizarCache(descuento);
        return descuento;
    }


    @Override
    public Object buscarEnCache(Object key) {
        return null;
    }

    @Override
    public void agregarACache(Object entidad) {

    }

    @Override
    public void borrarDeCache(Object key) {

    }

    @Override
    public void actualizarCache(Object entidad) {
        Descuento descuento = (Descuento) entidad;
        descuentoList.removeIf(d -> d.getNombre().equals(descuento.getNombre()));
        descuentoList.add(descuento);
    }
}
