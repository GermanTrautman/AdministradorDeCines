package com.cine.controlador;

import com.cine.modelo.Establecimiento;

public class ControladorCine {

	public void altaEstablecimiento(Integer cuit, String nombre, String domicilio, Integer capacidad) {

		Establecimiento establecimiento = new Establecimiento(cuit, nombre, domicilio, capacidad);
		
		establecimiento.guardar(establecimiento);
	}
}
