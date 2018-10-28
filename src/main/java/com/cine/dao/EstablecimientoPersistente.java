package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cine.modelo.Establecimiento;

public class EstablecimientoPersistente implements Persistencia {
	
	@Override
	public Object buscar(Object cuit) {
		
		try {
			
            Establecimiento establecimiento = null;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("SELECT * FROM TPO.dbo.Establecimiento WHERE CUIT=?");
			preparedStatement.setInt(1, (int) cuit);
			ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	establecimiento = new Establecimiento(resultSet.getInt("CUIT"), resultSet.getString("Nombre"), resultSet.getString("Domicilio"), resultSet.getInt("Capacidad"));
            }
            
            return establecimiento;
            
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar establecimiento con cuit " + cuit);
        } finally {
            cerrarConexion();
        }
	}
	
	@Override
	public List<Object> listar() {

		try {
			
            List<Object> establecimientos = new ArrayList<>();
            Establecimiento establecimiento = null;
            
            ResultSet resultSet = ejecutarSelect("SELECT * FROM TPO.dbo.Establecimiento");
            
            while (resultSet.next()) {
            	
            	establecimiento = new Establecimiento(resultSet.getInt("CUIT"), resultSet.getString("Nombre"), resultSet.getString("Domicilio"), resultSet.getInt("Capacidad"));
            	establecimientos.add(establecimiento);
            }
            
            return establecimientos;
            
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar todos los establecimientos");
        } finally {
            cerrarConexion();
        }
	}

	@Override
	public void insertar(Object objetoEstablecimiento) {
		
		try {
			
			Establecimiento establecimiento = (Establecimiento) objetoEstablecimiento;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.establecimiento (CUIT, Nombre, Domicilio, Capacidad) values (?, ?, ?, ?)");
			preparedStatement.setInt(1, establecimiento.getCuit());
			preparedStatement.setString(2, establecimiento.getNombre());
			preparedStatement.setString(3, establecimiento.getDomicilio());
			preparedStatement.setInt(4, establecimiento.getCapacidad());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
	}

	@Override
	public void actualizar(Object objetoEstablecimiento) {

		try {
			
			Establecimiento establecimiento = (Establecimiento) objetoEstablecimiento;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE TPO.dbo.establecimiento SET Nombre = ?, Domicilio = ?, Capacidad = ? WHERE CUIT = ?");
			preparedStatement.setString(1, establecimiento.getNombre());
			preparedStatement.setString(2, establecimiento.getDomicilio());
			preparedStatement.setInt(3, establecimiento.getCapacidad());
			preparedStatement.setInt(4, establecimiento.getCuit());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
	}

	@Override
	public void borrar(Object cuit) {

        try {
        	
        	PreparedStatement preparedStatement = conectarDb().prepareStatement("DELETE FROM TPO.dbo.Establecimiento WHERE CUIT=?");
			preparedStatement.setInt(1, (int) cuit);
			preparedStatement.executeUpdate();
            
        }catch (SQLException e){
            e.printStackTrace();
        }
	}
}
