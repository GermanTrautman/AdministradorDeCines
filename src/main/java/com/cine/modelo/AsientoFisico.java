package com.cine.modelo;

import com.cine.utilidades.Estado;

public class AsientoFisico {

	private Integer fila;
    private Integer numeroDeAsiento;
    private Estado estado;
    
    public AsientoFisico(Integer fila, Integer numeroDeAsiento, Estado estado) {
		super();
		this.fila = fila;
		this.numeroDeAsiento = numeroDeAsiento;
		this.estado = estado;
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
