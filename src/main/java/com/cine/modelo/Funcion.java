package com.cine.modelo;

import com.cine.dao.FuncionPersistente;
import com.cine.utilidades.Estado;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class Funcion {

	private Integer id = null;
	private LocalDate fecha;
	private Time hora;
	private Sala sala;
	private Pelicula pelicula;
	private AsientoVirtual[][] asientosVirtuales = new AsientoVirtual[25][25];
	private Estado estado;

	public Funcion(LocalDate fecha2, Sala sala, Pelicula pelicula, Estado estado, Time hora) {
		this.fecha = fecha2;
		this.sala = sala;
		this.pelicula = pelicula;
		this.estado = estado;
		this.hora = hora;
//		cargarAsientosVirtuales();
	}

	public Funcion() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<AsientoVirtual> mostrarAsientos() {
		return null;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public AsientoVirtual[][] getAsientoVirtual() {
		return asientosVirtuales;
	}

	public void setAsientoVirtual(AsientoVirtual[][] asientoVirtual) {
		this.asientosVirtuales = asientoVirtual;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	private void cargarAsientosVirtuales() {
		/*
		AsientoFisico[][] asientosSala = this.sala.getAsientosFisicos();
		for (int i = 0; i < asientosSala.length; i++) {
			for (int j = 0; j < asientosSala.length; j++) {
				if (asientosSala[i][j] != null && asientosSala[i][j].getEstado() != null
						&& asientosSala[i][j].getEstado().equals(Estado.ACTIVO)) {
					this.asientosVirtuales[i][j] = new AsientoVirtual(asientosSala[i][j], this.getId());
					this.asientosVirtuales[i][j].insertar();
				}
			}
		}
		*/
	}

	public void insertarFuncion() {
		FuncionPersistente.getInstance().insertar(this);
		this.setId(FuncionPersistente.getInstance().getIdFuncion(this.getFecha(), this.getSala(), this.getPelicula(), this.getHora()));
		this.cargarAsientosVirtuales();

	}

	public void eliminarFuncion() {
		FuncionPersistente.getInstance().borrar(this.getId());
	}

	public void actualizarFuncion(Integer id, LocalDate fecha, Sala sala, Pelicula pelicula, Estado estado, Time hora) {
		Funcion f = new Funcion(fecha, sala, pelicula, estado, hora);
		f.setId(id);
		FuncionPersistente.getInstance().actualizar(f);
	}

	public Funcion buscarPeliculaPorDiaYHora(Integer idEstablecimiento, String nombrePelicula){
		return FuncionPersistente.getInstance().buscarPeliculaPorDiaYHora(idEstablecimiento,nombrePelicula);
	}

}
