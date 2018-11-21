package com.cine.dao;

import com.cine.modelo.Descuento;
import com.cine.modelo.DescuentoTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DescuentoPersistente implements Persistencia {

    public static DescuentoPersistente instancia;

    public DescuentoPersistente() {
    }

    public static DescuentoPersistente getInstance() {
        if (instancia == null) {
            instancia = new DescuentoPersistente();
        }
        return instancia;
    }

    @Override
    public Object buscar(Object nombreDescuento) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Descuento WHERE Nombre='" + nombreDescuento+"'");
            return construirDescuento(resultSet);
        } catch (SQLException e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar descuento con nombre " + nombreDescuento);
        } finally {
            cerrarConexion();
        }
    }

    @Override
    public List<Object> listar() {
        try {

            ResultSet resultSet = ejecutarSelect("SELECT * FROM Descuento");
            List<Object> descuentosList = new ArrayList<>();
            while (resultSet.next()) {
                descuentosList.add(construirDescuento(resultSet));
            }

            return descuentosList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertar(Object descuento) {
        try {
            Descuento desc = (Descuento) descuento;
            PreparedStatement preparedStatement = conectarDb().prepareStatement(
                    "INSERT INTO Descuento VALUES (?,?,?,?)");
            construirDescuento(desc, preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();

        }
    }

    @Override
    public void actualizar(Object descuento) {
        try {
            Descuento desc = (Descuento) descuento;
            PreparedStatement preparedStatement = conectarDb().prepareStatement("UPDATE Descuento SET Nombre=?,Porcentaje=?,Cantidad=?,Tipo=? WHERE nombre=" + ((Descuento) descuento).getNombre());
            construirDescuento(desc, preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void construirDescuento(Descuento desc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, desc.getNombre());
        preparedStatement.setInt(2, desc.getPorcentaje());
        preparedStatement.setInt(3, desc.getCantidad());
        preparedStatement.setString(4, desc.getTipo().toString());
        preparedStatement.executeUpdate();
    }

    @Override
    public void borrar(Object nombre) {

        try {
            Connection connection = conectarDb();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Descuento where Nombre=" + nombre);
            System.out.println("Descuento con nombre " + nombre + " borrado correctamente de la db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Descuento construirDescuento(ResultSet resultSet) throws SQLException {
        Descuento descuento = new Descuento();
        if (resultSet.next()) {
            descuento.setIdDescuento(resultSet.getInt("Id"));
            descuento.setNombre(resultSet.getString("Nombre"));
            descuento.setPorcentaje(resultSet.getInt("Porcentaje"));
            descuento.setCantidad(resultSet.getInt("Cantidad"));
            descuento.setTipo(DescuentoTipo.valueOf(resultSet.getString("Tipo")));
        }

        return descuento;
    }
}