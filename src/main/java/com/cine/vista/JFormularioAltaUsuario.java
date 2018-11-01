package com.cine.vista;

import com.cine.controlador.ControladorRol;
import com.cine.controlador.ControladorUsuario;
import com.cine.modelo.Rol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JFormularioAltaUsuario extends JFormularioBase {

    private static final long serialVersionUID = 1L;

    private ControladorUsuario controladorUsuario = ControladorUsuario.getInstance();
    private ControladorRol controladorRol = ControladorRol.getInstance();


    private JTextField dni;
    private JTextField nombreDeUsuario;
    private JTextField email;
    private JPasswordField password;
    private JTextField nombre;
    private JTextField domicilio;
    private JComboBox<Rol> comboRoles = new JComboBox<>();

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private JFormattedTextField fecha = new JFormattedTextField(format);

    /**
     * Create the panel.
     */
    public JFormularioAltaUsuario() {
        setLayout(null);

        dni = new JTextField();
        dni.setBounds(467, 195, 256, 26);
        add(dni);
        dni.setColumns(10);

        nombreDeUsuario = new JTextField();
        nombreDeUsuario.setBounds(467, 237, 256, 26);
        add(nombreDeUsuario);
        nombreDeUsuario.setColumns(10);

        email = new JTextField();
        email.setBounds(467, 279, 256, 26);
        add(email);
        email.setColumns(10);

        password = new JPasswordField();
        password.setBounds(467, 321, 256, 26);
        add(password);
        password.setColumns(10);

        nombre = new JTextField();
        nombre.setBounds(467, 363, 256, 26);
        add(nombre);
        nombre.setColumns(10);

        domicilio = new JTextField();
        domicilio.setBounds(467, 450, 256, 26);
        add(domicilio);
        domicilio.setColumns(10);

        fecha.setBounds(467, 410, 256, 26);
        fecha.setToolTipText("dd/MM/yyyy");
        add(fecha);
        fecha.setColumns(10);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(223, 670, 115, 29);
        add(btnCrear);

        this.getContentPane().add(new JLabel());
        this.getContentPane().add(fecha);


        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (dni.getText() != null && nombreDeUsuario.getText() != null && domicilio.getText() != null
                        && nombre.getText() != null && password.getText() != null && fecha.getText() != null) {

                    controladorUsuario.altaUsuario(Integer.parseInt(dni.getText()), nombreDeUsuario.getText(), email.getText(), password.getText(),
                            nombre.getText(), domicilio.getText(), fecha.getText());

                }
                JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
                reset();
            }
        });

        this.getContentPane().add(btnCrear);

        btnCrear.setMaximumSize(getMaximumSize());


        JLabel alta_establecimientos = new JLabel("Alta Usuario");
        alta_establecimientos.setHorizontalAlignment(SwingConstants.CENTER);
        alta_establecimientos.setFont(new Font("Tahoma", Font.BOLD, 26));
        alta_establecimientos.setBounds(0, 122, 1024, 38);
        add(alta_establecimientos);

        JLabel lblDni = new JLabel("DNI");
        lblDni.setBounds(223, 195, 229, 20);
        add(lblDni);

        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario");
        lblNombreUsuario.setBounds(223, 237, 229, 20);
        add(lblNombreUsuario);

        JLabel email = new JLabel("Nombre");
        email.setBounds(223, 450, 229, 20);
        add(email);

        JLabel lblDomicilio = new JLabel("Email");
        lblDomicilio.setBounds(223, 279, 229, 20);
        add(lblDomicilio);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(223, 321, 229, 20);
        add(lblPassword);

        JLabel domicilio = new JLabel("Domicilio");
        domicilio.setBounds(223, 365, 229, 20);
        add(domicilio);

        JLabel lblFechanac = new JLabel("Fecha de nacimiento");
        lblFechanac.setBounds(223, 410, 229, 20);
        add(lblFechanac);


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
