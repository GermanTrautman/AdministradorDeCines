package com.cine.controlador;

import com.cine.dao.Cache;
import com.cine.modelo.Rol;
import com.cine.modelo.RolUsuario;

import java.util.ArrayList;
import java.util.List;

public class ControladorRolUsuario implements Cache {

    public List<Rol> rolUsuarioList;
    private static ControladorRolUsuario instancia;

    public ControladorRolUsuario() {
        this.rolUsuarioList = new ArrayList<>();
    }

    public static ControladorRolUsuario getInstance() {
        if (ControladorRolUsuario.instancia == null) {
            ControladorRolUsuario.instancia = new ControladorRolUsuario();
        }
        return ControladorRolUsuario.instancia;
    }

    public void altaRolUsuario(Integer dni, List<String> roles) {

        roles.forEach(r -> {
            RolUsuario unRol = new RolUsuario();
            unRol.setIdUsuario(dni);
            unRol.setIdRol(unRol.buscarRolPorNombre(r));
            unRol.insertarRolUsuario();
            System.out.println("UsuarioRol insertado correctamente ~ " + unRol);
        });
    }

    public void bajaRolUsuario(Integer dni) {
        borrarDeCache(dni);
        RolUsuario rolUsuario = new RolUsuario();
        rolUsuario.setIdUsuario(dni);
        rolUsuario.borrarRolUsuario();
    }


    @Override
    public Rol buscarEnCache(Object key) {
        return rolUsuarioList
                .stream()
                .filter(rol -> rol.getNombre().equals(key))
                .findFirst()
                .orElse(null);
    }

    public Rol buscarRolEnDb(Integer dni) {
        return null;
    }


    @Override
    public void agregarACache(Object entidad) {
    }

    @Override
    public void borrarDeCache(Object key) {

    }

    @Override
    public void actualizarCache(Object entidad) {
        //TODO: ACTUALIZAR CACHE
    }

    public Rol buscarRolPorId(Integer idRol){
        RolUsuario rolUsuario = new RolUsuario();
       return rolUsuario.buscarRolPorId(idRol);
    }



}
