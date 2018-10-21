package com.cine.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.cine.modelo.Establecimiento;

public class EstablecimientoPersistente implements Persistencia {
	
	@Override
	public Object buscar(Integer cuit) {
		
		try {
			
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Establecimiento WHERE CUIT=" + cuit);

            Establecimiento establecimiento = null;
            
            if (resultSet != null) {
            	establecimiento = new Establecimiento(resultSet.getInt("CUIT"), resultSet.getString("Nombre"), resultSet.getString("Domicilio"), resultSet.getInt("Capacidad"));
            }
            
            return establecimiento;
            
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar usuario con dni " + cuit);
        } finally {
            cerrarConexion();
        }
	}
	
	@Override
	public List<Object> listar() {
		return null;
	}

	@Override
	public boolean insertar(Object objetoEstablecimiento) {
		
		try {
			
			Establecimiento establecimiento = (Establecimiento) objetoEstablecimiento;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.establecimiento (CUIT, Nombre, Domicilio, Capacidad) values (?, ?, ?, ?)");
			preparedStatement.setInt(1, establecimiento.getCuit());
			preparedStatement.setString(2, establecimiento.getNombre());
			preparedStatement.setString(3, establecimiento.getDomicilio());
			preparedStatement.setInt(4, establecimiento.getCapacidad());
			
			preparedStatement.executeUpdate();
			
			int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 0) {
                return true;
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return false;
	}

	@Override
	public boolean actualizar(Object objetoEstablecimiento) {

		try {
			
			Establecimiento establecimiento = (Establecimiento) objetoEstablecimiento;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE TPO.dbo.establecimiento SET Nombre = ?, Domicilio = ?, Capacidad = ? WHERE CUIT = ?");
			preparedStatement.setString(1, establecimiento.getNombre());
			preparedStatement.setString(2, establecimiento.getDomicilio());
			preparedStatement.setInt(3, establecimiento.getCapacidad());
			preparedStatement.setInt(4, establecimiento.getCuit());
			
			preparedStatement.executeUpdate();
			
			int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 0) {
                return true;
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return false;
	}

	@Override
	public boolean borrar(Integer cuit) {

        try {
        	
            Connection connection = conectarDb();
            Statement statement = connection.createStatement();
            int filasAfectadas =  statement.executeUpdate("DELETE FROM Establecimiento where CUIT=" + cuit);

            if (filasAfectadas == 1){
                return true;
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
	}
}
