package com.cine.controlador;

import java.util.ArrayList;
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
	public void borrarDeCache(Object key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarCache(Object entidad) {
		// TODO Auto-generated method stub
		
	}
}
