package com.cine.modelo;

import com.cine.utilidades.Estado;

public class AsientoFisico {

	private Integer idSala;
	private Integer fila;
    private Integer numeroDeAsiento;
    private Estado estado;
    
	public AsientoFisico(Integer idSala, Integer fila, Integer numeroDeAsiento, Estado estado) {
		super();
		this.idSala = idSala;
		this.fila = fila;
		this.numeroDeAsiento = numeroDeAsiento;
		this.estado = estado;
	}

	public Integer getIdSala() {
		return idSala;
	}

	public void setIdSala(Integer idSala) {
		this.idSala = idSala;
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
}
