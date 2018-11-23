package com.cine.controlador;

import com.cine.dao.Cache;
import com.cine.modelo.Descuento;
import com.cine.modelo.EstablecimientoDescuento;
import com.cine.modelo.Rol;
import com.cine.modelo.RolUsuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControladorDescuentoEstablecimiento implements Cache {

    public List<EstablecimientoDescuento> establecimientoDescuentoList;
    private static ControladorDescuentoEstablecimiento instancia;

    public ControladorDescuentoEstablecimiento() {
        this.establecimientoDescuentoList = new ArrayList<>();
    }

    public static ControladorDescuentoEstablecimiento getInstance() {
        if (ControladorDescuentoEstablecimiento.instancia == null) {
            ControladorDescuentoEstablecimiento.instancia = new ControladorDescuentoEstablecimiento();
        }
        return ControladorDescuentoEstablecimiento.instancia;
    }

    public void altaDescuentoEstablecimiento(Integer cuitEstablecimiento, Integer idDescuento, LocalDate vigenciaInicio, LocalDate vigenciaFin) {
        EstablecimientoDescuento establecimientoDescuento = new EstablecimientoDescuento(cuitEstablecimiento,idDescuento,vigenciaInicio,vigenciaFin);
        establecimientoDescuento.insertarEstablecimientoDescuento(establecimientoDescuento);

        if (establecimientoDescuentoList.contains(establecimientoDescuento)){

            establecimientoDescuentoList.forEach(r -> {
                establecimientoDescuento.setCuitEstablecimiento(cuitEstablecimiento);
                establecimientoDescuento.setIdDescuento(idDescuento);
                establecimientoDescuento.setVigenciaInicio(vigenciaInicio);
                establecimientoDescuento.setVigenciaFin(vigenciaFin);
                agregarACache(establecimientoDescuento);
                establecimientoDescuento.insertarEstablecimientoDescuento(establecimientoDescuento);
                System.out.println("UsuarioRol insertado correctamente ~ " + establecimientoDescuento);
            });
        }
    }

    public void bajaRolUsuario(Integer dni) {
        borrarDeCache(dni);
        RolUsuario rolUsuario = new RolUsuario();
        rolUsuario.setIdUsuario(dni);
        rolUsuario.borrarRolUsuario();
    }


    @Override
    public EstablecimientoDescuento buscarEnCache(Object key) {
        return establecimientoDescuentoList
                .stream()
                .filter(descuento -> descuento.getIdDescuento().equals(key))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void agregarACache(Object entidad) {
        this.establecimientoDescuentoList.add((EstablecimientoDescuento) entidad);
    }

    @Override
    public void borrarDeCache(Object key) {
       // this.establecimientoDescuentoList.removeIf(rolUsuario -> rolUsuario.getIdUsuario().equals(key));
    }

    @Override
    public void actualizarCache(Object entidad) {
        establecimientoDescuentoList.add(this.establecimientoDescuentoList
                .stream()
                .filter(descuento -> descuento.getIdDescuento().equals(((EstablecimientoDescuento) entidad).getIdDescuento()))
                .findFirst()
                .get());
    }

    public Rol buscarRolPorId(Integer idRol) {
        RolUsuario rolUsuario = new RolUsuario();
        return rolUsuario.buscarRolPorId(idRol);
    }

    public List<Descuento> obtenerDescuentosPorEstablecimiento(Integer cuitEstablecimiento){
        EstablecimientoDescuento establecimientoDescuento = new EstablecimientoDescuento();
        return establecimientoDescuento.obtenerDescuentosPorEstablecimiento(cuitEstablecimiento);
    }


}
