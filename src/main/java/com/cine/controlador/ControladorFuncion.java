package com.cine.controlador;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cine.dao.AsientoVirtualPersistente;
import com.cine.dao.Cache;
import com.cine.dao.FuncionPersistente;
import com.cine.modelo.AsientoFisico;
import com.cine.modelo.AsientoVirtual;
import com.cine.modelo.Funcion;
import com.cine.modelo.Pelicula;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;

public class ControladorFuncion implements Cache {

	private static ControladorFuncion instancia;
	private List<Funcion> funciones;
	private AsientoVirtualPersistente asientoVirtualPersistente = new AsientoVirtualPersistente();

	private ControladorFuncion() {
		this.funciones = new ArrayList<>();
	}

	public static ControladorFuncion getInstance() {

		if (ControladorFuncion.instancia == null) {
			ControladorFuncion.instancia = new ControladorFuncion();
		}

		return ControladorFuncion.instancia;
	}

	public List<Funcion> buscarPor(Integer cuitEstablecimiento, String nombrePelicula) {

		List<Funcion> funcionesBuscadas = buscarEnCachePor(cuitEstablecimiento, nombrePelicula);

		if (funcionesBuscadas.isEmpty()) {

			funcionesBuscadas = (List<Funcion>) FuncionPersistente.getInstance().buscarPor(cuitEstablecimiento,
					nombrePelicula);

			for (Funcion funcion : funcionesBuscadas) {

				AsientoVirtual[][] asientosVirtuales = (AsientoVirtual[][]) asientoVirtualPersistente
						.buscar(funcion.getId());
				funcion.setAsientoVirtual(asientosVirtuales);
			}

			for (Funcion funcion : funcionesBuscadas) {
				agregarACache(funcion);
			}
		}

		return funcionesBuscadas;
	}

	private Funcion buscarPor(Integer idFuncion) {

		Funcion funcionBuscada = null;

		if (funcionBuscada == null) {

			funcionBuscada = (Funcion) FuncionPersistente.getInstance().buscarPor(idFuncion);

			AsientoVirtual[][] asientosVirtuales = (AsientoVirtual[][]) asientoVirtualPersistente.buscar(idFuncion);
			funcionBuscada.setAsientoVirtual(asientosVirtuales);

			agregarACache(funcionBuscada);
		}

		return funcionBuscada;
	}

	public void altaFuncion(LocalDate fecha, Sala sala, Pelicula pelicula, Estado estado, Time hora) {

		Funcion funcion = new Funcion(fecha, sala, pelicula, estado, hora);
		funcion.insertarFuncion();
		agregarACache(funcion);
	}

	public void bajaFuncion(Funcion funcionAEliminar) {
		Funcion funcion = (Funcion) buscarEnCache(funcionAEliminar);

		if (funcion != null) {
			funcion.eliminarFuncion();
			funciones.remove(funcion);
		}
	}

	public void modificarFuncion(Funcion funcionAModificar) {

		Funcion funcion = (Funcion) buscarEnCache(funcionAModificar);

		/*
		 * To-do funcion.actualizarFuncion(funcion.getId(), funcion.getFecha(),
		 * funcion.getSala(), funcion.getPelicula(), funcion.getEstado(),
		 * funcion.getHora()); actualizarCache(funcion);
		 */
	}

	public void modificarAsientos(Integer idFuncion, AsientoVirtual[][] asientosModificados) {
		
		Funcion funcion = buscarPor(idFuncion);

		for (int i = 1; i < AsientoVirtual.FILAS; i++) {

			for (int j = 1; j < AsientoVirtual.ASIENTOSPORFILA; j++) {

				if (asientosModificados[i][j] != null && funcion.getAsientoVirtual()[i][j] != null) {

					if (!asientosModificados[i][j].getEstado().equals(funcion.getAsientoVirtual()[i][j].getEstado())) {

						 asientosModificados[i][j].vender();
					}
				}
			}
		}
	}

	@Override
	public Object buscarEnCache(Object funcionBuscada) {
		for (Funcion funcion : funciones) {
			if ((funcion.getPelicula().getNombre().equals(((Funcion) funcionBuscada).getPelicula().getNombre()))
					&& (funcion.getSala().getNombre().equals(((Funcion) funcionBuscada).getSala().getNombre()))
					&& (funcion.getFecha().compareTo(((Funcion) funcionBuscada).getFecha()) == 0)
					&& (funcion.getHora().compareTo(((Funcion) funcionBuscada).getHora()) == 0)) {
				return funcion;
			}
		}
		Funcion funcion = (Funcion) FuncionPersistente.getInstance().buscar(funcionBuscada);
		if (funcion != null) {
			this.agregarACache(funcion);
			return funcion;
		}
		return null;
	}

	private List<Funcion> buscarEnCachePor(Integer cuitEstablecimiento, String nombrePelicula) {

		List<Funcion> funcionesBuscadas = new ArrayList<>();

		for (Funcion funcion : funciones) {

			if (funcion.getSala().getEstablecimiento().getCuit() == cuitEstablecimiento
					&& funcion.getPelicula().getNombre().equals(nombrePelicula)) {
				funcionesBuscadas.add(funcion);
			}
		}

		return funcionesBuscadas;
	}

	private Funcion buscarEnCachePor(Integer idFuncion) {

		Funcion funcionBuscada = null;

		for (Funcion funcion : funciones) {

			if (funcion.getId() == idFuncion) {
				funcionBuscada = funcion;
			}
		}

		return funcionBuscada;
	}

	@Override
	public void agregarACache(Object entidad) {
		funciones.add((Funcion) entidad);
	}

	@Override
	public void borrarDeCache(Object funcionBuscada) {
		if (buscarEnCache(funcionBuscada) != null) {
			for (Iterator<Funcion> iterator = funciones.listIterator(); iterator.hasNext();) {
				Funcion funcion = iterator.next();
				if (funcion.getId().equals(((Funcion) funcionBuscada).getId())) {
					iterator.remove();
				}
			}
		}
	}

	@Override
	public void actualizarCache(Object entidad) {
		Funcion funcionModificada = (Funcion) entidad;
		Funcion funcionSinModificar = (Funcion) buscarEnCache(funcionModificada.getId());
		borrarDeCache(funcionSinModificar);
		agregarACache(funcionModificada);
	}

	public Funcion buscarPeliculaPorDiaYHora(Integer idEstablecimiento, String nombrePelicula) {

		Funcion funcion = new Funcion();
		return funcion.buscarPeliculaPorDiaYHora(idEstablecimiento, nombrePelicula);
	}
}
