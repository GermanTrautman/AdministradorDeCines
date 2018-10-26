package com.cine.vista;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cine.vista.modelo.ModeloTablaSala;

public class JFormularioListarSalas extends JFormularioBase {

	private static final long serialVersionUID = -982163608442583196L;

	private JTable table;
    private ModeloTablaSala modeloTablaSala;

    public JFormularioListarSalas() {
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        table = new JTable(modeloTablaSala = new ModeloTablaSala());
        table.setSize(200, 100);
        getContentPane().add(table);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!(e.getValueIsAdjusting() || table.getSelectedRow() <= 0)) {

                    String nombre = (String) (table.getValueAt(table.getSelectedRow(), 0) != "N/A"
                            ? table.getValueAt(table.getSelectedRow(), 0)
                            : "");
                    if (nombre != null && !nombre.isEmpty()) {
                    	
                        int result = JOptionPane.showConfirmDialog(null, "¿Queres modificar esta sala?",
                                "Confirmacion", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {

                            JFrame formularioModificarSala = new JFormularioModificarSala(modeloTablaSala, nombre);
                            formularioModificarSala.setVisible(true);
                        }
                    }
                }
            }
        });
    }
}
