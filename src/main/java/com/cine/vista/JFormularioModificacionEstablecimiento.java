package com.cine.vista;

import javax.swing.BoxLayout;
import javax.swing.JTable;

import com.cine.controlador.ControladorCine;

import modeloVista.ModeloEstablecimiento;

public class JFormularioModificacionEstablecimiento extends JFormularioBase {

	private static final long serialVersionUID = 1488800648208098796L;
	
	private JTable table;
	private ModeloEstablecimiento modeloEstablecimiento;

	public JFormularioModificacionEstablecimiento() {
		
		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		table = new JTable(modeloEstablecimiento = new ModeloEstablecimiento());
		table.setSize(200, 100);
		getContentPane().add(table);
	}
}
