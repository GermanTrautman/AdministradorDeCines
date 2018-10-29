package com.cine.dao;

import com.cine.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPersistente implements Persistencia {

    public static UsuarioPersistente instancia;

    public UsuarioPersistente() {
    }

    public static UsuarioPersistente getInstance(){
        if (instancia == null){
            instancia = new UsuarioPersistente();
        }
        return instancia;
    }

    @Override
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

    @Override
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

    @Override
    public void insertar(Object usuario) {
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
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();

        }
    }

    @Override
    public void actualizar(Object usuario) {
        try {
            Usuario usr = (Usuario) usuario;
            PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE Usuario SET NombreDeUsuario=?,Email=?,Password=?,Nombre=?,Domicilio=?,FechaDeNacimiento=?");
            preparedStatement.setString(1, usr.getNombreDeUsuario());
            preparedStatement.setString(2, usr.getEmail());
            preparedStatement.setString(3, usr.getPassword());
            preparedStatement.setString(4, usr.getNombre());
            preparedStatement.setString(5, usr.getDomicilio());
            preparedStatement.setDate(6, usr.getFechaDeNacimiento());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrar(Object dni) {

        try {
            Connection connection = conectarDb();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Usuario where dni=" + dni);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}