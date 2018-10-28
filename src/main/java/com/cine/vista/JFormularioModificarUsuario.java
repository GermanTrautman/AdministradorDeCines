package com.cine.vista;

import com.cine.controlador.ControladorUsuario;
import com.cine.modelo.Usuario;
import com.cine.vista.modelo.ModeloTablaUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioModificarUsuario extends JFormularioBase {

    private static final long serialVersionUID = 1L;

    private JTextField dni = new JTextField();
    private JTextField nombreDeUsuario = new JTextField();
    private JTextField nombre = new JTextField();
    private JTextField email = new JTextField();
    private JTextField domicilio = new JTextField();
    private JTextField password = new JTextField();

    private JButton btnAgregarUsuario = new JButton("Guardar cambios");

    private ControladorUsuario controladorUsuario = ControladorUsuario.getInstance();

    public JFormularioModificarUsuario(ModeloTablaUsuario modelo, Integer unDni) {

        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        Usuario usuario = (Usuario) controladorUsuario.buscarEnCache(unDni);

        this.getContentPane().add(new JLabel("DNI:"));
        this.getContentPane().add(dni).setEnabled(false);

        this.getContentPane().add(new JLabel("Nombre de Usuario :"));
        this.getContentPane().add(nombreDeUsuario);

        this.getContentPane().add(new JLabel("Nombre:"));
        this.getContentPane().add(nombre);

        this.getContentPane().add(new JLabel("Email:"));
        this.getContentPane().add(email);

        this.getContentPane().add(new JLabel("Domicilio :"));
        this.getContentPane().add(domicilio);

        this.getContentPane().add(new JLabel("Password:"));
        this.getContentPane().add(password);

        popularCampos(usuario);

        btnAgregarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (dni.getText() != null && nombreDeUsuario.getText() != null && domicilio.getText() != null
                        && password.getText() != null) {
                    controladorUsuario.modificacionUsuario(Integer.parseInt(dni.getText()),nombreDeUsuario.getText(),email.getText(),
                            password.getText(),nombre.getText(),domicilio.getText(),null);

                    JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");

                    reset();

                    modelo.fireTableDataChanged();

                    dispose();
                }
            }
        });
        this.getContentPane().add(btnAgregarUsuario);

        btnAgregarUsuario.setMaximumSize(getMaximumSize());
    }


    private void popularCampos(Usuario usuario) {

        this.dni.setText(usuario.getDni().toString());
        this.nombreDeUsuario.setText(usuario.getNombreDeUsuario());
        this.nombre.setText(usuario.getNombre());
        this.email.setText(usuario.getEmail());
        this.domicilio.setText(usuario.getDomicilio());
        this.password.setText(usuario.getPassword());
    }

    public void reset() {

        this.dni.setText("");
        this.nombreDeUsuario.setText("");
        this.email.setText("");
        this.domicilio.setText("");
        this.password.setText("");
    }
}