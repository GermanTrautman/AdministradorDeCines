package com.cine.observer;

import java.util.ArrayList;
import java.util.List;

public class Observado {

    private List<IObservador> observadores = new ArrayList<IObservador>();

    public void agregarObservador(IObservador obs){
        this.observadores.add(obs);
    }

    public void quitarObservador(IObservador obs){
        this.observadores.remove(obs);
    }

    public void notificarObservadores(){
        for(IObservador obs : this.observadores){
            obs.actualizar();
        }
    }
}
