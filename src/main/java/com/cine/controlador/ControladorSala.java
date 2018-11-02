package com.cine.controlador;

import java.util.ArrayList;
import java.util.List;

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
	private AsientoFisico[][] asientosFisicosTemporales = new AsientoFisico[25][25];
	private Sala salaSeleccionada = null;

	private SalaPersistente salaPersistente = new SalaPersistente();
	private AsientoFisicoPersistente asientoFisicoPersistente = new AsientoFisicoPersistente();

	public static ControladorSala getInstance() {
		return controladorSala;
	}
	
	public List<Sala> getSalas() {
		return salas;
	}
	
	public AsientoFisico[][] getAsientosFisicosTemporales() {
		return asientosFisicosTemporales;
	}
	
	public Sala getSalaSeleccionada() {
		return salaSeleccionada;
	}

	public void setSalaSeleccionada(Sala salaSeleccionada) {
		this.salaSeleccionada = salaSeleccionada;
	}
	
	@SuppressWarnings("unchecked")
	public void obtenerSalas() {
		salas = (List<Sala>) (Object) salaPersistente.listar();
	}

	public Sala buscar(String nombre) {
		
		Sala sala = (Sala) buscarEnCache(nombre);
		
		if (sala == null) {
			
			sala = (Sala) salaPersistente.buscar(nombre);
			
			AsientoFisico[][] asientosFisicos = (AsientoFisico[][]) asientoFisicoPersistente.buscar(sala.getNombre());
			sala.setAsientosFisicos(asientosFisicos);
			
			if (sala != null) {
				agregarACache(sala);
			}
		}
	
		return sala;
	}
	
	public void alta(String nombre, AsientoFisico[][] asientos, Integer cuitEstablecimiento, String estadoEnLetras) {
		
		Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(cuitEstablecimiento);
		Estado estado = Estado.valueOf(estadoEnLetras.toUpperCase());

		Sala sala = new Sala(nombre, establecimiento, estado);
		sala.setAsientosFisicos(asientos);

		if (buscarEnCache(sala.getNombre()) == null) {

			agregarACache(sala);

			if (salaPersistente.buscar(sala.getNombre()) == null) {
				
				sala.insertar();
				
				for (int i = 0; i < asientos.length; i++) {
					for (int j = 0; j < asientos.length; j++) {
						
						if (asientos[i][j] != null) {
							asientos[i][j].insertar();
						}
					}
				}
			}
		}
	}

	public void baja(String nombre) {

		borrarDeCache(nombre);
		
		Sala sala = (Sala) salaPersistente.buscar(nombre);

		if (sala != null) {
			
			sala.setAsientosFisicos((AsientoFisico[][]) asientoFisicoPersistente.buscar(nombre));
			
			for (int i = 0; i < sala.getAsientosFisicos().length; i++) {
				
				for (int j = 0; j < sala.getAsientosFisicos().length; j++) {
					
					AsientoFisico asientoFisico = sala.getAsientosFisicos()[i][j];
					if (asientoFisico != null) {
						asientoFisico.borrar();
					}
				}
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
