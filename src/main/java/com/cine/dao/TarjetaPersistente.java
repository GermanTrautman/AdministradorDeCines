package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cine.modelo.Tarjeta;
import com.cine.utilidades.Banco;
import com.cine.utilidades.ConversorDeTipos;

public class TarjetaPersistente implements Persistencia {
	
	public static TarjetaPersistente instancia;
	
    public TarjetaPersistente() {
    }

    public static TarjetaPersistente getInstance() {
    	
        if (instancia == null) {
        
        	instancia = new TarjetaPersistente();
        }
        
        return instancia;
    }

	@Override
	public Object buscar(Object objetoNumero) {

		try {
			
			Long numero = (Long) objetoNumero;
			
            Tarjeta tarjeta = null;

            PreparedStatement preparedStatement = conectarDb().prepareStatement("SELECT * FROM TPO.dbo.Tarjeta WHERE Numero = ?");
			preparedStatement.setLong(1, numero);
			ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	
            	Banco banco = Banco.valueOf(resultSet.getString("BancoEmisor").toUpperCase());
            	
            	tarjeta = new Tarjeta(banco, resultSet.getLong("Numero"), resultSet.getDate("FechaDeVencimiento"));
            	tarjeta.setId(resultSet.getInt("Id"));
            }
            
            return tarjeta;
            
        } catch (SQLException e) {
        	
            System.out.println("Error Query: " + e.getMessage());
            
            throw new RuntimeException("Error intentando buscar tarjeta con numero " + objetoNumero);
        
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
	public void insertar(Object objetoTarjeta) {

		try {
			
			Tarjeta tarjeta = (Tarjeta) objetoTarjeta;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.Tarjeta (BancoEmisor, Numero, FechaDeVencimiento) VALUES (?, ?, ?)");
			preparedStatement.setString(1, tarjeta.getBanco().getLabel());
			preparedStatement.setLong(2, tarjeta.getNumero());
			preparedStatement.setDate(3, new ConversorDeTipos().deJavaDateToSqlDate(tarjeta.getMesYAnioDeVencimiento()));
			
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
