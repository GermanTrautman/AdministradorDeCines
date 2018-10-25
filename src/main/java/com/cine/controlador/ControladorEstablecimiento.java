package com.cine.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cine.dao.Cache;
import com.cine.dao.EstablecimientoPersistente;
import com.cine.modelo.Establecimiento;

public class ControladorEstablecimiento implements Cache {

	private static final ControladorEstablecimiento controladorEstablecimiento = new ControladorEstablecimiento();

	private List<Establecimiento> establecimientos = new ArrayList<>();

	private EstablecimientoPersistente establecimientoPersistente = new EstablecimientoPersistente();

	public static ControladorEstablecimiento getInstance() {
		return controladorEstablecimiento;
	}
	
	public List<Establecimiento> getEstablecimientos() {
		return establecimientos;
	}

	@SuppressWarnings("unchecked")
	public void obtenerEstablecimientos() {
		establecimientos = (List<Establecimiento>) (Object) establecimientoPersistente.listar();
	}

	public Establecimiento buscar(Integer cuit) {
		
		Establecimiento establecimiento = (Establecimiento) buscarEnCache(cuit);
		
		if (establecimiento == null) {
			establecimientoPersistente.buscar(cuit);
		}
	
		return establecimiento;
	}
	
	public void alta(Integer cuit, String nombre, String domicilio, Integer capacidad) {

		Establecimiento establecimiento = new Establecimiento(cuit, nombre, domicilio, capacidad);

		if (buscarEnCache(establecimiento.getCuit()) == null) {

			agregarACache(establecimiento);

			if (establecimientoPersistente.buscar(establecimiento.getCuit()) == null) {
				establecimientoPersistente.insertar(establecimiento);
			}
		}
	}

	public void baja(Integer cuit) {

		borrarDeCache(cuit);

		if (establecimientoPersistente.buscar(cuit) != null) {
			establecimientoPersistente.borrar(cuit);
		}
	}
	
	public void modificacion(Integer cuit, String nombre, String domicilio, Integer capacidad) {
		
		Establecimiento establecimiento = new Establecimiento(cuit, nombre, domicilio, capacidad);
		
		actualizarCache(establecimiento);
		establecimientoPersistente.actualizar(establecimiento);
	}

	@Override
	public Object buscarEnCache(Object cuit) {

		Establecimiento establecimientoEncontrado = null;

		for (Establecimiento establecimiento : establecimientos) {

			if (establecimiento.getCuit().equals(cuit)) {
				establecimientoEncontrado = establecimiento;
			}
		}

		return establecimientoEncontrado;
	}

	@Override
	public void agregarACache(Object establecimiento) {
		establecimientos.add((Establecimiento) establecimiento);
	}

	@Override
	public void borrarDeCache(Object key) {

		if (buscarEnCache(key) != null) {

			for (Iterator<Establecimiento> iterator = establecimientos.listIterator(); iterator.hasNext();) {
				
				Establecimiento establecimiento = iterator.next();
				
				if (establecimiento.getCuit().equals(key)) {
					iterator.remove();
				}
			}
		}
	}

	@Override
	public void actualizarCache(Object entidad) {

		Establecimiento establecimientoModificado = (Establecimiento) entidad;
		
		Establecimiento establecimientoSinModificaciones = (Establecimiento) buscarEnCache(establecimientoModificado.getCuit());
		
		borrarDeCache(establecimientoSinModificaciones.getCuit());
		agregarACache(establecimientoModificado);
	}
}
