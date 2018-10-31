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
		
		if (ControladorSala.getInstance().getSalaSeleccionada() != null && ControladorSala.getInstance().getSalaSeleccionada().getAsientosFisicos()[fila][columna] != null
				&& ControladorSala.getInstance().getSalaSeleccionada().getAsientosFisicos()[fila][columna].getFila() != null
				&& ControladorSala.getInstance().getSalaSeleccionada().getAsientosFisicos()[fila][columna]
						.getNumeroDeAsiento() != null) {
			return "X";
		} else {
			return "";
		}
	}
	
    public boolean isCellEditable(int fila, int columna){  
        return false;  
    }

}
