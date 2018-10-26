package com.cine.dao;

import com.cine.modelo.Rol;
import com.cine.modelo.Usuario;
import com.cine.utilidades.Estado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolPersistente implements Persistencia {

    public Rol buscar(Object nombre) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Rol WHERE Nombre=" + nombre);
            Rol rol = (Rol) Class.forName(resultSet.getString("Nombre")).newInstance();
            if (resultSet.next()) {
                rol.setNombre(resultSet.getString("Nombre"));
                rol.setEstado(Enum.valueOf(Estado.class, resultSet.getString("Estado")));
            }

            return rol;
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar usuario con dni " + nombre);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            cerrarConexion();
        }
    }


    public List<Object> listar() {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Rol");
            List<Object> rolList = new ArrayList<>();
            while (resultSet.next()) {
                Rol rol = (Rol) Class.forName(resultSet.getString("Nombre")).newInstance();
                rol.setNombre(resultSet.getString("Nombre"));
                rol.setNombre(resultSet.getString("Estado"));
                rolList.add(rol);
            }
            return rolList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertar(Object rol) {
        try {
            Rol aRol = (Rol) rol;
            PreparedStatement preparedStatement = conectarDb().prepareStatement(
                    "INSERT INTO Rol VALUES (?,?)");
            preparedStatement.setString(1, aRol.getNombre());
            preparedStatement.setString(2, aRol.getEstado().estado());
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


    public boolean actualizar(Object rol) {
        try {
            Rol aRol = (Rol) rol;
            PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE Rol SET Nombre=?,Estado=? WHERE Nombre=" + ((Rol) rol).getNombre());
            preparedStatement.setString(1, aRol.getNombre());
            preparedStatement.setString(2, aRol.getEstado().estado());
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean borrar(Object nombre) {

        try {
            Connection connection = conectarDb();
            Statement statement = connection.createStatement();
            int filasAfectadas = statement.executeUpdate("DELETE FROM Rol where Nombre=" + nombre);

            if (filasAfectadas == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}