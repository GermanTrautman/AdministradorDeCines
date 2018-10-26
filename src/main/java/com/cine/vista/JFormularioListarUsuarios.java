package com.cine.vista;

import com.cine.vista.modelo.ModeloTablaEstablecimiento;
import com.cine.vista.modelo.ModeloTablaUsuario;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JFormularioListarUsuarios extends JFormularioBase {

    private static final long serialVersionUID = 1488800648208098796L;

    private JTable table;
    private ModeloTablaUsuario modeloTablaUsuario;

    public JFormularioListarUsuarios() {
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        table = new JTable(modeloTablaUsuario = new ModeloTablaUsuario());
        table.setSize(200, 100);
        getContentPane().add(table);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!(e.getValueIsAdjusting() || table.getSelectedRow() <= 0)) {

                    int dni = (int) (table.getValueAt(table.getSelectedRow(), 0) != "N/A"
                            ? table.getValueAt(table.getSelectedRow(), 0)
                            : -1);
                    if (dni > 0) {
                        int result = JOptionPane.showConfirmDialog(null, "Quiere modificar este usuario?",
                                "Confirmacion", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {

                            JFrame asd = new JFormularioModificarUsuario(modeloTablaUsuario, dni);
                            asd.setVisible(true);

                        }
                    }
                }
            }
        });
    }
}
