package com.cine.vista.modelo;

import javax.swing.table.AbstractTableModel;

import com.cine.controlador.ControladorPelicula;
import com.cine.utilidades.Estado;

public class ModeloTablePelicula extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getRowCount() {
		return ControladorPelicula.getInstance().getPeliculas().size() + 1;
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		if (fila == 0) {
			switch (columna) {
			case 0: {
				return "Id";
			}
			case 1: {
				return "Nombre";
			}
			case 2: {
				return "Director";
			}
			case 3: {
				return "Genero";
			}
			case 4: {
				return "Duracion";
			}
			case 5: {
				return "Idioma";
			}
			case 6: {
				return "Subtitulos";
			}
			case 7: {
				return "Calificacion";
			}
			case 8: {
				return "Observacion";
			}
			case 9: {
				return "Estado";
			}

			default:
				break;
			}
		}else {
			switch (columna) {
			case 0: {
				return ((Integer) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getId());
			}
			case 1: {
				return ((String) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getNombre());
			}
			case 2: {
				return ((String) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getDirector());
			}
			case 3: {
				return ((String) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getGenero());
			}
			case 4: {
				return ((Integer) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getDuracion());
			}
			case 5: {
				return ((String) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getIdioma());
			}
			case 6: {
				return ((Boolean) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getSubtitulos());
			}
			case 7: {
				return ((float) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getCalificacion());
			}
			case 8: {
				return ((String) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getObservaciones());
			}
			case 9: {
				return ((Estado) ControladorPelicula.getInstance().getPeliculas().get(fila-1).getEstado());
			}

			default:
				break;
			}
		}
		return "N/A";
	}

}
