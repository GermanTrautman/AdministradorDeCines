package com.cine.vista;

import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.cine.controlador.ControladorCine;

import modeloVista.ModeloModificacionEstablecimiento;

public class JFormularioModificacionEstablecimiento extends JFormularioBase {

	private static final long serialVersionUID = 1488800648208098796L;

	private JTable table;
	private ModeloModificacionEstablecimiento modeloModificacionEstablecimiento;

	public JFormularioModificacionEstablecimiento() {
		
		ControladorCine.getInstance().obtenerEstablecimientos();

		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		table = new JTable(modeloModificacionEstablecimiento = new ModeloModificacionEstablecimiento());
		table.setSize(200, 100);
		table.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent arg0) {
				String a = "a";
				String b = a;
			}
			
		});

		getContentPane().add(table);
	}
}
