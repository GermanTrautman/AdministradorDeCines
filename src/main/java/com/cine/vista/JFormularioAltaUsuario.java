package com.cine.vista;

import javax.swing.*;

public class JFormularioAltaUsuario extends JFormularioBase {

    private static final long serialVersionUID = 1L;

    private JTextField txtNombre = new JTextField();
    private JTextField txtDni = new JTextField();
    private JTextField txtFecha = new JTextField();
    private JButton btnAgregarUsuario = new JButton("Agregar Usuario");

    public JFormularioAltaUsuario() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JLabel("Nombre :"));
        this.getContentPane().add(txtDni);
        this.getContentPane().add(new JLabel("Dni :"));
        this.getContentPane().add(txtNombre);
        this.getContentPane().add(new JLabel("Fecha de Nacimiento :"));
        this.getContentPane().add(txtFecha);
        this.getContentPane().add(btnAgregarUsuario);


        btnAgregarUsuario.setMaximumSize(getMaximumSize());
    }

    public void reset() {

        this.txtNombre.setText("");
        this.txtDni.setText("");
    }

}
