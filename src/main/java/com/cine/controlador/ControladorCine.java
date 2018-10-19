package com.cine.controlador;

import com.cine.modelo.Establecimiento;
import com.cine.servicio.IServicioCine;

public class ControladorCine {

    private IServicioCine servicioCine;

    public Establecimiento crearEstablecimiento() {

        return servicioCine.crearEstablecimiento();
    }

    public Establecimiento modificarEstablecimiento() {
        return servicioCine.modificarEstablecimiento();
    }
}
