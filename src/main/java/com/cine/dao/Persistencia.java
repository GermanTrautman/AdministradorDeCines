package com.cine.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cine.persistencia.PoolConnection;

public interface Persistencia {

    Object buscar(Object key);

    List<Object> listar();

    void insertar(Object entidad);

    void actualizar(Object entidad);

    void borrar(Object key);

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
