package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.cine.modelo.AsientoVirtual;
import com.cine.modelo.Venta;

public class VentaAsientoPersistente implements Persistencia {
	
	 public static VentaAsientoPersistente instancia;

    public VentaAsientoPersistente() {
    }

    public static VentaAsientoPersistente getInstance() {
    	
        if (instancia == null) {
        	
            instancia = new VentaAsientoPersistente();
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

	public void insertar(Venta venta, AsientoVirtual asientoVirtual) {
		
		try {
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.VentaTarjeta (IdVenta, IdAsientoVirtual) values (?, ?)");
			preparedStatement.setInt(1, venta.getId());
			preparedStatement.setInt(2, asientoVirtual.getId());
			
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
