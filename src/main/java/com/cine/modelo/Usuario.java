package com.cine.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.cine.Persistencia.PoolConnection;

public class Usuario {

	private String nombreDeUsuario;
	private String email;
	private String password;
	private String nombre;
	private String domicilio;
	private Integer dni;
	private Date fechaDeNacimiento;
	private Rol rol;

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public ResultSet buscarUsuario() {
		
		PoolConnection poolConnection = PoolConnection.getPoolConnection();
		Connection connection = poolConnection.getConnection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("select * from dbo.Usuarios");
			//preparedStatement.setString(1, dni);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			return resultSet;

		} catch (Exception e) {
			
			System.out.println("Error Query: " + e.getMessage());
			
			return null;
		}
	}
}
