package com.cine.vista;

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.controlador.ControladorRol;
import com.cine.controlador.ControladorSala;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Rol;
import com.cine.utilidades.Estado;
import com.cine.vista.modelo.ComboEstablecimiento;
import com.cine.vista.modelo.ComboEstado;
import com.cine.vista.modelo.ComboRol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioAltaRol extends JFormularioBase {

    private static final long serialVersionUID = 463503988391452862L;

    private JTextField nombre = new JTextField();
    private JComboBox<ComboRol> comboRoles = new JComboBox<>();
    private JComboBox<ComboEstado> comboEstados = new JComboBox<>();

    private JButton btnGuardarRol = new JButton("Guardar");

    public JFormularioAltaRol() {

        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JLabel("Nombre:"));
        this.getContentPane().add(nombre);

        this.getContentPane().add(new JLabel("Rol :"));
        this.getContentPane().add(comboRoles);

        popularComboRoles();

        this.getContentPane().add(new JLabel("Estado :"));
        this.getContentPane().add(comboEstados);

        popularEstado();

        this.btnGuardarRol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Object itemEstablecimiento = comboRoles.getSelectedItem();
                Integer idEstablecimiento = ((ComboEstablecimiento) itemEstablecimiento).getId();

                Object itemEstado = comboEstados.getSelectedItem();
                String estadoEnletras = ((ComboEstado) itemEstado).getEstado();

                if (nombre.getText() != null && comboRoles.getSelectedItem() != null && comboEstados.getSelectedItem() != null) {
                    try {
                        ControladorRol.getInstance().altaRol(nombre.getText(),Estado.valueOf(comboEstados.getSelectedItem().toString()));
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    } catch (InstantiationException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        this.getContentPane().add(btnGuardarRol);

        this.btnGuardarRol.setMaximumSize(getMaximumSize());
    }

    public void reset() {

        this.nombre.setText("");
        this.comboEstados.setSelectedIndex(0);
        this.comboRoles.setSelectedIndex(0);
    }

    private void popularComboRoles() {

        for (Rol rol : ControladorRol.getInstance().getRoles()) {
            comboRoles.addItem(new ComboRol(rol.getNombre(), rol.getEstado()));
        }

    }

    private void popularEstado() {

        comboEstados.addItem(new ComboEstado(Estado.ACTIVO.estado(), 1));
        comboEstados.addItem(new ComboEstado(Estado.INACTIVO.estado(), 2));
    }
}
