package com.cine.vista;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.cine.controlador.ControladorCine;

import modeloVista.ModeloModificacionEstablecimiento;

public class JFormularioListarEstablecimiento extends JFormularioBase {

    private static final long serialVersionUID = 1488800648208098796L;

    private JTable table;
    private ModeloModificacionEstablecimiento modeloModificacionEstablecimiento;

    public JFormularioListarEstablecimiento() {
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        table = new JTable(modeloModificacionEstablecimiento = new ModeloModificacionEstablecimiento());
        table.setSize(200, 100);
        getContentPane().add(table);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!(e.getValueIsAdjusting() || table.getSelectedRow() <= 0)) {

                    int cuit = (int) (table.getValueAt(table.getSelectedRow(), 0) != "N/A"
                            ? table.getValueAt(table.getSelectedRow(), 0)
                            : -1);
                    if (cuit > 0) {
                        int result = JOptionPane.showConfirmDialog(null, "Quiere modificar este establecimiento?",
                                "Confirmacion", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {

                            JFrame asd = new JFormularioModificarEstablecimiento(modeloModificacionEstablecimiento, cuit);
                            asd.setVisible(true);

                        }
                    }
                }
            }
        });
    }
}
