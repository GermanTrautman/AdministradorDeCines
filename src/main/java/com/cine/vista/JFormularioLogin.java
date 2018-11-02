package com.cine.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioLogin extends JFormularioBase {
    private JTextField txtNombreDeUsuario;
    private JPasswordField txtPassword;

    /**
     * Create the panel.
     */
    public JFormularioLogin() {
        setLayout(null);

        txtNombreDeUsuario = new JTextField();
        txtNombreDeUsuario.setBounds(467, 237, 256, 26);
        add(txtNombreDeUsuario);
        txtNombreDeUsuario.setColumns(10);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(223, 670, 115, 29);
        add(btnIngresar);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame j = new JFormularioPrincipal();
                j.setVisible(true);
                hide();
            }
        });

        JLabel lbAdministracionDeCines = new JLabel("Administraci\u00F3n de Cines");
        lbAdministracionDeCines.setHorizontalAlignment(SwingConstants.CENTER);
        lbAdministracionDeCines.setFont(new Font("Tahoma", Font.BOLD, 26));
        lbAdministracionDeCines.setBounds(0, 122, 1024, 38);
        add(lbAdministracionDeCines);

        JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
        lblNombreDeUsuario.setBounds(223, 240, 229, 20);
        add(lblNombreDeUsuario);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(223, 324, 229, 20);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(467, 321, 256, 26);
        add(txtPassword);

        JButton btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(608, 670, 115, 29);
        add(btnRegistrarse);

    }

}
