package com.cine.controlador;

import com.cine.dao.Cache;
import com.cine.dao.UsuarioPersistente;
import com.cine.modelo.Rol;
import com.cine.modelo.Usuario;
import com.cine.utilidades.Estado;

import java.util.ArrayList;
import java.util.List;

public class ControladorRol implements Cache {

    public List<Rol> rolList;
    private static ControladorRol instancia;

    public ControladorRol() {
        this.rolList = new ArrayList<>();
    }

    public static ControladorRol getInstance() {
        if (ControladorRol.instancia == null) {
            ControladorRol.instancia = new ControladorRol();
        }
        return ControladorRol.instancia;
    }

    public void altaRol(String nombre, Estado estado) {

    }


    public void bajaRol(Integer dni) {
    }

    public void modificacionRol(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, String fechaDeNacimiento) {
    }


    @Override
    public Rol buscarEnCache(Object key) {
        return rolList
                .stream()
                .filter(rol -> rol.getNombre().equals(key))
                .findFirst()
                .orElse(null);
    }

    public Rol buscarUsuarioEnDb(Integer dni) {
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
    }

}
