package com.cine.utilidades;

import java.util.HashMap;
import java.util.Map;

public class DescuentoStrategy {

    DescuentoProviderStrategy dosPorUnoStrategy = new DosPorUnoStrategy();

    DescuentoProviderStrategy porcentajeStrategy = new PorcentajeStrategy();


    private Map<DescuentoProvider, DescuentoProviderStrategy> strategyMap = new HashMap<>();

    public DescuentoStrategy() {
        this.strategyMap.put(DescuentoProvider.DOSXUNO,dosPorUnoStrategy);
        this.strategyMap.put(DescuentoProvider.PORCENTAJE,porcentajeStrategy);
    }

    public DescuentoProviderStrategy getStrategy(DescuentoProvider descuentoProvider){
        return strategyMap.get(descuentoProvider);
    }
}
