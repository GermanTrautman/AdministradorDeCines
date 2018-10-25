package com.cine.dao;

import com.cine.modelo.Usuario;

import java.sql.*;
import java.util.List;

public class UsuarioPersistente implements Persistencia {


    public Object buscar(Object dni) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Usuario WHERE dni=" + dni);
            Usuario usuario = new Usuario();
            if (resultSet != null) {
                usuario.setDni(resultSet.getInt("dni"));
                usuario.setNombre(resultSet.getString("nombre"));
            }

            return usuario;
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar usuario con dni " + dni);
        } finally {
            cerrarConexion();
        }
    }


    public List<Object> listar() {
        return null;
    }

    public boolean insertar(Object usuario) {
        try {
            Usuario usr = (Usuario) usuario;
            PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO Usuario VALUES (?,?,?,?,?,?,NULL)");
            preparedStatement.setString(1, usr.getNombreDeUsuario());
            preparedStatement.setString(2, usr.getEmail());
            preparedStatement.setString(3, usr.getPassword());
            preparedStatement.setString(4, usr.getNombre());
            preparedStatement.setString(5, usr.getDomicilio());
            preparedStatement.setInt(6, usr.getDni());
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


    public boolean actualizar(Object usuario) {
        return false;
    }

    public boolean borrar(Object dni) {

        try {
            Connection connection = conectarDb();
            Statement statement = connection.createStatement();
            int filasAfectadas =  statement.executeUpdate("DELETE FROM Usuario where dni=" + dni);

            if (filasAfectadas == 1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
