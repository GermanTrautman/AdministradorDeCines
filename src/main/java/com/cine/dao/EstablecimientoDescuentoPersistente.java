package com.cine.dao;

import com.cine.modelo.Descuento;
import com.cine.modelo.DescuentoTipo;
import com.cine.modelo.EstablecimientoDescuento;
import com.cine.utilidades.DescuentoProvider;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstablecimientoDescuentoPersistente implements Persistencia {

    public static EstablecimientoDescuentoPersistente instancia;

    public EstablecimientoDescuentoPersistente() {
    }

    public static EstablecimientoDescuentoPersistente getInstance() {
        if (instancia == null) {
            instancia = new EstablecimientoDescuentoPersistente();
        }
        return instancia;
    }


    @Override
    public void insertar(Object establecimientoDescuento) {
        try {
            EstablecimientoDescuento establDesc = (EstablecimientoDescuento) establecimientoDescuento;
            PreparedStatement preparedStatement = conectarDb().prepareStatement(
                    "INSERT INTO EstablecimientoDescuento VALUES (?,?,?,?)");
            preparedStatement.setInt(1, establDesc.getCuitEstablecimiento());
            preparedStatement.setInt(2, establDesc.getIdDescuento());
            preparedStatement.setDate(3, Date.valueOf(establDesc.getVigenciaInicio()));
            preparedStatement.setDate(4, Date.valueOf(establDesc.getVigenciaFin()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion();

        }
    }

    @Override
    public Object buscar(Object key) {
        return null;
    }

    @Override
    public List<Object> listar() {
        return null;
    }

    @Override
    public void actualizar(Object entidad) {

    }

    @Override
    public void borrar(Object key) {

    }


    public List<Descuento> obtenerDescuentosPorEstablecimiento(Integer cuitEstablecimiento) {

        try {

            List<Descuento> descuentos = new ArrayList<>();

            PreparedStatement preparedStatement = conectarDb().prepareStatement("SELECT * FROM TPO.dbo.EstablecimientoDescuento_vw WHERE CUITEstablecimiento =" + cuitEstablecimiento);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Descuento descuento = new Descuento();
                descuento.setNombre(resultSet.getString("NombreDescuento"));
                descuento.setIdDescuento(resultSet.getInt("IdDescuento"));
                descuento.setPorcentaje(resultSet.getInt("Porcentaje"));
                descuento.setTipo(DescuentoTipo.valueOf(resultSet.getString("Tipo")));
                descuentos.add(descuento);
            }

            return descuentos;

        } catch (SQLException e) {

            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar el establecimiento con cuit" + cuitEstablecimiento);

        } finally {
            liberarConexion();
        }
    }

}