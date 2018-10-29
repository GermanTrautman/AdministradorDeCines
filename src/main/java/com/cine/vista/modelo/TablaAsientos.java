package com.cine.vista.modelo;

import javax.swing.table.AbstractTableModel;

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
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}
}
