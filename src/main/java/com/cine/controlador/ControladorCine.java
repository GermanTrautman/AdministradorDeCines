package com.cine.controlador;

import java.util.ArrayList;
import java.util.List;

import com.cine.Dao.EstablecimientoPersistente;
import com.cine.modelo.Establecimiento;

public class ControladorCine {
	
	private static final ControladorCine controladorCine = new ControladorCine();

    private ControladorCine() {}

    public static ControladorCine getInstance() {
    	return controladorCine; 
    }
	
	private List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();

	public void altaEstablecimiento(Integer cuit, String nombre, String domicilio, Integer capacidad) {

		Establecimiento establecimiento = new Establecimiento(cuit, nombre, domicilio, capacidad);
		
		EstablecimientoPersistente establecimientoPersistente = new EstablecimientoPersistente();
		establecimientoPersistente.insertar(establecimientos, establecimiento);
	}
}
