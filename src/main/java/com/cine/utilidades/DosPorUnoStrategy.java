package com.cine.utilidades;

public class DosPorUnoStrategy implements DescuentoProviderStrategy {


    public DosPorUnoStrategy() {
    }


    @Override
    public Double calcularMonto(Double montoACalcular, Integer porcentaje) {
        return (montoACalcular / 2);
    }
}
