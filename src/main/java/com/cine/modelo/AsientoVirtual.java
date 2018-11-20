package com.cine.modelo;

import com.cine.dao.AsientoVirtualPersistente;
import com.cine.utilidades.EstadoVirtual;

public class AsientoVirtual {
	
	public static final Integer FILAS = 100;
	public static final Integer ASIENTOSPORFILA = 100;

	private AsientoFisico fisicoAsociado;
	private EstadoVirtual estado;
	private Integer idFuncion;

	public AsientoVirtual(AsientoFisico fisicoAsociado, Integer idFuncion) {
		super();
		this.fisicoAsociado = fisicoAsociado;
		this.idFuncion = idFuncion;
		this.estado = EstadoVirtual.LIBRE;
		
	}

	public AsientoFisico getFisicoAsociado() {
		return fisicoAsociado;
	}

	public void setFisicoAsociado(AsientoFisico fisicoAsociado) {
		this.fisicoAsociado = fisicoAsociado;
	}

	public EstadoVirtual getEstado() {
		return estado;
	}

	public void setEstado(EstadoVirtual estado) {
		this.estado = estado;
	}

	public Integer getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(Integer idFuncion) {
		this.idFuncion = idFuncion;
	}

	public void insertar() {
		AsientoVirtualPersistente asientoVirtualPersistente = new AsientoVirtualPersistente();
		asientoVirtualPersistente.insertar(this);
	}
	
	public void vender() {
		this.setEstado(EstadoVirtual.OCUPADO);
		AsientoVirtualPersistente asientoVirtualPersistente = new AsientoVirtualPersistente();
		asientoVirtualPersistente.actualizar(this);
	}

	public void borrar() {
		AsientoVirtualPersistente asientoVirtualPersistente = new AsientoVirtualPersistente();
		asientoVirtualPersistente.borrar(this);
	}
}
