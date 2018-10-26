package com.cine.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cine.dao.Cache;
import com.cine.dao.SalaPersistente;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;

public class ControladorSala implements Cache {
	
	private static final ControladorSala controladorSala = new ControladorSala();

	private List<Sala> salas = new ArrayList<>();

	private SalaPersistente salaPersistente = new SalaPersistente();

	public static ControladorSala getInstance() {
		return controladorSala;
	}
	
	public List<Sala> getSalas() {
		return salas;
	}
	
	@SuppressWarnings("unchecked")
	public void obtenerSalas() {
		salas = (List<Sala>) (Object) salaPersistente.listar();
	}

	public void alta(String nombre, Integer capacidad, Integer cuitEstablecimiento, String estadoEnLetras) {
		
		Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(cuitEstablecimiento);
		Estado estado = Estado.valueOf(estadoEnLetras.toUpperCase());

		Sala sala = new Sala(nombre, capacidad, establecimiento, estado);

		if (buscarEnCache(sala.getNombre()) == null) {

			agregarACache(sala);

			if (salaPersistente.buscar(sala.getNombre()) == null) {
				salaPersistente.insertar(sala);
			}
		}
	}

	public void baja(String nombre) {

		borrarDeCache(nombre);

		if (salaPersistente.buscar(nombre) != null) {
			salaPersistente.borrar(nombre);
		}
	}
	
	public void modificacion(String nombre, Integer capacidad, Integer cuitEstablecimiento, String estadoEnLetras) {
		
		Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(cuitEstablecimiento);
		Estado estado = Estado.valueOf(estadoEnLetras.toUpperCase());

		Sala sala = new Sala(nombre, capacidad, establecimiento, estado);
		
		actualizarCache(sala);
		salaPersistente.actualizar(sala);
	}
	
	@Override
	public Object buscarEnCache(Object nombre) {

		Sala salaEncontrada = null;

		for (Sala sala : salas) {

			if (sala.getNombre().equals(nombre)) {
				salaEncontrada = sala;
			}
		}

		return salaEncontrada;
	}

	@Override
	public void agregarACache(Object sala) {
		salas.add((Sala) sala);
	}

	@Override
	public void borrarDeCache(Object nombre) {

		if (buscarEnCache(nombre) != null) {

			for (Iterator<Sala> iterator = salas.listIterator(); iterator.hasNext();) {
				
				Sala sala = iterator.next();
				
				if (sala.getNombre().equals(nombre)) {
					iterator.remove();
				}
			}
		}
	}

	@Override
	public void actualizarCache(Object sala) {

		Sala salaModificada = (Sala) sala;
		
		Sala salaSinModificaciones = (Sala) buscarEnCache(salaModificada.getNombre());
		
		borrarDeCache(salaSinModificaciones.getNombre());
		agregarACache(salaModificada);
	}
}
