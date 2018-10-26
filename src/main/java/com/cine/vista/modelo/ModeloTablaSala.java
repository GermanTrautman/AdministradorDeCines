package com.cine.vista.modelo;

import javax.swing.table.AbstractTableModel;

import com.cine.controlador.ControladorSala;

public class ModeloTablaSala extends AbstractTableModel  {

	private static final long serialVersionUID = -5065889673449492921L;
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return ControladorSala.getInstance().getSalas().size() + 1;
	}

	@Override
	public Object getValueAt(int fila, int columna) {

		if (fila == 0) {
			
			if (columna == 0) {
				return "Nombre";
			} else if (columna == 1) {
				return "Capacidad";
			} else if (columna == 2) {
				return "Nombre del establecimiento";
			} else if (columna == 3) {
				return "Estado";
			}
		} else {
			
			if (columna == 0) {
				return ControladorSala.getInstance().getSalas().get(fila - 1).getNombre();
			} else if (columna == 1) {
				return (Integer) ControladorSala.getInstance().getSalas().get(fila - 1).getCapacidad();
			} else if (columna == 2) {
				return ControladorSala.getInstance().getSalas().get(fila - 1).getEstablecimiento().getNombre();
			} else if (columna == 3) {
				return ControladorSala.getInstance().getSalas().get(fila - 1).getEstado().estado();
			}
		}
		
		return "N/A";
	}
}
