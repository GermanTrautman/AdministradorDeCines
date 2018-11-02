package com.cine.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cine.dao.Cache;
import com.cine.dao.PeliculaPersistente;
import com.cine.modelo.Pelicula;
import com.cine.utilidades.EstadoActivoInactivo;

public class ControladorPelicula implements Cache {
	private static ControladorPelicula instancia;
	private List<Pelicula> peliculas;
//	private PeliculaPersistente peliculaPersistente = new PeliculaPersistente();

	private ControladorPelicula() {
		this.peliculas = new ArrayList<Pelicula>();
	}

	public static ControladorPelicula getInstance() {
		if (ControladorPelicula.instancia == null) {
			ControladorPelicula.instancia = new ControladorPelicula();
		}
		return ControladorPelicula.instancia;
	}

//	@SuppressWarnings("unchecked")
//	public void obtenerPeliculas() {
//		peliculas = (List<Pelicula>) (Object) peliculaPersistente.listar();
//	}

	public void altaPelicula(String nombre, String director, String genero, Integer duracion, String idioma,
			Boolean subtitulos, Float calificacion, String observaciones, EstadoActivoInactivo estado) {

		Pelicula pelicula = new Pelicula(nombre, director, genero, duracion, idioma, subtitulos, calificacion,
				observaciones, estado);
		pelicula.insertarPelicula();
		agregarACache(pelicula);

	}

	public void bajaPelicula(String nombre) {
		Pelicula pelicula = (Pelicula) buscarEnCache(nombre);

		if (pelicula != null) {
			pelicula.eliminarPelicula();
			peliculas.remove(pelicula);
		}
	}

	public void modificarPelicula(Integer id, String nombre, String director, String genero, Integer duracion,
			String idioma, Boolean subtitulos, Float calificacion, String observaciones, EstadoActivoInactivo estado) {
		Pelicula pelicula = (Pelicula) buscarEnCache(nombre);
		
		pelicula.actualizarPelicula(id, nombre, director, genero, duracion, idioma, subtitulos, calificacion, observaciones, estado);
		actualizarCache(pelicula);
		
	}

	@Override
	public Object buscarEnCache(Object nombre) {

		for (Pelicula pelicula : peliculas) {
			if (pelicula.getNombre().equals(nombre)) {
				return pelicula;
			}
		}
		Pelicula pelicula = (Pelicula) PeliculaPersistente.getInstance().buscar(nombre);
		if (pelicula != null) {
			peliculas.add(pelicula);
			return pelicula;
		}
		return null;
	}

//	public void agregarACache(Object entidad) {
//		peliculas.add((Pelicula) entidad);
//
//	}

	@Override
	public void borrarDeCache(Object nombre) {
		if (buscarEnCache(nombre) != null) {
			for (Iterator<Pelicula> iterator = peliculas.listIterator(); iterator.hasNext();) {
				Pelicula pelicula = iterator.next();
				if (pelicula.getNombre().equals(nombre)) {
					iterator.remove();
				}

			}
		}

	}

	public void actualizarCache(Object entidad) {
		Pelicula peliculaModificada = (Pelicula) entidad;
		Pelicula PeliculaSinModificar = (Pelicula) buscarEnCache(peliculaModificada.getId());
		borrarDeCache(PeliculaSinModificar.getNombre());
		agregarACache(peliculaModificada);
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	@Override
	public void agregarACache(Object entidad) {
		peliculas.add((Pelicula) entidad);
		
	}
	
//	public List<Pelicula> getPeliculasDeFuncion(Funcion funcion) {
//		return PeliculaPersistente.getInstance().peliculasPorFuncion(funcion);
//		
//	}
}
