package com.cine.dao;

import com.cine.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPersistente implements Persistencia {

    public static UsuarioPersistente instancia;

    public UsuarioPersistente() {
    }

    public static UsuarioPersistente getInstance() {
        if (instancia == null) {
            instancia = new UsuarioPersistente();
        }
        return instancia;
    }

    @Override
    public Object buscar(Object dni) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Usuario WHERE dni=" + dni);
            return construirUsuario(resultSet);
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
                usuarioList.add(construirUsuario(resultSet));
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
            preparedStatement.setString(7, usr.getFechaDeNacimiento());
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
            PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE Usuario SET NombreDeUsuario=?,Email=?,Password=?,Nombre=?,Domicilio=?,FechaDeNacimiento=? WHERE dni=" + ((Usuario) usuario).getDni());
            preparedStatement.setString(1, usr.getNombreDeUsuario());
            preparedStatement.setString(2, usr.getEmail());
            preparedStatement.setString(3, usr.getPassword());
            preparedStatement.setString(4, usr.getNombre());
            preparedStatement.setString(5, usr.getDomicilio());
            preparedStatement.setString(6, usr.getFechaDeNacimiento());
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
            System.out.println("Usuario con dni " + dni + " borrado correctamente de la db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarUsuarioPorUsrAndPass(String nombreDeUsuario, String password) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Usuario WHERE NombreDeUsuario='" + nombreDeUsuario + "'" + "and Password='" + password + "'");
            return construirUsuario(resultSet);
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar usuario con usr " + nombreDeUsuario);
        } finally {
            cerrarConexion();
        }
    }

    private Usuario construirUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        if (resultSet.next()) {
            usuario.setDni(resultSet.getInt("DNI"));
            usuario.setNombre(resultSet.getString("NombreDeUsuario"));
            usuario.setNombreDeUsuario(resultSet.getString("NombreDeUsuario"));
            usuario.setPassword(resultSet.getString("Password"));
            usuario.setEmail(resultSet.getString("Email"));
            usuario.setDomicilio(resultSet.getString("Domicilio"));
            usuario.setFechaDeNacimiento(resultSet.getString("FechaDeNacimiento"));
        }

        return usuario;
    }
}