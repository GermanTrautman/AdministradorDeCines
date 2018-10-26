package com.cine.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cine.dao.Cache;
import com.cine.dao.PeliculaPersistente;
import com.cine.modelo.Pelicula;
import com.cine.utilidades.EstadoActivoInactivo;

public class ControladorPelicula implements Cache {
	private static final ControladorPelicula controladorPelicula = new ControladorPelicula();
	private List<Pelicula> peliculas = new ArrayList<Pelicula>();
	private PeliculaPersistente peliculaPersistente = new PeliculaPersistente();

	public static ControladorPelicula getInstance() {
		return controladorPelicula;
	}

	@SuppressWarnings("unchecked")
	public void obtenerPeliculas() {
		peliculas = (List<Pelicula>) (Object) peliculaPersistente.listar();
	}

	public void altaPelicula(String nombre, String director, String genero, Integer duracion, String idioma,
			Boolean subtitulos, Float calificacion, String observaciones, EstadoActivoInactivo estado) {
		Pelicula pelicula = new Pelicula(nombre, director, genero, duracion, idioma, subtitulos, calificacion,
				observaciones, estado);
		if (buscarEnCache(pelicula.getId()) == null) {
			
			if (peliculaPersistente.buscar(pelicula.getId()) == null) {
				peliculaPersistente.insertar(pelicula);
				pelicula.setId(peliculaPersistente.getIdPelicula(nombre));
				agregarACache(pelicula);
			}
		}
	}

	public void bajaPelicula(Integer id) {
		borrarDeCache(id);
		if (peliculaPersistente.buscar(id) != null) {
			peliculaPersistente.borrar(id);
		}
	}

	public void modificarPelicula(Integer id, String nombre, String director, String genero, Integer duracion,
			String idioma, Boolean subtitulos, Float calificacion, String observaciones, EstadoActivoInactivo estado) {
		Pelicula pelicula = new Pelicula(nombre, director, genero, duracion, idioma, subtitulos, calificacion,
				observaciones, estado);
		pelicula.setId(id);
		actualizarCache(pelicula);
		peliculaPersistente.actualizar(pelicula);
	}

	@Override
	public Object buscarEnCache(Integer key) {
		Pelicula peliculaEncontrada = null;
		for (Pelicula pelicula : peliculas) {
			if (pelicula.getId().equals(key)) {
				peliculaEncontrada = pelicula;
			}
		}
		return peliculaEncontrada;
	}

	public void agregarACache(Object entidad) {
		peliculas.add((Pelicula) entidad);

	}

	public void borrarDeCache(Integer key) {
		if (buscarEnCache(key) != null) {
			for (Iterator<Pelicula> iterator = peliculas.listIterator(); iterator.hasNext();) {
				Pelicula pelicula = iterator.next();
				if (pelicula.getId().equals(key)) {
					iterator.remove();
				}

			}
		}

	}

	public void actualizarCache(Object entidad) {
		Pelicula peliculaModificada = (Pelicula) entidad;
		Pelicula PeliculaSinModificar = (Pelicula) buscarEnCache(peliculaModificada.getId());
		borrarDeCache(PeliculaSinModificar.getId());
		agregarACache(peliculaModificada);
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}
}
