package com.cine.utilidades;

public enum DescuentoProvider {

    DOSXUNO("2x1"),PORCENTAJE("Porcentaje");

    private String descuento;

     DescuentoProvider(String descuento){
         this.descuento = descuento;
     }

    public String getLabel() {
        return descuento;
    }
}
