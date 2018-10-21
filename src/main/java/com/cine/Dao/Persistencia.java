package com.cine.Dao;

import com.cine.Persistencia.PoolConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Persistencia {

    Object buscar(Integer key);

    List<Object> listar();

    boolean insertar(Object entidad);

    boolean actualizar(Object entidad);

    boolean borrar(Integer key);

    default Connection conectarDb() {
    	
        PoolConnection pool = PoolConnection.getPoolConnection();
        
        return pool.getConnection();
    }

    default ResultSet ejecutarSelect(String query) {
        
    	try {
            Connection connection = conectarDb();
            return connection.createStatement().executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();
        }
        
        return null;
    }

    default void cerrarConexion() {
        try {
            conectarDb().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
