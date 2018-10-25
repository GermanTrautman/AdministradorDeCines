package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;

public class SalaPersistente implements Persistencia {

	@Override
	public Object buscar(Object nombre) {
		
		try {
			
            Sala sala = null;

            ResultSet resultSet = ejecutarSelect("SELECT * FROM TPO.dbo.Sala WHERE Nombre=" + "'" + nombre + "'");
            
            if (resultSet.next()) {
            	
            	Integer cuitEstablecimiento = resultSet.getInt("CuitEstablecimiento");
            	Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(cuitEstablecimiento);

            	Estado estado = Estado.valueOf(resultSet.getString("Estado"));
            	
            	sala = new Sala(resultSet.getString("Nombre"), resultSet.getInt("Capacidad"), establecimiento, estado);
            }
            
            return sala;
            
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar sala con nombre " + nombre);
        } finally {
            cerrarConexion();
        }
	}

	@Override
	public List<Object> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(Object objectoSala) {

		try {
			
			Sala sala = (Sala) objectoSala;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.Sala (Nombre, Capacidad, CUITEstablecimiento, Estado) values (?, ?, ?, ?)");
			preparedStatement.setString(1, sala.getNombre());
			preparedStatement.setInt(2, sala.getCapacidad());
			preparedStatement.setInt(3, sala.getEstablecimiento().getCuit());
			preparedStatement.setString(4, sala.getEstado().estado());
			
			preparedStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		return false;
	}

	@Override
	public boolean actualizar(Object entidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrar(Object key) {
		// TODO Auto-generated method stub
		return false;
	}
}
