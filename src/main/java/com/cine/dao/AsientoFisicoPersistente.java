package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cine.modelo.AsientoFisico;
import com.cine.utilidades.Estado;

public class AsientoFisicoPersistente implements Persistencia {

	@Override
	public Object buscar(Object idSala) {
		
		try {
			
            AsientoFisico asientoFisico = null;

            PreparedStatement preparedStatement = conectarDb().prepareStatement("SELECT * FROM TPO.dbo.AsientoFisico WHERE IdSala = ?");
			preparedStatement.setInt(1, (Integer) idSala);
			ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	
            	Estado estado = Estado.valueOf(resultSet.getString("Estado").toUpperCase());
            	asientoFisico = new AsientoFisico(resultSet.getInt("Fila"), resultSet.getInt("NumeroDeAsiento"), estado);
            }
            
            return asientoFisico;
            
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
	public void insertar(Object entidad) {
		// TODO Auto-generated method stub
		
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
