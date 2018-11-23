package com.cine.modelo;

import com.cine.dao.EstablecimientoDescuentoPersistente;
import sun.security.krb5.internal.crypto.Des;

import java.time.LocalDate;
import java.util.List;

public class EstablecimientoDescuento {

    private Integer cuitEstablecimiento;
    private Integer idDescuento;
    private LocalDate vigenciaInicio;
    private LocalDate vigenciaFin;

    public Integer getCuitEstablecimiento() {
        return cuitEstablecimiento;
    }

    public void setCuitEstablecimiento(Integer cuitEstablecimiento) {
        this.cuitEstablecimiento = cuitEstablecimiento;
    }

    public Integer getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Integer idDescuento) {
        this.idDescuento = idDescuento;
    }

    public LocalDate getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(LocalDate vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    public LocalDate getVigenciaFin() {
        return vigenciaFin;
    }

    public void setVigenciaFin(LocalDate vigenciaFin) {
        this.vigenciaFin = vigenciaFin;
    }

    public EstablecimientoDescuento(Integer cuitEstablecimiento, Integer idDescuento, LocalDate vigenciaInicio, LocalDate vigenciaFin) {
        this.cuitEstablecimiento = cuitEstablecimiento;
        this.idDescuento = idDescuento;
        this.vigenciaInicio = vigenciaInicio;
        this.vigenciaFin = vigenciaFin;
    }

    public EstablecimientoDescuento() {
    }

    public void insertarEstablecimientoDescuento(EstablecimientoDescuento establecimientoDescuento){
        EstablecimientoDescuentoPersistente.getInstance().insertar(establecimientoDescuento);
    }


    public List<Descuento> obtenerDescuentosPorEstablecimiento(Integer cuitEstablecimiento){
        return EstablecimientoDescuentoPersistente.getInstance().obtenerDescuentosPorEstablecimiento(cuitEstablecimiento);
    }
}
