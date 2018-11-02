package com.cine.vista;


import com.cine.controlador.ControladorRolUsuario;
import com.cine.controlador.ControladorUsuario;
import com.cine.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioBajaUsuario extends JFormularioBase {

    private static final long serialVersionUID = 1488800648208098796L;

    private JTextField dni;
    private JTextField nombre;
    private JTextField email;
    private JTextField fechaNacimiento;
    private Usuario usuario;

    public JFormularioBajaUsuario() {

        getContentPane().setLayout(null);

        JLabel lblAltasalas = new JLabel("Baja Usuario");
        lblAltasalas.setHorizontalAlignment(SwingConstants.CENTER);
        lblAltasalas.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblAltasalas.setBounds(0, 122, 1024, 38);
        this.getContentPane().add(lblAltasalas);

        JLabel lblCuit = new JLabel("DNI");
        lblCuit.setBounds(223, 195, 229, 20);
        this.getContentPane().add(lblCuit);

        this.dni = new JTextField();
        this.dni.setBounds(467, 195, 256, 26);
        this.dni.setColumns(10);
        this.getContentPane().add(this.dni);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if (dni.getText() != null) {
                    usuario = ControladorUsuario.getInstance().buscarEnCache(Integer.parseInt(dni.getText()));
                    if (usuario == null) {
                        usuario = ControladorUsuario.getInstance().buscarUsuarioEnDb(Integer.parseInt(dni.getText()));
                    }
                    if (usuario.getDni() != null) {

                        popularCampos(usuario);
                    } else {

                        JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                    }
                }
            }
        });
        btnBuscar.setBounds(745, 194, 115, 29);
        this.getContentPane().add(btnBuscar);

        JLabel lblNombre = new JLabel("Nombre de Usuario");
        lblNombre.setBounds(223, 237, 229, 20);
        this.getContentPane().add(lblNombre);

        this.nombre = new JTextField();
        this.nombre.setEnabled(false);
        this.nombre.setBounds(467, 237, 256, 26);
        this.nombre.setColumns(10);
        this.getContentPane().add(this.nombre);

        JLabel lblDomicilio = new JLabel("Email");
        lblDomicilio.setBounds(223, 279, 229, 20);
        this.getContentPane().add(lblDomicilio);

        this.email = new JTextField();
        this.email.setEnabled(false);
        this.email.setBounds(467, 279, 256, 26);
        this.email.setColumns(10);
        this.getContentPane().add(this.email);

        JLabel lblCapacidad = new JLabel("Fecha de Nacimiento");
        lblCapacidad.setBounds(223, 321, 229, 20);
        this.getContentPane().add(lblCapacidad);

        this.fechaNacimiento = new JTextField();
        this.fechaNacimiento.setEnabled(false);
        this.fechaNacimiento.setBounds(467, 321, 256, 26);
        this.fechaNacimiento.setColumns(10);
        this.getContentPane().add(this.fechaNacimiento);

        JButton btnBorrarUsuario = new JButton("Eliminar");
        btnBorrarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if (dni.getText() != null) {
                    ControladorRolUsuario.getInstance().bajaRolUsuario(Integer.parseInt(dni.getText()));
                    ControladorUsuario.getInstance().bajaUsuario(Integer.parseInt(dni.getText()));
                    usuario.borrarUsuario();
                    JOptionPane.showMessageDialog(null, "Usuario borrado correctamente");
                    resetCampos();
                }
            }
        });
        btnBorrarUsuario.setBounds(223, 670, 115, 29);
        this.getContentPane().add(btnBorrarUsuario);
    }

    private void popularCampos(Usuario usuario) {

        if (usuario != null) {

            this.nombre.setText(usuario.getNombre());
            this.email.setText(usuario.getDomicilio());
            this.fechaNacimiento.setText(usuario.getFechaDeNacimiento());
        }
    }

    private void resetCampos() {

        this.dni.setText("");
        this.nombre.setText("");
        this.email.setText("");
        this.fechaNacimiento.setText("");
    }
}
