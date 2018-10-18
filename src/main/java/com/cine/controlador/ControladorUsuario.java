package com.cine.controlador;

import com.cine.modelo.Usuario;

public class ControladorUsuario {

	public void altaUsuario(String nombreDeUsuario, String email, String password, String nombre, String domicilio, String dni, String fechaDeNacimiento) {
		
		Usuario usuario = new Usuario();
		usuario.setNombreDeUsuario(nombreDeUsuario);
		usuario.setEmail(email);
		usuario.setPassword(password);
		usuario.setNombre(nombre);
		usuario.setDomicilio(domicilio);
		
		usuario.buscarUsuario();
	}
}
