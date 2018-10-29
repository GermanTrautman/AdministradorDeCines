package com.cine.modelo;

public class AsientoFisico {

	private Integer fila;
    private Integer numeroDeAsiento;
    
    public AsientoFisico(Integer fila, Integer numeroDeAsiento) {
    	super();
    	this.fila = fila;
    	this.numeroDeAsiento = numeroDeAsiento;
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
}
