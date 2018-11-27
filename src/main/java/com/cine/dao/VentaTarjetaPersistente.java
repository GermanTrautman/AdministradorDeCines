package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.cine.modelo.Venta;

public class VentaTarjetaPersistente implements Persistencia {
	
	 public static VentaTarjetaPersistente instancia;

	 public VentaTarjetaPersistente() {
	 }

	 public static VentaTarjetaPersistente getInstance() {
	    	
		 if (instancia == null) {
			
		    instancia = new VentaTarjetaPersistente();
		 }
		
		return instancia;
	 }

	@Override
	public Object buscar(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insertar(Object entidad) {
		// TODO Auto-generated method stub
		
	}

	public void insertar(Venta venta) {

		try {
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.VentaTarjeta (IdVenta, IdTarjeta) values (?, ?)");
			preparedStatement.setInt(1, venta.getId());
			preparedStatement.setInt(2, venta.getTarjeta().getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		
		} finally {
		
			liberarConexion();
		}
	}

	@Override
	public void actualizar(Object entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Object key) {
		// TODO Auto-generated method stub
		
	}
}
