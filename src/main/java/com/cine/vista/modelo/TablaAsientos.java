package com.cine.vista.modelo;

import javax.swing.table.AbstractTableModel;

import com.cine.controlador.ControladorSala;

public class TablaAsientos extends AbstractTableModel {

	private static final long serialVersionUID = -7759006375534276789L;

	@Override
	public int getColumnCount() {
		return 25;
	}

	@Override
	public int getRowCount() {
		return 25;
	}

	@Override
	public Object getValueAt(int fila, int columna) {

		if (ControladorSala.getInstance().getAsientosFisicosTemporales()[fila][columna] != null
				&& ControladorSala.getInstance().getAsientosFisicosTemporales()[fila][columna].getFila() != null
				&& ControladorSala.getInstance().getAsientosFisicosTemporales()[fila][columna]
						.getNumeroDeAsiento() != null) {
			return "X";
		} else {
			return "";
		}
	}
}
