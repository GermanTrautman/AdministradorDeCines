package com.cine.controlador;

import com.cine.Dao.UsuarioPersistente;
import com.cine.modelo.Usuario;

import java.util.Date;

public class ControladorUsuario {

    private UsuarioPersistente usuarioPersistente;

    public void altaUsuario(String nombreDeUsuario, String email, String password, String nombre, String domicilio, Integer dni, Date fechaDeNacimiento) {
        Usuario usuario = new Usuario(nombreDeUsuario,email,password,nombre,domicilio,dni,fechaDeNacimiento);
    }
}
