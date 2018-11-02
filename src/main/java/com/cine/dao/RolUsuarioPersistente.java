package com.cine.dao;

import com.cine.modelo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolUsuarioPersistente implements Persistencia {

    public static RolUsuarioPersistente instancia;

    public RolUsuarioPersistente() {
    }

    public static RolUsuarioPersistente getInstance() {
        if (instancia == null) {
            instancia = new RolUsuarioPersistente();
        }
        return instancia;
    }

    @Override
    public RolUsuario buscar(Object dni) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM RolUsuario WHERE IdUsuario=" + dni);
            RolUsuario rolUsuario = new RolUsuario();
            if (resultSet.next()) {
                rolUsuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                rolUsuario.setIdRol(resultSet.getInt("IdRol"));
            }

            return rolUsuario;
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

            ResultSet resultSet = ejecutarSelect("SELECT * FROM RolUsuario");
            List<Object> usuarioList = new ArrayList<>();
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setDni(resultSet.getInt("dni"));
                usuario.setNombreDeUsuario(resultSet.getString("nombreDeUsuario"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setDomicilio(resultSet.getString("domicilio"));
                usuario.setFechaDeNacimiento(resultSet.getString("fechaDeNacimiento"));
                usuarioList.add(usuario);
            }


            return usuarioList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertar(Object rolUsuario) {
        try {
            RolUsuario rolUsr = (RolUsuario) rolUsuario;
            PreparedStatement preparedStatement = conectarDb().prepareStatement(
                    "INSERT INTO RolUsuario VALUES (?,?)");
            preparedStatement.setInt(1, ((RolUsuario) rolUsuario).getIdUsuario());
            preparedStatement.setInt(2, rolUsr.getIdRol());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();

        }
    }

    @Override
    public void actualizar(Object rolUsr) {
        try {
            RolUsuario usrRol = (RolUsuario) rolUsr;
            PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE RolUsuario SET IdUsuario=?,IdRol=? WHERE idUsuario=" + usrRol.getIdUsuario());
            preparedStatement.setInt(1, usrRol.getIdUsuario());
            preparedStatement.setInt(2, usrRol.getIdRol());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer buscarPorNombreRol(String nombre) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Rol WHERE Nombre='" + nombre + "'");

            switch (nombre) {
                case "Cliente":
                    Rol cliente = new Cliente();
                    if (resultSet.next()) {
                        cliente.setId(resultSet.getInt("Id"));
                        return cliente.getId();
                    }
                case "Operador":
                    Rol operador = new Operador();
                    if (resultSet.next()) {
                        operador.setId(resultSet.getInt("Id"));
                        return operador.getId();
                    }
                case "Administrador":
                    Rol administrador = new Administrador();
                    if (resultSet.next()) {
                        administrador.setId(resultSet.getInt("Id"));
                        return administrador.getId();
                    }
                case "Agente Comercial":
                    Rol agente = new AgenteComercial();
                    if (resultSet.next()) {
                        agente.setId(resultSet.getInt("Id"));
                        return agente.getId();
                    }
                case "Vendedor Boleteria":
                    Rol vendedor = new VendedorBoleteria();
                    if (resultSet.next()) {
                        vendedor.setId(resultSet.getInt("Id"));
                        return vendedor.getId();
                    }
            }

            return 0;
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar usuario con dni " + nombre);
        } finally {
            cerrarConexion();
        }
    }


    public Rol buscarPorIdRol(Integer rolId) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Rol WHERE ID=" + rolId);

            if (resultSet.next()) {

                switch (resultSet.getString("Nombre")) {
                    case "Cliente":
                        Rol cliente = new Cliente();
                        if (resultSet.next()) {
                            cliente.setId(resultSet.getInt("Id"));
                            cliente.setNombre(resultSet.getString("Nombre"));
                            return cliente;
                        }
                    case "Operador":
                        Rol operador = new Operador();
                        if (resultSet.next()) {
                            operador.setId(resultSet.getInt("Id"));
                            operador.setNombre(resultSet.getString("Nombre"));
                            return operador;
                        }
                    case "Administrador":
                        Rol administrador = new Administrador();
                            administrador.setId(resultSet.getInt("Id"));
                            administrador.setNombre(resultSet.getString("Nombre"));
                            return administrador;
                    case "Agente Comercial":
                        Rol agente = new AgenteComercial();
                            agente.setId(resultSet.getInt("Id"));
                            agente.setNombre(resultSet.getString("Nombre"));
                            return agente;
                    case "Vendedor Boleteria":
                        Rol vendedor = new VendedorBoleteria();
                        if (resultSet.next()) {
                            vendedor.setId(resultSet.getInt("Id"));
                            vendedor.setNombre(resultSet.getString("Nombre"));
                            return vendedor;
                        }
                }
            }

            return null;
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar usuario con rol id= " + rolId);
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public void borrar(Object dni) {

        try {
            Connection connection = conectarDb();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM RolUsuario where IdUsuario=" + dni);
            System.out.println("RolUsuario borrado correctamente de la db con dni=" + dni);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}