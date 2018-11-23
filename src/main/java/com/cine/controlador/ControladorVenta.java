package com.cine.controlador;

import java.util.ArrayList;
import java.util.List;

import com.cine.dao.AsientoVirtualPersistente;
import com.cine.dao.Cache;
import com.cine.dao.VentaAsientoPersistente;
import com.cine.dao.VentaPersistente;
import com.cine.dao.VentaTarjetaPersistente;
import com.cine.modelo.AsientoVirtual;
import com.cine.modelo.Descuento;
import com.cine.modelo.DescuentoTipo;
import com.cine.modelo.Funcion;
import com.cine.modelo.Tarjeta;
import com.cine.modelo.Usuario;
import com.cine.modelo.Venta;
import com.cine.utilidades.FormaDePago;
import com.cine.utilidades.TipoVenta;

public class ControladorVenta implements Cache {

    public List<Descuento> descuentoList;
    
    private List<Venta> ventas = new ArrayList<>();
    
	private static ControladorVenta instancia;

    public ControladorVenta() {
        this.descuentoList = new ArrayList<>();
    }
    
    public List<Venta> getVentas() {
		return ventas;
	}

    public static ControladorVenta getInstance() {
    
    	if (ControladorVenta.instancia == null) {
            ControladorVenta.instancia = new ControladorVenta();
        }
        
    	return ControladorVenta.instancia;
    }

    public Venta buscarVentaPor(String codigo) {

		Venta venta = (Venta) VentaPersistente.getInstance().buscar(codigo);

		if (venta != null) {
			
			agregarACache(venta);
		}

		return venta;
    }
    
    public Venta buscarVentaPor(Integer dni, String codigo) {

		Venta venta = (Venta) VentaPersistente.getInstance().buscar(codigo);

		if (venta != null) {
			
			agregarACache(venta);
		}

		return venta;
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
    
    public void altaVenta(String codigo, Usuario usuario, Funcion funcion, List<AsientoVirtual> asientosAdquiridos, Double monto, FormaDePago formaDePago, TipoVenta tipoVenta, Tarjeta tarjeta) {

		Venta venta = new Venta(usuario, funcion, asientosAdquiridos, monto, formaDePago, tipoVenta);
		venta.setCodigo(codigo);
		
		agregarACache(venta);
		
		venta.insertar();

		venta = buscarVentaPor(venta.getCodigo());
		
		if (formaDePago != null && formaDePago.equals(FormaDePago.TARJETA)) {

			venta.setTarjeta(tarjeta);
			venta.getTarjeta().insertar();
			
			venta.setTarjeta(venta.getTarjeta().buscar(venta.getTarjeta().getNumero()));
			
			VentaTarjetaPersistente.getInstance().insertar(venta);
		}
		
		for (AsientoVirtual asientoVirtual : asientosAdquiridos) {
			
			asientoVirtual = AsientoVirtualPersistente.getInstance().buscarPor(asientoVirtual.getId());
			VentaAsientoPersistente.getInstance().insertar(venta, asientoVirtual);
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
    
    public void agregarSalaACache(Venta venta) {
    	ventas.add(venta);
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
