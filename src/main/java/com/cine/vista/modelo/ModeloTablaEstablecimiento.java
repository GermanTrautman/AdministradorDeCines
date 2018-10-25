package com.cine.vista.modelo;

import javax.swing.table.AbstractTableModel;

import com.cine.controlador.ControladorEstablecimiento;

public class ModeloTablaEstablecimiento extends AbstractTableModel  {

	private static final long serialVersionUID = -5065889673449492921L;
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return ControladorEstablecimiento.getInstance().getEstablecimientos().size() + 1;
	}

	@Override
	public Object getValueAt(int fila, int columna) {

		if (fila == 0) {
			
			if (columna == 0) {
				return "CUIT";
			} else if (columna == 1) {
				return "Nombre";
			} else if (columna == 2) {
				return "Domicilio";
			} else if (columna == 3) {
				return "Capacidad";
			}
		} else {
			
			if (columna == 0) {
				return (Integer) ControladorEstablecimiento.getInstance().getEstablecimientos().get(fila - 1).getCuit();
			} else if (columna == 1) {
				return ControladorEstablecimiento.getInstance().getEstablecimientos().get(fila - 1).getNombre();
			} else if (columna == 2) {
				return ControladorEstablecimiento.getInstance().getEstablecimientos().get(fila - 1).getDomicilio();
			} else if (columna == 3) {
				return (Integer) ControladorEstablecimiento.getInstance().getEstablecimientos().get(fila - 1).getCapacidad();
			}
		}
		
		return "N/A";
	}
}
