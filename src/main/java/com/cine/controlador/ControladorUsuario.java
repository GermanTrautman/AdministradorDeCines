package com.cine.controlador;

import com.cine.dao.Cache;
import com.cine.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void altaUsuario(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, String fechaDeNacimiento) {

        if (buscarEnCache(dni) == null) {

            Usuario usuario = new Usuario(dni, nombreDeUsuario, email, password, nombre, domicilio, fechaDeNacimiento);
            agregarACache(usuario);
            usuario.insertarUsuario();
        } else {
            throw new RuntimeException("No se permiten usuarios duplicados");
        }
    }


    public void bajaUsuario(Integer dni) {
        borrarDeCache(dni);
        System.out.println("Usuario con dni" + dni + "borrado correctamente de la cache");
    }

    public void modificacionUsuario(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, String fechaDeNacimiento) {

        Usuario usuario = new Usuario(dni, nombreDeUsuario, email, password, nombre, domicilio, fechaDeNacimiento);
        ControladorRolUsuario.getInstance().bajaRolUsuario(usuario.getDni());
        actualizarCache(usuario);
        usuario.actualizarUsuario(usuario);
    }


    @Override
    public Usuario buscarEnCache(Object key) {
        return usuarioList
                .stream()
                .filter(usuario -> usuario.getDni().equals(key))
                .findFirst()
                .orElse(null);
    }

    public Usuario buscarUsuarioEnDb(Integer dni) {
        Usuario userDb = new Usuario();
        userDb = userDb.buscarUsuario(dni);
        if (userDb.getDni() != null) {

            usuarioList.add(userDb);
        }
        return userDb;
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
        usuario.actualizarUsuario(usuario);
    }

    public Usuario buscarPorNombreUsuarioYPass(String nombreDeUsuario, String password) {
        Usuario usr = new Usuario();
        if (!usuarioList.isEmpty()) {

            List<Usuario> usuarios = usuarioList.stream().filter(usuario -> nombreDeUsuario.equals(usuario.getNombreDeUsuario())).collect(Collectors.toList());
            if (!usuarios.isEmpty()){
                usr = usuarios.get(0);
            }
        }

        if (usr.getDni() == null) {

            usr = usr.buscarUsuarioPorUsrAndPass(nombreDeUsuario, password);
            usuarioList.add(usr);
        }

        return usr;
    }
}
