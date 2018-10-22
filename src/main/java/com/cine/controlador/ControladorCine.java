package com.cine.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cine.Dao.Cache;
import com.cine.Dao.EstablecimientoPersistente;
import com.cine.modelo.Establecimiento;

public class ControladorCine implements Cache {

	private static final ControladorCine controladorCine = new ControladorCine();

	private List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();

	private EstablecimientoPersistente establecimientoPersistente = new EstablecimientoPersistente();

	public static ControladorCine getInstance() {
		return controladorCine;
	}

	@SuppressWarnings("unchecked")
	private void obtenerEstablecimientos() {
		establecimientos = (List<Establecimiento>) (Object) establecimientoPersistente.listar();
	}

	public void altaEstablecimiento(Integer cuit, String nombre, String domicilio, Integer capacidad) {

		Establecimiento establecimiento = new Establecimiento(cuit, nombre, domicilio, capacidad);

		if (buscarEnCache(establecimiento.getCuit()) == null) {

			agregarACache(establecimiento);

			if (establecimientoPersistente.buscar(establecimiento.getCuit()) == null) {
				establecimientoPersistente.insertar(establecimiento);
			}
		}
	}

	public void bajaEstablecimiento(Integer cuit) {

		borrarDeCache(cuit);

		if (establecimientoPersistente.buscar(cuit) != null) {
			establecimientoPersistente.borrar(cuit);
		}
	}
	
	public void modificacionEstablecimiento(Integer cuit, String nombre, String domicilio, Integer capacidad) {
		
		Establecimiento establecimiento = new Establecimiento(cuit, nombre, domicilio, capacidad);
		
		actualizarCache(establecimiento);
		establecimientoPersistente.actualizar(establecimiento);
	}

	@Override
	public Object buscarEnCache(Integer key) {

		Establecimiento establecimientoEncontrado = null;

		for (Establecimiento establecimiento : establecimientos) {

			if (establecimiento.getCuit().equals(key)) {
				establecimientoEncontrado = establecimiento;
			}
		}

		return establecimientoEncontrado;
	}

	@Override
	public void agregarACache(Object entidad) {
		establecimientos.add((Establecimiento) entidad);
	}

	@Override
	public void borrarDeCache(Integer key) {

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
	
	public List<Establecimiento> getEstablecimientos() {
		//Cambiar
		obtenerEstablecimientos();
		return establecimientos;
	}
}
