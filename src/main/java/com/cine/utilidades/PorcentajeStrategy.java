package com.cine.utilidades;

public class PorcentajeStrategy implements DescuentoProviderStrategy {

    private Integer porcentaje;

    public PorcentajeStrategy() {

    }

    @Override
    public Double calcularMonto(Double montoACalcular, Integer porcentaje) {
        return montoACalcular - ((montoACalcular * porcentaje) / 100);
    }
}
