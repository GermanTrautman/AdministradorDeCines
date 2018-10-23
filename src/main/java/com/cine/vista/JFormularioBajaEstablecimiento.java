package com.cine.vista;

import javax.swing.*;

import com.cine.controlador.ControladorCine;
import com.cine.vista.modelo.ModeloTablaEstablecimiento;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFormularioBajaEstablecimiento extends JFormularioBase {

	private static final long serialVersionUID = 1488800648208098796L;

	private JTable table;
	
	private ModeloTablaEstablecimiento modeloBajaEstablecimiento;

	public JFormularioBajaEstablecimiento() {
		
		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		table = new JTable(modeloBajaEstablecimiento = new ModeloTablaEstablecimiento());
		table.setSize(200, 100);
		getContentPane().add(table);

		JButton btnBorrarEstablecimiento = new JButton("Borrar establecimiento");
		btnBorrarEstablecimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int[] filasSeleccionadas = table.getSelectedRows();

				for (int fila : filasSeleccionadas) {

					int cuit = ((int) (table.getValueAt(fila, 0) != "N/A" ? table.getValueAt(fila, 0) : - 1));

					ControladorCine.getInstance().bajaEstablecimiento(cuit);
				}
				
				modeloBajaEstablecimiento.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Establecimiento borrado correctamente");
			}
		});
		
		getContentPane().add(btnBorrarEstablecimiento);
	}
}
