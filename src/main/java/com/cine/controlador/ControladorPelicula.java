package com.cine.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cine.dao.PeliculaPersistente;
import com.cine.modelo.Pelicula;
import com.cine.utilidades.EstadoActivoInactivo;

public class ControladorPelicula {
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
		if (buscarEnCache(pelicula.getNombre()) == null) {
			agregarACache(pelicula);
			if (peliculaPersistente.buscar(pelicula.getNombre()) == null) {
				peliculaPersistente.insertar(pelicula);
			}
		}
	}
	
	public void bajaPelicula (String nombre) {
		borrarDeCache(nombre);
		if (peliculaPersistente.buscar(nombre)!= null) {
			peliculaPersistente.borrar(nombre);
		}
	}

	public void modificarPelicula(String nombre, String director, String genero, Integer duracion, String idioma, Boolean subtitulos, Float calificacion, String observaciones, EstadoActivoInactivo estado) {
		Pelicula pelicula = new Pelicula (nombre, director, genero, duracion, idioma, subtitulos, calificacion,	observaciones, estado);
		peliculaPersistente.actualizar(pelicula);
	}

	public Object buscarEnCache(String nombre) {
		Pelicula peliculaEncontrada = null;
		for (Pelicula pelicula : peliculas) {
			if (pelicula.getNombre().equals(nombre)) {
				peliculaEncontrada = pelicula;
			}
		}
		return peliculaEncontrada;
	}

	public void agregarACache(Object entidad) {
		peliculas.add((Pelicula) entidad);

	}

	public void borrarDeCache(String nombre) {
		if (buscarEnCache(nombre)!=null){
			for(Iterator<Pelicula> iterator = peliculas.listIterator(); iterator.hasNext();) {
				Pelicula pelicula = iterator.next();
				if(pelicula.getNombre().equals(nombre)) {
					iterator.remove();
				}
				
			}
		}

	}

	public void actualizarCache(Object entidad) {
		Pelicula peliculaModificada = (Pelicula) entidad;
		Pelicula PeliculaSinModificar = (Pelicula) buscarEnCache(peliculaModificada.getNombre());
		borrarDeCache(PeliculaSinModificar.getNombre());
		agregarACache(peliculaModificada);
	}

	public List<Pelicula> getPeliculas(){
		return peliculas;
	}
}
