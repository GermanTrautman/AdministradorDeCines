package com.cine.vista;

import javax.swing.BoxLayout;
import javax.swing.JTable;

import com.cine.controlador.ControladorCine;

import modeloVista.ModeloEstablecimientos;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JFormularioBajaEstablecimiento extends JFormularioBase {

	private static final long serialVersionUID = 1488800648208098796L;
	
	private JTable table;
	private ModeloEstablecimientos modeloEstablecimientosABorrar;

	public JFormularioBajaEstablecimiento() {
		
		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		ControladorCine controladorCine = ControladorCine.getInstance();
		
		table = new JTable(modeloEstablecimientosABorrar = new ModeloEstablecimientos(controladorCine));
		table.setSize(200, 100);
		getContentPane().add(table);
		
		JButton btnBorrarEstablecimiento = new JButton("Borrar establecimiento");
		btnBorrarEstablecimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] indices = table.getSelectedRows();
				modeloEstablecimientosABorrar.borrarEstablecimientos(indices);
			}
		});
		getContentPane().add(btnBorrarEstablecimiento);
	}
}
