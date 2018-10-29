package com.cine.modelo;

import com.cine.dao.PeliculaPersistente;
import com.cine.utilidades.EstadoActivoInactivo;

public class Pelicula {

	private Integer id = null;
	private String nombre;
	private String director;
	private String genero;
	private Integer duracion;
	private String idioma;
	private Boolean subtitulos;
	private Float calificacion;
	private String observaciones;
	private EstadoActivoInactivo estado;

	public Pelicula(String nombre, String director, String genero, Integer duracion, String idioma, Boolean subtitulos,
			Float calificacion, String observaciones, EstadoActivoInactivo estado) {
//        this.setId(id);
		this.nombre = nombre;
		this.director = director;
		this.genero = genero;
		this.duracion = duracion;
		this.idioma = idioma;
		this.subtitulos = subtitulos;
		this.calificacion = calificacion;
		this.observaciones = observaciones;
		this.estado = estado;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public Boolean getSubtitulos() {
		return subtitulos;
	}

	public void setSubtitulos(Boolean subtitulos) {
		this.subtitulos = subtitulos;
	}

	public Float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public EstadoActivoInactivo getEstado() {
		return estado;
	}

	public void setEstado(EstadoActivoInactivo estado) {
		this.estado = estado;
	}

	public void insertarPelicula() {
		PeliculaPersistente.getInstance().insertar(this);
		this.setId(PeliculaPersistente.getInstance().getIdPelicula(this.getNombre()));
	}

	public void eliminarPelicula() {
		PeliculaPersistente.getInstance().borrar(this);
	}

	public void actualizarPelicula(Integer id,String nombre, String director, String genero, Integer duracion, String idioma,
            Boolean subtitulos, Float calificacion, String observaciones, EstadoActivoInactivo estado) {
    	Pelicula p = new Pelicula(nombre, director, genero, duracion, idioma, subtitulos, calificacion, observaciones, estado);
    	p.setId(id);
    	PeliculaPersistente.getInstance().actualizar(p);
    }

}
