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

	private static PeliculaPersistente instancia;

	private PeliculaPersistente() {

	}

	public static PeliculaPersistente getInstance() {
		if (instancia == null) {
			instancia = new PeliculaPersistente();
		}
		return instancia;
	}

	@Override
	public Object buscar(Object nombre) {
		try {

			Pelicula pelicula = null;

			if (nombre != null) {
				PreparedStatement preparedStatement = conectarDb()
						.prepareStatement("SELECT * FROM Pelicula WHERE Nombre = ?");
				preparedStatement.setString(1, (String) nombre);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					EstadoActivoInactivo estadoPelicula;
					if (resultSet.getInt("Estado") == 1) {
						estadoPelicula = EstadoActivoInactivo.ACTIVO;
					} else
						estadoPelicula = EstadoActivoInactivo.INACTIVO;

					pelicula = new Pelicula(resultSet.getString("Nombre"), resultSet.getString("Director"),
							resultSet.getString("Genero"), resultSet.getInt("Duracion"), resultSet.getString("Idioma"),
							resultSet.getString("Subtitulos").equals("Y"), resultSet.getFloat("Calificacion"),
							resultSet.getString("Observaciones"), estadoPelicula);
					pelicula.setId(resultSet.getInt("Id"));
				}
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
						resultSet.getString("Subtitulos").equals("Y"), resultSet.getFloat("Calificacion"),
						resultSet.getString("Observaciones"), estadoPelicula);
				pelicula.setId(resultSet.getInt("Id"));
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
					"INSERT INTO TPO.dbo.Pelicula (Nombre, Director, Genero, Duracion, Idioma, Subtitulos, Calificacion, Observaciones, Estado ) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

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
					"UPDATE TPO.dbo.Pelicula SET Nombre = ?, Director = ?, Genero = ?, Duracion = ?, Idioma = ?, Subtitulos = ?, Calificacion = ?, Observaciones = ?, Estado = ? WHERE ID = ?");
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
				preparedStatement.setInt(9, 0);
			preparedStatement.setInt(10, pelicula.getId());
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

	@Override
	public boolean borrar(Object key) {
		try {

			Connection connection = conectarDb();
			Statement statement = connection.createStatement();
			int filasAfectadas = statement.executeUpdate("DELETE FROM Pelicula where Id=" + key);

			if (filasAfectadas == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Integer getIdPelicula(String nombre) {
		Integer id = null;
		try {

			PreparedStatement preparedStatement = conectarDb()
					.prepareStatement("SELECT Id FROM Pelicula WHERE Nombre = ?");
			preparedStatement.setString(1, nombre);
			ResultSet resultSet = preparedStatement.executeQuery();
//			ResultSet resultset = ejecutarSelect("Select Id FROM Pelicula WHERE Nombre = " + nombre);
			if (resultSet.next()) {
				id = resultSet.getInt("Id");
			}
		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar Pelicula con nombre " + nombre);
		} finally {
			cerrarConexion();
		}
		return id;
	}

}
