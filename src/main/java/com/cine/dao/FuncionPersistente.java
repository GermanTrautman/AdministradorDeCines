package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.cine.controlador.ControladorPelicula;
import com.cine.controlador.ControladorSala;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Funcion;
import com.cine.modelo.Pelicula;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;
import com.cine.utilidades.EstadoActivoInactivo;

public class FuncionPersistente implements Persistencia {

	private static FuncionPersistente instancia;

	private FuncionPersistente() {

	}

	public static FuncionPersistente getInstance() {
		if (instancia == null) {
			instancia = new FuncionPersistente();
		}
		return instancia;
	}

	@Override
	public Object buscar(Object funcionBuscada) {

		try {
			Funcion funcion = null;
			if (funcionBuscada != null) {
				PreparedStatement preparedStatement = conectarDb().prepareStatement(
						"SELECT * FROM Funcion_vw WHERE NombrePelicula = ? AND NombreSala = ? AND Fecha = ? AND Horario = ?");
				preparedStatement.setString(1, ((Funcion) funcionBuscada).getPelicula().getNombre());
				preparedStatement.setString(2, ((Funcion) funcionBuscada).getSala().getNombre());
				preparedStatement.setObject(3, ((Funcion) funcionBuscada).getFecha());
				preparedStatement.setTime(4, ((Funcion) funcionBuscada).getHora());
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					Sala salaDeFuncion = ControladorSala.getInstance().buscar(resultSet.getString("NombreSala"));
					Pelicula peliculaDeFuncion = (Pelicula) ControladorPelicula.getInstance()
							.buscarEnCache(resultSet.getString("NombrePelicula"));
					Estado estadoFuncion;
					if (resultSet.getInt("Estado") == 1) {
						estadoFuncion = Estado.ACTIVO;
					} else
						estadoFuncion = Estado.INACTIVO;
					LocalDate fecha = resultSet.getObject("Fecha", LocalDate.class);
					funcion = new Funcion(fecha, salaDeFuncion, peliculaDeFuncion, estadoFuncion,
							resultSet.getTime("Horario"));
					funcion.setId(resultSet.getInt("Id"));
				}
			}
			return null;
		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar Funcion deseada");
		} finally {
			cerrarConexion();
		}
	}

	@Override
	public List<Object> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(Object objetoFuncion) {
		try {
			Funcion funcion = (Funcion) objetoFuncion;
			PreparedStatement preparedStatement = conectarDb().prepareStatement(
					"INSERT INTO TPO.dbo.Funcion (Horario, IdPelicula, NombreSala, Estado, Fecha) VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setTime(1, funcion.getHora());
			preparedStatement.setInt(2, funcion.getPelicula().getId());
			preparedStatement.setString(3, funcion.getSala().getNombre());
			if (funcion.getEstado().equals(Estado.ACTIVO)) {
				preparedStatement.setInt(4, 1);
			} else
				preparedStatement.setInt(4, 0);
			preparedStatement.setDate(5, Date.valueOf(funcion.getFecha()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
	}

	@Override
	public void actualizar(Object objetoFuncion) {
		try {
			Funcion funcion = (Funcion) objetoFuncion;
			PreparedStatement preparedStatement = conectarDb().prepareStatement(
					"UPDATE TPO.dbo.Funcion SET Horario = ?, IdPelicula = ?, NombreSala = ?, Estado = ?, Fecha = ? WHERE Id = ?");
			preparedStatement.setTime(1, funcion.getHora());
			preparedStatement.setInt(2, funcion.getPelicula().getId());
			preparedStatement.setString(3, funcion.getSala().getNombre());
			if (funcion.getEstado().equals(Estado.ACTIVO)) {
				preparedStatement.setInt(4, 1);
			} else
				preparedStatement.setInt(4, 0);
			preparedStatement.setObject(5, funcion.getFecha());
			preparedStatement.setInt(6, funcion.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

	}

	@Override
	public void borrar(Object key) {
		try {

			PreparedStatement preparedStatement = conectarDb().prepareStatement("DELETE FROM Funcion where Id= ?");
			preparedStatement.setInt(1, (Integer) key);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

	}

	public Integer getIdFuncion(LocalDate fecha, Sala sala, Pelicula pelicula, Time hora) {
		try {
			Integer id = null;
			if (fecha != null && sala != null && pelicula != null) {
				PreparedStatement preparedStatement = conectarDb().prepareStatement(
						"SELECT Id FROM Funcion_vw WHERE NombrePelicula = ? AND NombreSala = ? AND Fecha = ? AND Horario = ?");
				preparedStatement.setString(1, pelicula.getNombre());
				preparedStatement.setString(2, sala.getNombre());
				preparedStatement.setString(3, fecha.toString());
				preparedStatement.setString(4, hora.toString());
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					id = resultSet.getInt("Id");
				}
			}
			return id;
		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar Funcion deseada");
		} finally {
			cerrarConexion();
		}
	}

	public List<Establecimiento> getEstablecimientos() {
		try {
			List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
			Establecimiento establecimiento = null;
			ResultSet resultSet = ejecutarSelect("SELECT * FROM Establecimiento ");
			while (resultSet.next()) {

				establecimiento = new Establecimiento(resultSet.getInt("CUIT"), resultSet.getString("Nombre"),
						resultSet.getString("Domicilio"), resultSet.getInt("Capacidad"));
				establecimientos.add(establecimiento);
			}

			return establecimientos;

		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar todos los establecimientos");
		} finally {
			liberarConexion();
		}

	}

	public List<Sala> getSalasDeEstablecimiento(Establecimiento establecimiento) {
		try {
			List<Sala> salas = new ArrayList<Sala>();
			Sala sala = null;
			PreparedStatement preparedStatement = conectarDb()
					.prepareStatement("SELECT * FROM Sala WHERE CUITEstablecimiento = ? AND Estado = 'Activo'");
			preparedStatement.setInt(1, establecimiento.getCuit());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Estado estado = Estado.valueOf(resultSet.getString("Estado").toUpperCase());
				sala = new Sala(resultSet.getString("Nombre"), establecimiento, estado);

				salas.add(sala);
			}
			return salas;
		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar todos los establecimientos");
		} finally {
			liberarConexion();
		}
	}

	public List<Pelicula> getPeliculasDisponibles() {
		try {

			List<Pelicula> peliculas = new ArrayList<Pelicula>();
			Pelicula pelicula = null;
			ResultSet resultSet = ejecutarSelect("SELECT * FROM Pelicula WHERE Estado = 1");

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
			liberarConexion();
		}
	}

}
