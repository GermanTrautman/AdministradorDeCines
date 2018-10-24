package com.cine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cine.modelo.Pelicula;
import com.cine.utilidades.EstadoActivoInactivo;

public class PeliculaPersistente implements Persistencia {

	public Object buscar(String nombre) {
		try {

			Pelicula pelicula = null;

			PreparedStatement preparedStatement = conectarDb()
					.prepareStatement("SELECT * FROM Pelicula WHERE Nombre= ?");
			preparedStatement.setString(1, nombre);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				EstadoActivoInactivo estadoPelicula;
				if (resultSet.getInt("Estado") == 1) {
					estadoPelicula = EstadoActivoInactivo.ACTIVO;
				} else
					estadoPelicula = EstadoActivoInactivo.INACTIVO;

				pelicula = new Pelicula(resultSet.getString("Nombre"), resultSet.getString("Director"),
						resultSet.getString("Genero"), resultSet.getInt("Duracion"), resultSet.getString("Idioma"),
						resultSet.getString("Subtitulo").equals("Y"), resultSet.getFloat("Clasificacion"),
						resultSet.getString("Observaciones"), estadoPelicula);
			}

			return pelicula;

		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar Pelicula con Nombre " + nombre);
		} finally {
			cerrarConexion();
		}
	}

	@Override
	public List<Object> listar() {
		try {

			List<Object> peliculas = new ArrayList<Object>();
			Pelicula pelicula = null;

			ResultSet resultSet = ejecutarSelect("SELECT * FROM Pelicula");

			while (resultSet.next()) {
				EstadoActivoInactivo estadoPelicula;
				if (resultSet.getInt("Estado") == 1) {
					estadoPelicula = EstadoActivoInactivo.ACTIVO;
				} else
					estadoPelicula = EstadoActivoInactivo.INACTIVO;

				pelicula = new Pelicula(resultSet.getString("Nombre"), resultSet.getString("Director"),
						resultSet.getString("Genero"), resultSet.getInt("Duracion"), resultSet.getString("Idioma"),
						resultSet.getString("Subtitulo").equals("Y"), resultSet.getFloat("Clasificacion"),
						resultSet.getString("Observaciones"), estadoPelicula);
				peliculas.add(pelicula);
			}

			return peliculas;

		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar todas las peliculas");
		} finally {
			cerrarConexion();
		}

	}

	@Override
	public boolean insertar(Object objetoPelicula) {
		try {

			Pelicula pelicula = (Pelicula) objetoPelicula;

			PreparedStatement preparedStatement = conectarDb().prepareStatement(
					"INSERT INTO TPO.dbo.Pelicula (Nombre, Director, Genero, Duracion, Idioma, Subtitulos, Clasificacion, Observaciones, Estado ) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, pelicula.getNombre());
			preparedStatement.setString(2, pelicula.getDirector());
			preparedStatement.setString(3, pelicula.getGenero());
			preparedStatement.setInt(4, pelicula.getDuracion());
			preparedStatement.setString(5, pelicula.getIdioma());
			if (pelicula.getSubtitulos()) {
				preparedStatement.setInt(6, 1);

			} else
				preparedStatement.setInt(6, 0);
			preparedStatement.setFloat(7, pelicula.getCalificacion());
			preparedStatement.setString(8, pelicula.getObservaciones());
			if (pelicula.getEstado().equals(EstadoActivoInactivo.ACTIVO)) {
				preparedStatement.setInt(9, 1);
			} else
				preparedStatement.setInt(9, 1);

			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return false;

	}

	@Override
	public boolean actualizar(Object objetoPelicula) {
		try {

			Pelicula pelicula = (Pelicula) objetoPelicula;

			PreparedStatement preparedStatement = conectarDb().prepareStatement(
					"UPDATE TPO.dbo.Pelicula SET Director = ?, Genero = ?, Duracion = ?, Idioma = ?, Subtitulos = ?, Clasificacion = ?, Observaciones = ?, Estado = ? WHERE Nombre = ?)");

			preparedStatement.setString(1, pelicula.getDirector());
			preparedStatement.setString(2, pelicula.getGenero());
			preparedStatement.setInt(3, pelicula.getDuracion());
			preparedStatement.setString(4, pelicula.getIdioma());
			if (pelicula.getSubtitulos()) {
				preparedStatement.setInt(5, 1);

			} else
				preparedStatement.setInt(5, 0);
			preparedStatement.setFloat(6, pelicula.getCalificacion());
			preparedStatement.setString(7, pelicula.getObservaciones());
			if (pelicula.getEstado().equals(EstadoActivoInactivo.ACTIVO)) {
				preparedStatement.setInt(8, 1);
			} else
				preparedStatement.setInt(8, 1);
			preparedStatement.setString(9, pelicula.getNombre());
			int filasAfectadas = preparedStatement.executeUpdate();
			if (filasAfectadas != 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return false;

	}

	public boolean borrar(String nombre) {
		try {

			Connection connection = conectarDb();
			Statement statement = connection.createStatement();
			int filasAfectadas = statement.executeUpdate("DELETE FROM Pelicula where Nombre=" + nombre);

			if (filasAfectadas == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Object buscar(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean borrar(Integer key) {
		// TODO Auto-generated method stub
		return false;
	}

}
