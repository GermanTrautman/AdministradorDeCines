package com.cine.vista;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cine.vista.modelo.ModeloTablePelicula;

public class JFormularioListarPelicula extends JFormularioBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1488800648208098796L;
	private JTable tabla;
	private ModeloTablePelicula modeloTablaPelicula;
	
	public JFormularioListarPelicula() {

        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        tabla = new JTable(modeloTablaPelicula = new ModeloTablePelicula());
        tabla.setSize(200, 100);
        getContentPane().add(tabla);

        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!(e.getValueIsAdjusting() || tabla.getSelectedRow() <= 0)) {

                    int id = (int) (tabla.getValueAt(tabla.getSelectedRow(), 0) != "N/A"
                            ? tabla.getValueAt(tabla.getSelectedRow(), 0)
                            : -1);
                    if (id > 0) {
                        int result = JOptionPane.showConfirmDialog(null, "Quiere modificar esta pelicula?",
                                "Confirmacion", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {

                            JFrame asd = new JFormularioModificacionPelicula(modeloTablaPelicula, id);
                            asd.setVisible(true);

                        }
                    }
                }
            }
        });

	}

}
