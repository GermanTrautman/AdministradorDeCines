package com.cine.controlador;

import com.cine.modelo.Cliente;

public class ControladorUsuario {

	public void altaUsuario(String nombreDeUsuario, String email, String password, String nombre, String domicilio, String dni, String fechaDeNacimiento) {
		
		Cliente cliente = new Cliente();
		cliente.setNombreDeUsuario(nombreDeUsuario);
		cliente.setEmail(email);
		cliente.setPassword(password);
		cliente.setNombre(nombre);
		cliente.setDomicilio(domicilio);
		
		
	}
}
