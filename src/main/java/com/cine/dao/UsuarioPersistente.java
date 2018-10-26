package com.cine.dao;

import com.cine.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPersistente implements Persistencia {

    public Object buscar(Object dni) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Usuario WHERE dni=" + dni);
            Usuario usuario = new Usuario();
            if (resultSet.next()) {
                usuario.setDni(resultSet.getInt("DNI"));
                usuario.setNombre(resultSet.getString("NombreDeUsuario"));
                usuario.setNombreDeUsuario(resultSet.getString("Email"));
                usuario.setEmail(resultSet.getString("Nombre"));
                usuario.setDomicilio(resultSet.getString("Domicilio"));
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
        try {

            ResultSet resultSet = ejecutarSelect("SELECT * FROM Usuario");
            List<Object> usuarioList = new ArrayList<>();
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setDni(resultSet.getInt("dni"));
                usuario.setNombreDeUsuario(resultSet.getString("nombreDeUsuario"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setDomicilio(resultSet.getString("domicilio"));
                usuario.setFechaDeNacimiento(resultSet.getDate("fechaDeNacimiento"));
                usuarioList.add(usuario);
            }


            return usuarioList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertar(Object usuario) {
        try {
            Usuario usr = (Usuario) usuario;
            PreparedStatement preparedStatement = conectarDb().prepareStatement(
                    "INSERT INTO Usuario VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1, usr.getDni());
            preparedStatement.setString(2, usr.getNombreDeUsuario());
            preparedStatement.setString(3, usr.getEmail());
            preparedStatement.setString(4, usr.getPassword());
            preparedStatement.setString(5, usr.getNombre());
            preparedStatement.setString(6, usr.getDomicilio());
            preparedStatement.setDate(7, usr.getFechaDeNacimiento());
            int filasAfectadas = preparedStatement.executeUpdate();


            if (filasAfectadas == 1) {
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
        try {
            Usuario usr = (Usuario) usuario;
            PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE Usuario SET NombreDeUsuario=?,Email=?,Password=?,Nombre=?,Domicilio=?,FechaDeNacimiento=? WHERE DNI=" + ((Usuario) usuario).getDni());
            preparedStatement.setString(1, usr.getNombreDeUsuario());
            preparedStatement.setString(2, usr.getEmail());
            preparedStatement.setString(3, usr.getPassword());
            preparedStatement.setString(4, usr.getNombre());
            preparedStatement.setString(5, usr.getDomicilio());
            preparedStatement.setDate(6, usr.getFechaDeNacimiento());
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean borrar(Object dni) {

        try {
            Connection connection = conectarDb();
            Statement statement = connection.createStatement();
            int filasAfectadas = statement.executeUpdate("DELETE FROM Usuario where dni=" + dni);

            if (filasAfectadas == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}