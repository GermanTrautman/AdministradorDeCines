package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

            PreparedStatement preparedStatement = conectarDb().prepareStatement("SELECT * FROM TPO.dbo.Sala WHERE Nombre = ?");
			preparedStatement.setString(1, (String) nombre);
			ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	
            	Integer cuitEstablecimiento = resultSet.getInt("CuitEstablecimiento");
            	Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(cuitEstablecimiento);

            	Estado estado = Estado.valueOf(resultSet.getString("Estado").toUpperCase());
            	
            	sala = new Sala(resultSet.getString("Nombre"), resultSet.getInt("Capacidad"), establecimiento, estado);
            }
            
            return sala;
            
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar sala con nombre " + nombre);
        } finally {
            liberarConexion();
        }
	}

	@Override
	public List<Object> listar() {

		try {
			
            List<Object> salas = new ArrayList<>();
            Sala sala = null;
            
            ResultSet resultSet = ejecutarSelect("SELECT * FROM TPO.dbo.Sala");
            
            while (resultSet.next()) {
            	
            	Integer cuitEstablecimiento = resultSet.getInt("CuitEstablecimiento");
            	Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(cuitEstablecimiento);

            	Estado estado = Estado.valueOf(resultSet.getString("Estado").toUpperCase());
            	
            	sala = new Sala(resultSet.getString("Nombre"), resultSet.getInt("Capacidad"), establecimiento, estado);
            	salas.add(sala);
            }
            
            return salas;
            
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar todos las salas");
        } finally {
        	liberarConexion();
        }
	}

	@Override
	public void insertar(Object objectoSala) {

		try {
			
			Sala sala = (Sala) objectoSala;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.Sala (Nombre, Capacidad, CUITEstablecimiento, Estado) values (?, ?, ?, ?)");
			preparedStatement.setString(1, sala.getNombre());
			preparedStatement.setInt(2, sala.getCapacidad());
			preparedStatement.setInt(3, sala.getEstablecimiento().getCuit());
			preparedStatement.setString(4, sala.getEstado().estado());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			liberarConexion();
		}
	}

	@Override
	public void actualizar(Object objetoSala) {

		try {
			
			Sala sala = (Sala) objetoSala;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE TPO.dbo.Sala SET Capacidad = ?, CUITEstablecimiento = ?, Estado = ? WHERE Nombre = ?");
			preparedStatement.setInt(1, sala.getCapacidad());
			preparedStatement.setInt(2, sala.getEstablecimiento().getCuit());
			preparedStatement.setString(3, sala.getEstado().estado());
			preparedStatement.setString(4, sala.getNombre());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			liberarConexion();
		}
	}

	@Override
	public void borrar(Object objectoSala) {

		try {
			
			Sala sala = (Sala) objectoSala;
            
            PreparedStatement preparedStatement = conectarDb().prepareStatement("DELETE FROM TPO.dbo.Sala where Nombre = ?");
			preparedStatement.setString(1, sala.getNombre());
			preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
			liberarConexion();
		}
	}
}
