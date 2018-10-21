package com.cine.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.cine.modelo.Establecimiento;

public class EstablecimientoPersistente implements Persistencia {

	private Establecimiento buscarEnMemoria(List<Establecimiento> establecimientos, Establecimiento establecimientoBuscado) {

		Establecimiento establecimientoEncontrado = null;
		
		for (Establecimiento establecimiento : establecimientos) {
			
			if (establecimiento.getCuit().equals(establecimientoBuscado.getCuit())) {
				establecimientoEncontrado = establecimiento;
			}
		}
		
		return establecimientoEncontrado;
	}
	
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

	//@SuppressWarnings("unchecked")
	//@Override
	public boolean insertar(List<Establecimiento> establecimientos, Object objetoEstablecimiento) {
		
		boolean fueInsertado = false;
		
		Establecimiento establecimiento = (Establecimiento) objetoEstablecimiento;
		
		Establecimiento establecimientoEncontradoEnMemoria = buscarEnMemoria(establecimientos, establecimiento);
		
		if (establecimientoEncontradoEnMemoria == null) {
			
			Establecimiento establecimientoEncontradoEnLaBase = (Establecimiento) buscar(establecimiento.getCuit());
			
			if (establecimientoEncontradoEnLaBase == null) {
				
				insertarEnMemoria(establecimientos, establecimiento);
				insertarEnLaBase(establecimientos, establecimiento);
			
			} else {
				insertarEnMemoria(establecimientos, establecimientoEncontradoEnLaBase);
			}
			
			fueInsertado = true;
		}

        return fueInsertado;
	}

	private void insertarEnMemoria(List<Establecimiento> establecimientos, Establecimiento establecimiento) {
		establecimientos.add(establecimiento);
	}
	
	private void insertarEnLaBase(List<Establecimiento> establecimientos, Establecimiento establecimiento) {
		
		try {
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("insert into TPO.dbo.establecimiento (CUIT, Nombre, Domicilio, Capacidad) values (?, ?, ?, ?)");
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
	public List<Object> listar() {
		return null;
	}

	@Override
	public boolean actualizar(Object entidad) {
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
