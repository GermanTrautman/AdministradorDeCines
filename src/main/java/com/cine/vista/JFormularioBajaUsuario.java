package com.cine.vista;

import com.cine.controlador.ControladorUsuario;
import com.cine.vista.modelo.ModeloTablaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioBajaUsuario extends JFormularioBase {

	private static final long serialVersionUID = 1488800648208098796L;

	private JTable table;

	private ModeloTablaUsuario modeloBajaUsuario;

	public JFormularioBajaUsuario() {
		
		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		table = new JTable(modeloBajaUsuario = new ModeloTablaUsuario());
		table.setSize(200, 100);
		getContentPane().add(table);

		JButton btnBorrarUsuario = new JButton("Borrar usuario");
		btnBorrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int[] filasSeleccionadas = table.getSelectedRows();

				for (int fila : filasSeleccionadas) {

					int dni = ((int) (table.getValueAt(fila, 0) != "N/A" ? table.getValueAt(fila, 0) : - 1));

					ControladorUsuario.getInstance().bajaUsuario(dni);
				}
				
				modeloBajaUsuario.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Establecimiento borrado correctamente");
			}
		});
		
		getContentPane().add(btnBorrarUsuario);
	}
}
