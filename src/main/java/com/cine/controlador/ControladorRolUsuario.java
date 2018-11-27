package com.cine.controlador;

import com.cine.dao.Cache;
import com.cine.modelo.Rol;
import com.cine.modelo.RolUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorRolUsuario implements Cache {

    public List<RolUsuario> rolUsuarioList;
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
            RolUsuario unRolUsuario = new RolUsuario();
            unRolUsuario.setIdUsuario(dni);
            unRolUsuario.setIdRol(unRolUsuario.buscarRolPorNombre(r));
            agregarACache(unRolUsuario);
            unRolUsuario.insertarRolUsuario();
            System.out.println("UsuarioRol insertado correctamente ~ " + unRolUsuario);
        });
    }

    public void bajaRolUsuario(Integer dni) {
        borrarDeCache(dni);
        RolUsuario rolUsuario = new RolUsuario();
        rolUsuario.setIdUsuario(dni);
        rolUsuario.borrarRolUsuario();
    }


    @Override
    public RolUsuario buscarEnCache(Object key) {
        return rolUsuarioList
                .stream()
                .filter(rol -> rol.getIdUsuario().equals(key))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void agregarACache(Object entidad) {
        this.rolUsuarioList.add((RolUsuario) entidad);
    }

    @Override
    public void borrarDeCache(Object key) {
        this.rolUsuarioList.removeIf(rolUsuario -> rolUsuario.getIdUsuario().equals(key));
    }

    @Override
    public void actualizarCache(Object entidad) {
        rolUsuarioList.add(this.rolUsuarioList
                .stream()
                .filter(rolUsuario -> rolUsuario.getIdUsuario().equals(((RolUsuario) entidad).getIdUsuario()))
                .findFirst()
                .get());
    }

    public Rol buscarRolPorId(Integer idRol) {
        RolUsuario rolUsuario = new RolUsuario();
        return rolUsuario.buscarRolPorId(idRol);
    }


}
