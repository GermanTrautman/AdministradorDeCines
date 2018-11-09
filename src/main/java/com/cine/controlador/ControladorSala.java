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

	public Sala buscar(String nombre) {

		Sala sala = (Sala) buscarEnCache(nombre);

		if (sala == null) {

			sala = (Sala) salaPersistente.buscar(nombre);

			AsientoFisico[][] asientos = (AsientoFisico[][]) asientoFisicoPersistente.buscar(sala.getNombre());
			sala.setAsientosFisicos(asientos);

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

	public void baja(String nombre) {

		borrarDeCache(nombre);

		Sala sala = (Sala) salaPersistente.buscar(nombre);

		if (sala != null) {

			sala.setAsientosFisicos((AsientoFisico[][]) asientoFisicoPersistente.buscar(nombre));

			for (int i = 0; i < sala.getAsientosFisicos().length; i++) {

				for (int j = 0; j < sala.getAsientosFisicos().length; j++) {

					if (sala.getAsientosFisicos()[i][j] != null) {

						sala.getAsientosFisicos()[i][j].borrar();
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

	public void modificarAsientos(String nombreSala, AsientoFisico[][] asientosModificados) {

		AsientoFisico[][] asientosAAgregar = new AsientoFisico[100][100];
		AsientoFisico[][] asientosABorrar = new AsientoFisico[100][100];

		for (int i = 1; i < asientosModificados.length; i++) {

			for (int j = 1; j < asientosModificados.length; j++) {
				
				Sala sala = buscar(nombreSala);

				if (asientosModificados[i][j] != null && sala.getAsientosFisicos()[i][j] == null) {

					asientosAAgregar[i][j] = asientosModificados[i][j];

				} else if (asientosModificados[i][j] == null && buscar(nombreSala).getAsientosFisicos()[i][j] != null) {

					asientosABorrar[i][j] = buscar(nombreSala).getAsientosFisicos()[i][j];
				}
			}
		}
		
		for (int i = 1; i < asientosModificados.length; i++) {

			for (int j = 1; j < asientosModificados.length; j++) {
			
				if (asientosAAgregar[i][j] != null) {
					asientosAAgregar[i][j].insertar();
				}
				
				if (asientosABorrar[i][j] != null) {
					asientosABorrar[i][j].borrar();
				}
			}
		}
	}

	@Override
	public Object buscarEnCache(Object nombre) {

		return salas.stream().filter(sala -> sala.getNombre().equals(nombre)).findAny().orElse(null);
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
