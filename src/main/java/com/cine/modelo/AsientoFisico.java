package com.cine.modelo;

import com.cine.dao.AsientoFisicoPersistente;
import com.cine.utilidades.Estado;

public class AsientoFisico {

	private String nombreSala;
	private Integer fila;
    private Integer numeroDeAsiento;
    private Estado estado;
	
	public AsientoFisico(String nombreSala, Integer fila, Integer numeroDeAsiento, Estado estado) {
		super();
		this.nombreSala = nombreSala;
		this.fila = fila;
		this.numeroDeAsiento = numeroDeAsiento;
		this.estado = estado;
	}

	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public Integer getFila() {
		return fila;
	}
	
    public void setFila(Integer fila) {
		this.fila = fila;
	}
	
	public Integer getNumeroDeAsiento() {
		return numeroDeAsiento;
	}

	public void setNumeroDeAsiento(Integer numeroDeAsiento) {
		this.numeroDeAsiento = numeroDeAsiento;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void insertar() {

		AsientoFisicoPersistente asientoFisicoPersistente = new AsientoFisicoPersistente();
		asientoFisicoPersistente.insertar(this);
	}
	
	public void borrar() {
		
		AsientoFisicoPersistente asientoFisicoPersistente = new AsientoFisicoPersistente();
		asientoFisicoPersistente.borrar(this);
	}
}
