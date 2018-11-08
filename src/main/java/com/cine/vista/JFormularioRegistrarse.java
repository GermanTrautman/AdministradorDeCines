package com.cine.vista;

import com.cine.controlador.ControladorRolUsuario;
import com.cine.controlador.ControladorUsuario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JFormularioRegistrarse extends JFormularioBase {
    private JTextField txtNombreDeUsuario;
    private JTextField txtEmail;
    private JTextField txtPassword;
    private JTextField txtNombre;
    private JTextField txtDomicilio;
    private JTextField txtDNI;
    private JTextField txtFechaDeNacimiento;
    private List<String> roles;

    /**
     * Create the panel.
     */
    public JFormularioRegistrarse() {
        setLayout(null);

        txtNombreDeUsuario = new JTextField();
        txtNombreDeUsuario.setBounds(467, 195, 256, 26);
        add(txtNombreDeUsuario);
        txtNombreDeUsuario.setColumns(10);

        txtEmail = new JTextField();
        txtEmail.setBounds(467, 237, 256, 26);
        add(txtEmail);
        txtEmail.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setBounds(467, 279, 256, 26);
        add(txtPassword);
        txtPassword.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setBounds(467, 321, 256, 26);
        add(txtNombre);
        txtNombre.setColumns(10);

        txtDomicilio = new JTextField();
        txtDomicilio.setBounds(467, 363, 256, 26);
        add(txtDomicilio);
        txtDomicilio.setColumns(10);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(223, 670, 115, 29);
        add(btnCrear);
        btnCrear.addActionListener(e -> {
            ControladorUsuario.getInstance().altaUsuario(Integer.parseInt(txtDNI.getText()), txtNombreDeUsuario.getText(), txtEmail.getText(), txtPassword.getText(), txtNombre.getText()
                    , txtDomicilio.getText(), txtFechaDeNacimiento.getText());

            roles = new ArrayList<>();
            roles.add("Cliente");

            ControladorRolUsuario.getInstance().altaRolUsuario(Integer.parseInt(txtDNI.getText()), roles);
        });

        JLabel lblAltaUsuarios = new JLabel("Alta Usuarios");
        lblAltaUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        lblAltaUsuarios.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblAltaUsuarios.setBounds(0, 122, 1024, 38);
        add(lblAltaUsuarios);

        JLabel lblDNI = new JLabel("DNI");
        lblDNI.setBounds(223, 408, 229, 20);
        add(lblDNI);

        JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
        lblNombreDeUsuario.setBounds(223, 198, 229, 20);
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

        txtDNI = new JTextField();
        txtDNI.setBounds(467, 405, 256, 26);
        add(txtDNI);
        txtDNI.setColumns(10);

        txtFechaDeNacimiento = new JTextField();
        txtFechaDeNacimiento.setBounds(467, 447, 256, 26);
        add(txtFechaDeNacimiento);
        txtFechaDeNacimiento.setColumns(10);

        JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
        lblFechaDeNacimiento.setBounds(223, 450, 229, 20);
        add(lblFechaDeNacimiento);

        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Cliente");
        chckbxNewCheckBox_1.setEnabled(false);
        chckbxNewCheckBox_1.setSelected(true);
        chckbxNewCheckBox_1.setBounds(467, 485, 256, 29);
        add(chckbxNewCheckBox_1);

        JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Vendedor Boleter\u00EDa");
        chckbxNewCheckBox_2.setEnabled(false);
        chckbxNewCheckBox_2.setBounds(467, 522, 256, 29);
        add(chckbxNewCheckBox_2);

        JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Operador");
        chckbxNewCheckBox_3.setEnabled(false);
        chckbxNewCheckBox_3.setBounds(467, 559, 256, 29);
        add(chckbxNewCheckBox_3);

        JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Administrador");
        chckbxNewCheckBox_4.setEnabled(false);
        chckbxNewCheckBox_4.setBounds(467, 596, 256, 29);
        add(chckbxNewCheckBox_4);

        JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Agente Comercial");
        chckbxNewCheckBox_5.setEnabled(false);
        chckbxNewCheckBox_5.setBounds(467, 633, 256, 29);
        add(chckbxNewCheckBox_5);

    }


}