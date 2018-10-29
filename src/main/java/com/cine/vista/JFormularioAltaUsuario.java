package com.cine.vista;

import com.cine.controlador.ControladorUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JFormularioAltaUsuario extends JFormularioBase {

    private static final long serialVersionUID = 1L;

    private ControladorUsuario controladorUsuario = ControladorUsuario.getInstance();

    private JTextField dni = new JTextField();
    private JTextField nombreDeUsuario = new JTextField();
    private JTextField email = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JTextField nombre = new JTextField();
    private JTextField domicilio = new JTextField();

    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    JFormattedTextField fecha = new JFormattedTextField(format);
    private JButton btnAgregarUsuario = new JButton("Agregar Usuario");

    public JFormularioAltaUsuario() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.getContentPane().add(new JLabel("Nombre :"));
        this.getContentPane().add(nombre);
        this.getContentPane().add(new JLabel("Nombre de usuario :"));
        this.getContentPane().add(nombreDeUsuario);
        this.getContentPane().add(new JLabel("Dni :"));
        this.getContentPane().add(dni);
        this.getContentPane().add(new JLabel("Email :"));
        this.getContentPane().add(email);
        this.getContentPane().add(new JLabel("Password :"));
        this.getContentPane().add(password);
        this.getContentPane().add(new JLabel("Domicilio :"));
        this.getContentPane().add(domicilio);
        this.getContentPane().add(new JLabel("Fecha de Nacimiento :"));
        this.getContentPane().add(fecha);


        btnAgregarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (dni.getText() != null && nombreDeUsuario.getText() != null && domicilio.getText() != null
                        && nombre.getText() != null && password.getText() != null && fecha.getText() != null) {

                    controladorUsuario.altaUsuario(Integer.parseInt(dni.getText()), nombreDeUsuario.getText(), email.getText(), password.getText(),
                            nombre.getText(), domicilio.getText(), Date.valueOf(fecha.getText()));

                }
                JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
                reset();
            }
        });

        this.getContentPane().add(btnAgregarUsuario);

        btnAgregarUsuario.setMaximumSize(getMaximumSize());
    }

    public void reset() {
        this.nombre.setText("");
        this.dni.setText("");
        this.nombreDeUsuario.setText("");
        this.email.setText("");
        this.password.setText("");
        this.domicilio.setText("");
        this.fecha.setText("");
    }

}
