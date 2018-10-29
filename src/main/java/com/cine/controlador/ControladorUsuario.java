package com.cine.controlador;

import com.cine.dao.Cache;
import com.cine.dao.UsuarioPersistente;
import com.cine.modelo.Usuario;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ControladorUsuario implements Cache {

    public List<Usuario> usuarioList;
    private static ControladorUsuario instancia;

    public ControladorUsuario() {
        this.usuarioList = new ArrayList<>();
    }

    public static ControladorUsuario getInstance() {
        if (ControladorUsuario.instancia == null) {
            ControladorUsuario.instancia = new ControladorUsuario();
        }
        return ControladorUsuario.instancia;
    }

    public void altaUsuario(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, Date fechaDeNacimiento) {

        if (buscarEnCache(dni) == null) {

            Usuario usuario = new Usuario(dni, nombreDeUsuario, email, password, nombre, domicilio, fechaDeNacimiento);
            usuarioList.add(usuario);
            usuario.insertarUsuario();
        } else {
            throw new RuntimeException("No se permiten usuarios duplicados");
        }
    }


    public void bajaUsuario(Integer dni) {
        borrarDeCache(dni);
        Usuario usuario = (Usuario) UsuarioPersistente.getInstance().buscar(dni);

        if (usuario != null) {
            UsuarioPersistente.getInstance().borrar(dni);
        }
    }

    public void modificacionUsuario(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, Date fechaDeNacimiento) {

        Usuario usuario = new Usuario(dni, nombreDeUsuario, email, password, nombre, domicilio, fechaDeNacimiento);

        actualizarCache(usuario);
        usuario.actualizarUsuario(usuario);
    }


    @Override
    public Usuario buscarEnCache(Object key) {
        return usuarioList.stream()
                .filter(usuario -> usuario.getDni().equals(key))
                .findAny()
                .orElse(null);
    }

    @Override
    public void agregarACache(Object entidad) {
        this.usuarioList.add((Usuario) entidad);
    }

    @Override
    public void borrarDeCache(Object key) {
        this.usuarioList.removeIf(usuario -> usuario.getDni().equals(key));

    }

    @Override
    public void actualizarCache(Object entidad) {
        Usuario usuario = (Usuario) entidad;
        borrarDeCache(usuario.getDni());
        this.usuarioList.add(usuario);
    }

}
