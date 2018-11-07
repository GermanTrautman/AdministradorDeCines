package com.cine.controlador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cine.dao.AsientoFisicoPersistente;
import com.cine.dao.Cache;
import com.cine.dao.SalaPersistente;
import com.cine.modelo.AsientoFisico;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;

public class ControladorSala implements Cache {
	
	private static final ControladorSala controladorSala = new ControladorSala();

	private List<Sala> salas = new ArrayList<>();

	private SalaPersistente salaPersistente = new SalaPersistente();
	private AsientoFisicoPersistente asientoFisicoPersistente = new AsientoFisicoPersistente();

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

	@SuppressWarnings("unchecked")
	public Sala buscar(String nombre) {
		
		Sala sala = (Sala) buscarEnCache(nombre);
		
		if (sala == null) {
			
			sala = (Sala) salaPersistente.buscar(nombre);
			
			Set<AsientoFisico> asientos = (Set<AsientoFisico>) asientoFisicoPersistente.buscar(sala.getNombre());
			sala.setAsientos(asientos);
			
			if (sala != null) {
				agregarACache(sala);
			}
		}
	
		return sala;
	}
	
	public void alta(String nombre, Integer cuitEstablecimiento, String estadoEnLetras) {
		
		Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(cuitEstablecimiento);
		Estado estado = Estado.valueOf(estadoEnLetras.toUpperCase());

		Sala sala = new Sala(nombre, establecimiento, estado);

		if (buscarEnCache(sala.getNombre()) == null) {

			agregarACache(sala);

			if (salaPersistente.buscar(sala.getNombre()) == null) {
				sala.insertar();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void baja(String nombre) {

		borrarDeCache(nombre);
		
		Sala sala = (Sala) salaPersistente.buscar(nombre);

		if (sala != null) {
			
			sala.setAsientos((Set<AsientoFisico>) asientoFisicoPersistente.buscar(nombre));
			
			for (AsientoFisico asientoFisico : sala.getAsientos()) {
				asientoFisico.borrar();
			}

			sala.borrar();
		}
	}
	
	public void modificacion(String nombre, Integer cuitEstablecimiento, String estadoEnLetras) {
		
		Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(cuitEstablecimiento);
		Estado estado = Estado.valueOf(estadoEnLetras.toUpperCase());

		Sala salaModificada = new Sala(nombre, establecimiento, estado);
		
		actualizarCache(salaModificada);
		salaModificada.actualizar(nombre, establecimiento, estado);
	}
	
	public void modificarAsientos(String nombreSala, Set<AsientoFisico> asientosModificados) {
		
		Set<AsientoFisico> asientosAAgregar = new HashSet<>();
		Set<AsientoFisico> asientosABorrar = new HashSet<>();
		
		for (AsientoFisico asientoModificado : asientosModificados) {
			
			boolean elAsientoFueAgregado = getInstance().buscar(nombreSala).getAsientos().add(asientoModificado);
			
			if (elAsientoFueAgregado) {
				asientosAAgregar.add(asientoModificado);
			} else {
				asientosABorrar.add(asientoModificado);
			}
		}
		
		for (AsientoFisico asientoAAgregar : asientosAAgregar) {
			asientoAAgregar.insertar();
		}
		
		for (AsientoFisico asientoABorrar : asientosABorrar) {
			asientoABorrar.borrar();
		}
	}
	
	@Override
	public Object buscarEnCache(Object nombre) {

		return salas.stream()
                .filter(sala -> sala.getNombre().equals(nombre))
                .findAny()
                .orElse(null);
	}

	@Override
	public void agregarACache(Object sala) {
		salas.add((Sala) sala);
	}

	@Override
	public void borrarDeCache(Object nombre) {
		this.salas.removeIf(sala -> sala.getNombre().equals(nombre));
	}

	@Override
	public void actualizarCache(Object objetoSalaModificada) {

		Sala salaModificada = (Sala) objetoSalaModificada;
		
		if (buscar(salaModificada.getNombre()) != null) {
			
			for (Sala sala : salas) {
				
				if (sala.getNombre().equals(salaModificada.getNombre())) {
					
					if (!sala.getEstablecimiento().getCuit().equals(salaModificada.getEstablecimiento().getCuit())) {
						sala.setEstablecimiento(salaModificada.getEstablecimiento());
					}
					
					if (!sala.getEstado().equals(salaModificada.getEstado())) {
						sala.setEstado(salaModificada.getEstado());
					}
				}
			}
		}
	}
}
