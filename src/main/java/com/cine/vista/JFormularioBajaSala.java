package com.cine.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.cine.controlador.ControladorSala;
import com.cine.vista.modelo.ModeloTablaSala;

public class JFormularioBajaSala extends JFormularioBase {

	private static final long serialVersionUID = -1172525196735885232L;
	
	private JTable table;
	
	private ModeloTablaSala modeloTablaSala;

	public JFormularioBajaSala() {
		
		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		table = new JTable(modeloTablaSala = new ModeloTablaSala());
		table.setSize(200, 100);
		getContentPane().add(table);

		JButton btnBorrarEstablecimiento = new JButton("Borrar sala");
		btnBorrarEstablecimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int[] filasSeleccionadas = table.getSelectedRows();

				for (int fila : filasSeleccionadas) {

					String nombre = ((String)(table.getValueAt(fila, 0) != "N/A" ? table.getValueAt(fila, 0) : ""));

					ControladorSala.getInstance().baja(nombre);
				}
				
				modeloTablaSala.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Sala borrada correctamente");
			}
		});
		
		getContentPane().add(btnBorrarEstablecimiento);
	}
}
