package com.cine.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.cine.controlador.ControladorPelicula;
import com.cine.vista.modelo.ModeloTablePelicula;

public class JFormularioBajaPelicula extends JFormularioBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private ModeloTablePelicula modeloBajaPelicula;

	/**
	 * Create the frame.
	 */
	public JFormularioBajaPelicula() {
		getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
		tabla = new JTable(modeloBajaPelicula= new ModeloTablePelicula());
		tabla.setSize(200, 200);
		getContentPane().add(tabla);
		JButton btnBorrarPelicula = new JButton("Borrar Pelicula");
		btnBorrarPelicula.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int[] filasSeleccionadas = tabla.getSelectedRows();
				for(int fila: filasSeleccionadas) {
					int id = ((int) (tabla.getValueAt(fila, 0)!= "N/A" ? tabla.getValueAt(fila, 0):-1));
//					ControladorPelicula.getInstance().bajaPelicula(id);
				}
				modeloBajaPelicula.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Pelicula Borrada Correctamente");
			}
		});
		getContentPane().add(btnBorrarPelicula);
		
	
	}

}
