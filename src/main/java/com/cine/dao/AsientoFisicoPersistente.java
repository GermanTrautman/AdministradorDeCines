package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cine.modelo.AsientoFisico;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;

public class AsientoFisicoPersistente implements Persistencia {

	@Override
	public Object buscar(Object idSala) {
		
		try {
			
            List<Object> asientosFisicos = new ArrayList<>();
            
            PreparedStatement preparedStatement = conectarDb().prepareStatement("SELECT * FROM TPO.dbo.AsientoFisico WHERE IdSala = ?");
			preparedStatement.setInt(1, (Integer) idSala);
			ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
            	
            	Estado estado = Estado.valueOf(resultSet.getString("Estado").toUpperCase());
            	AsientoFisico asientoFisico = new AsientoFisico(resultSet.getInt("IdSala"), resultSet.getInt("Fila"), resultSet.getInt("NumeroDeAsiento"), estado);
            	
            	asientosFisicos.add(asientoFisico);
            }
            
            return asientosFisicos;
            
        } catch (SQLException e) {
        	System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar asientos de la sala " + idSala);
        } finally {
        	liberarConexion();
        }
	}

	@Override
	public List<Object> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(Object objetoAsientoFisico) {

		try {
			
			@SuppressWarnings("unchecked")
			List<AsientoFisico> asientoFisicos = (List<AsientoFisico>) objetoAsientoFisico;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.AsientoFisico (IdSala, Fila, NumeroDeAsiento, Estado) values (?, ?, ?, ?)");
			preparedStatement.setInt(1, asientoFisicos.get(0).getIdSala());
			preparedStatement.setInt(2, asientoFisicos.get(0).getFila());
			preparedStatement.setInt(3, asientoFisicos.get(0).getNumeroDeAsiento());
			preparedStatement.setString(4, asientoFisicos.get(0).getEstado().estado());
			
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
