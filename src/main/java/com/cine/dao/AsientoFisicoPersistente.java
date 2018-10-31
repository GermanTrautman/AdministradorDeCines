package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cine.modelo.AsientoFisico;
import com.cine.utilidades.Estado;

public class AsientoFisicoPersistente implements Persistencia {

	@Override
	public Object buscar(Object nombreSala) {
		
		try {
			
			AsientoFisico[][] asientosFisicos = new AsientoFisico[25][25];
            
            PreparedStatement preparedStatement = conectarDb().prepareStatement("SELECT * FROM TPO.dbo.AsientoFisico WHERE NombreSala = ?");
			preparedStatement.setString(1, (String) nombreSala);
			ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
            	
            	Estado estado = Estado.valueOf(resultSet.getString("Estado").toUpperCase());
            	AsientoFisico asientoFisico = new AsientoFisico(resultSet.getString("NombreSala"), resultSet.getInt("Fila"), resultSet.getInt("NumeroDeAsiento"), estado);
            	
            	asientosFisicos[asientoFisico.getFila()][asientoFisico.getNumeroDeAsiento()] = asientoFisico;
            }
            
            return asientosFisicos;
            
        } catch (SQLException e) {
        	System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar asientos de la sala " + nombreSala);
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
			
			AsientoFisico asientoFisico = (AsientoFisico) objetoAsientoFisico;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.AsientoFisico (NombreSala, Fila, NumeroDeAsiento, Estado) values (?, ?, ?, ?)");
			preparedStatement.setString(1, asientoFisico.getNombreSala());
			preparedStatement.setInt(2, asientoFisico.getFila());
			preparedStatement.setInt(3, asientoFisico.getNumeroDeAsiento());
			preparedStatement.setString(4, asientoFisico.getEstado().estado());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			liberarConexion();
		}
	}

	@Override
	public void actualizar(Object objetoAsientoFisico) {

		try {
			
			AsientoFisico asientoFisico = (AsientoFisico) objetoAsientoFisico;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE TPO.dbo.AsientoFisico SET Estado = ? WHERE NombreSala = ? AND Fila = ? AND NumeroDeAsiento = ?");
			preparedStatement.setString(1, asientoFisico.getEstado().estado());
			preparedStatement.setString(2, asientoFisico.getNombreSala());
			preparedStatement.setInt(3, asientoFisico.getFila());
			preparedStatement.setInt(4, asientoFisico.getNumeroDeAsiento());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			liberarConexion();
		}
	}

	@Override
	public void borrar(Object objectoAsientoFisico) {

		try {
			
			AsientoFisico asientoFisico = (AsientoFisico) objectoAsientoFisico;
            
            PreparedStatement preparedStatement = conectarDb().prepareStatement("DELETE FROM TPO.dbo.AsientoFisico WHERE NombreSala = ? AND Fila = ? AND NumeroDeAsiento = ?");
            preparedStatement.setString(1, asientoFisico.getNombreSala());
			preparedStatement.setInt(2, asientoFisico.getFila());
			preparedStatement.setInt(3, asientoFisico.getNumeroDeAsiento());
			preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
			liberarConexion();
		}
	}
}
