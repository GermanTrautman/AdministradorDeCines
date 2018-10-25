package com.cine.controlador;

import com.cine.dao.Cache;
import com.cine.dao.UsuarioPersistente;
import com.cine.modelo.Usuario;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ControladorUsuario implements Cache {

    private static final ControladorUsuario controladorUsuario = new ControladorUsuario();

    private List<Usuario> usuarioList = new ArrayList<>();

    private UsuarioPersistente usuarioPersistente = new UsuarioPersistente();

    public static ControladorUsuario getInstance() {
        return controladorUsuario;
    }


    public void altaUsuario(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, Date fechaDeNacimiento) {

        if (buscarEnCache(dni) == null){

            Usuario usuario = new Usuario(dni, nombreDeUsuario, email, password, nombre, domicilio, fechaDeNacimiento);
            usuarioList.add(usuario);
            usuarioPersistente.insertar(usuario);
        }else {
            throw new RuntimeException("No se permiten usuarios duplicados");
        }
    }


    public void bajaUsuario(Integer dni) {

        borrarDeCache(dni);

        if (usuarioPersistente.buscar(dni) != null) {
            usuarioPersistente.borrar(dni);
        }
    }

    public void modificacionUsuario(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, Date fechaDeNacimiento) {

        Usuario usuario = new Usuario(dni,nombreDeUsuario,email,password,nombre,domicilio,fechaDeNacimiento);

        actualizarCache(usuario);
        usuarioPersistente.actualizar(usuario);
    }


    @Override
    public Object buscarEnCache(Integer key) {
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
    public void borrarDeCache(Integer key) {
        this.usuarioList.removeIf(usuario -> usuario.getDni().equals(key));

    }

    @Override
    public void actualizarCache(Object entidad) {
        Usuario usuario = (Usuario) entidad;
        borrarDeCache(usuario.getDni());
        this.usuarioList.add(usuario);
    }

    public void obtenerUsuariosDb() {
        usuarioList = (List<Usuario>) (Object) usuarioPersistente.listar();
    }

    public List<Usuario> getUsuarios(){
        return usuarioList;
    }
}
