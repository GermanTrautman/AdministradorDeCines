package com.cine.vista;

import com.cine.controlador.ControladorUsuario;
import com.cine.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioModificarUsuario extends JFormularioBase {

    private JTextField nombreDeUsuario;
    private JTextField email;
    private JPasswordField password;
    private JTextField nombre;
    private JTextField domicilio;
    private JTextField dni;
    private JTextField fechaDeNacimiento;

    /**
     * Create the panel.
     */
    public JFormularioModificarUsuario() {
        setLayout(null);

        nombreDeUsuario = new JTextField();
        nombreDeUsuario.setBounds(467, 405, 256, 26);
        add(nombreDeUsuario);
        nombreDeUsuario.setColumns(10);

        email = new JTextField();
        email.setBounds(467, 237, 256, 26);
        add(email);
        email.setColumns(10);

        password = new JPasswordField();
        password.setBounds(467, 279, 256, 26);
        add(password);
        password.setColumns(10);

        nombre = new JTextField();
        nombre.setBounds(467, 321, 256, 26);
        add(nombre);
        nombre.setColumns(10);

        domicilio = new JTextField();
        domicilio.setBounds(467, 363, 256, 26);
        add(domicilio);
        domicilio.setColumns(10);

        fechaDeNacimiento = new JTextField();
        fechaDeNacimiento.setBounds(467, 447, 256, 26);
        add(fechaDeNacimiento);
        fechaDeNacimiento.setColumns(10);

        dni = new JTextField();
        dni.setBounds(467, 195, 256, 26);
        add(dni);
        dni.setColumns(10);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(223, 670, 115, 29);
        add(btnModificar);

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario(Integer.parseInt(dni.getText()), nombreDeUsuario.getText(), email.getText(), password.getText(),
                        nombre.getText(), domicilio.getText(), fechaDeNacimiento.getText());
                ControladorUsuario.getInstance().actualizarCache(usuario);
                reset();
                JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
            }
        });

        JLabel lblModificacionUsuarios = new JLabel("Modificaci\u00F3n Usuarios");
        lblModificacionUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        lblModificacionUsuarios.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblModificacionUsuarios.setBounds(0, 122, 1024, 38);
        add(lblModificacionUsuarios);

        JLabel lblDNI = new JLabel("DNI");
        lblDNI.setBounds(223, 198, 229, 20);
        add(lblDNI);

        JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
        lblNombreDeUsuario.setBounds(223, 408, 229, 20);
        add(lblNombreDeUsuario);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(223, 240, 229, 20);
        add(lblEmail);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(223, 282, 229, 20);
        add(lblPassword);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(223, 324, 229, 20);
        add(lblNombre);

        JLabel lblDomicilio = new JLabel("Domicilio");
        lblDomicilio.setBounds(223, 366, 229, 20);
        add(lblDomicilio);

        JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
        lblFechaDeNacimiento.setBounds(223, 450, 229, 20);
        add(lblFechaDeNacimiento);

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Cliente");
        chckbxNewCheckBox_1.setBounds(467, 485, 256, 29);
        add(chckbxNewCheckBox_1);

        JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Vendedor Boleter\u00EDa");
        chckbxNewCheckBox_2.setBounds(467, 522, 256, 29);
        add(chckbxNewCheckBox_2);

        JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Operador");
        chckbxNewCheckBox_3.setBounds(467, 559, 256, 29);
        add(chckbxNewCheckBox_3);

        JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Administrador");
        chckbxNewCheckBox_4.setBounds(467, 596, 256, 29);
        add(chckbxNewCheckBox_4);

        JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Agente Comercial");
        chckbxNewCheckBox_5.setBounds(467, 633, 256, 29);
        add(chckbxNewCheckBox_5);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(745, 194, 115, 29);
        add(btnBuscar);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usr = ControladorUsuario.getInstance().buscarEnCache(Integer.parseInt(dni.getText()));
                if (usr == null) {
                    usr = ControladorUsuario.getInstance().buscarUsuarioEnDb(Integer.parseInt(dni.getText()));
                }

                popularCampos(usr);
                dni.setEnabled(false);
            }
        });

    }


    private void popularCampos(Usuario usuario) {

        this.dni.setText(usuario.getDni().toString());
        this.nombreDeUsuario.setText(usuario.getNombreDeUsuario());
        this.nombre.setText(usuario.getNombre());
        this.email.setText(usuario.getEmail());
        this.domicilio.setText(usuario.getDomicilio());
        this.password.setText(usuario.getPassword());
        this.fechaDeNacimiento.setText(usuario.getFechaDeNacimiento());
    }

    public void reset() {

        this.dni.setText("");
        this.nombreDeUsuario.setText("");
        this.nombre.setText("");
        this.email.setText("");
        this.domicilio.setText("");
        this.password.setText("");
        this.fechaDeNacimiento.setText("");
    }
}