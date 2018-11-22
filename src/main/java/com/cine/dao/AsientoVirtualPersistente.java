package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cine.modelo.AsientoFisico;
import com.cine.modelo.AsientoVirtual;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;
import com.cine.utilidades.EstadoVirtual;

public class AsientoVirtualPersistente implements Persistencia {

	// Busca todos los asientos virtuales de una determinada funcion
	@Override
	public Object buscar(Object idFuncion) {
		try {

			AsientoVirtual[][] asientosVirtuales = new AsientoVirtual[AsientoVirtual.FILAS][AsientoVirtual.ASIENTOSPORFILA];

			PreparedStatement preparedStatement = conectarDb()
					.prepareStatement("SELECT * FROM TPO.dbo.AsientoVirtual WHERE IdFuncion = ?");
			preparedStatement.setInt(1, (Integer) idFuncion);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EstadoVirtual estado = EstadoVirtual.valueOf(resultSet.getString("Estado").toUpperCase());
				Integer idAsientoFisico = resultSet.getInt("IdAsientoFisico");
				AsientoFisico asientoFisicoAsociado = buscarAsientoFisico(idAsientoFisico);

				AsientoVirtual asientoVirtual = new AsientoVirtual(asientoFisicoAsociado,
						resultSet.getInt("IdFuncion"));
				asientoVirtual.setEstado(estado);
				asientoVirtual.setId(resultSet.getInt("Id"));

				asientosVirtuales[asientoVirtual.getFisicoAsociado().getFila()][asientoVirtual.getFisicoAsociado()
						.getNumeroDeAsiento()] = asientoVirtual;
			}

			return asientosVirtuales;

		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar asientos de la funcion" + idFuncion);
		} finally {
			liberarConexion();
		}
	}

	private AsientoFisico buscarAsientoFisico(Integer idAsientoFisico) {
		try {
			AsientoFisico asientoFisico = null;
			PreparedStatement preparedStatement = conectarDb()
					.prepareStatement("SELECT * FROM AsientoFisico WHERE Id = ?");
			preparedStatement.setInt(1, idAsientoFisico);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Estado estado = Estado.valueOf(resultSet.getString("Estado").toUpperCase());
				asientoFisico = new AsientoFisico(resultSet.getString("NombreSala"), resultSet.getInt("Fila"),
						resultSet.getInt("NumeroDeAsiento"), estado);
			}
			return asientoFisico;
		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar asiento fisico");
		} finally {
			liberarConexion();
		}

	}

	@Override
	public List<Object> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(Object objetoAsientoVirtual) {
		try {

			AsientoVirtual asientoVirtual = (AsientoVirtual) objetoAsientoVirtual;
			Integer idAsientoFisico = getIdAsientoFisicoAsociado(asientoVirtual.getFisicoAsociado());
			PreparedStatement preparedStatement = conectarDb().prepareStatement(
					"INSERT INTO TPO.dbo.AsientoVirtual (IdAsientoFisico, IdFuncion, Estado) values (?, ?, ?)");

			preparedStatement.setInt(1, idAsientoFisico);
			preparedStatement.setInt(2, asientoVirtual.getIdFuncion());
			preparedStatement.setString(3, asientoVirtual.getEstado().estado());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			liberarConexion();
		}
	}

	private Integer getIdAsientoFisicoAsociado(AsientoFisico fisicoAsociado) {
		try {
			Integer idAsientoFisico = null;

			PreparedStatement preparedStatement = conectarDb().prepareStatement(
					"SELECT Id FROM AsientoFisico WHERE NombreSala= ? AND Fila = ? AND NumeroDeAsiento = ? ");
			preparedStatement.setString(1, fisicoAsociado.getNombreSala());
			preparedStatement.setInt(2, fisicoAsociado.getFila());
			preparedStatement.setInt(3, fisicoAsociado.getNumeroDeAsiento());
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				idAsientoFisico = resultset.getInt("Id");
			}
			return idAsientoFisico;
		} catch (SQLException e) {
			System.out.println("Error Query: " + e.getMessage());
			throw new RuntimeException("Error intentando buscar asiento");
		} finally {
			liberarConexion();
		}

	}

	@Override
	public void actualizar(Object entidad) {

		try {

			AsientoVirtual asientoVirtual = (AsientoVirtual) entidad;

			PreparedStatement preparedStatement = conectarDb()
					.prepareStatement("UPDATE TPO.dbo.AsientoVirtual SET Estado = ? WHERE Id = ?");
			preparedStatement.setString(1, asientoVirtual.getEstado().estado());
			preparedStatement.setInt(2, asientoVirtual.getId());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			liberarConexion();
		}

	}

	@Override
	public void borrar(Object key) {
		// TODO Auto-generated method stub

	}

}
