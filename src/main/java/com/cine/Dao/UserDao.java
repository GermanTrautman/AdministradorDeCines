package com.cine.Dao;

import com.cine.modelo.Usuario;

import java.util.List;

public interface UserDao {

    Usuario buscarUsuario(Integer dni);

    List<Usuario> listarUsuarios();

    boolean insertarUsuario(Usuario usuario);

    boolean actualizarUsuario(Usuario usuario);

    boolean borrarUsuario();
}
