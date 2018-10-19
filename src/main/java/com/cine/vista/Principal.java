package com.cine.vista;

import com.cine.controlador.ControladorUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {

	private ControladorUsuario controladorUsuario = new ControladorUsuario();

	public JFrame framePrincipal;
	private JPanel panel;
	private JTextField textField_nombre_de_usuario;
	private JTextField textField_email;
	private JTextField textField_password;
	private JTextField textField_nombre;
	private JTextField textField_domicilio;
	private JTextField textField_dni;
	private JTextField textField_nacimiento;
	private JTextField textField_establecimiento_cuit;
	private JTextField textField_establecimiento_nombre;
	private JTextField textField_establecimiento_direccion;
	private JTextField textField_establecimiento_cantidad_salas;
	private JTextField textField_establecimiento_capacidad_total;

	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		framePrincipal = new JFrame();
		framePrincipal.getContentPane().setLayout(new GridLayout());
		framePrincipal.setBounds(50, 50, 500, 500);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		framePrincipal.setJMenuBar(menuBar);

		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);

		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
			}
		});
		mnUsuarios.add(mntmAlta);

		JMenuItem mntmBaja = new JMenuItem("Baja");
		mnUsuarios.add(mntmBaja);

		JMenuItem mntmModificacion = new JMenuItem("Modificacion");
		mnUsuarios.add(mntmModificacion);
		
		JMenu mnEstablecimientos = new JMenu("Establecimientos");
		menuBar.add(mnEstablecimientos);
		
		JMenuItem mntmAlta_1 = new JMenuItem("Alta");
		mntmAlta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnEstablecimientos.add(mntmAlta_1);
		
		JMenuItem mntmBaja_1 = new JMenuItem("Baja");
		mnEstablecimientos.add(mntmBaja_1);
		
		JMenuItem mntmModificacion_1 = new JMenuItem("Modificacion");
		mnEstablecimientos.add(mntmModificacion_1);

		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		framePrincipal.getContentPane().add(panel, BorderLayout.SOUTH);

		panel.setVisible(false);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		panel.add(lblNombreDeUsuario);

		textField_nombre_de_usuario = new JTextField();
		panel.add(textField_nombre_de_usuario);
		textField_nombre_de_usuario.setColumns(10);

		JLabel lblEmail = new JLabel("email");
		panel.add(lblEmail);

		textField_email = new JTextField();
		panel.add(textField_email);
		textField_email.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		panel.add(lblPassword);

		textField_password = new JTextField();
		panel.add(textField_password);
		textField_password.setColumns(10);

		JLabel lblNombre = new JLabel("nombre");
		panel.add(lblNombre);

		textField_nombre = new JTextField();
		panel.add(textField_nombre);
		textField_nombre.setColumns(10);

		JLabel lblDomicilio = new JLabel("Domicilio");
		panel.add(lblDomicilio);

		textField_domicilio = new JTextField();
		panel.add(textField_domicilio);
		textField_domicilio.setColumns(10);

		JLabel lblDni = new JLabel("DNI");
		panel.add(lblDni);

		textField_dni = new JTextField();
		panel.add(textField_dni);
		textField_dni.setColumns(10);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		panel.add(lblFechaDeNacimiento);

		textField_nacimiento = new JTextField();
		panel.add(textField_nacimiento);
		textField_nacimiento.setColumns(10);

		JButton btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			/*	if (textField_nombre_de_usuario.getText() != null && textField_email.getText() != null
						&& textField_email.getText() != null && textField_password.getText() != null
						&& textField_nombre.getText() != null && textField_domicilio.getText() != null
						&& textField_dni.getText() != null && textField_nacimiento.getText() != null) {
					controladorUsuario.altaUsuario(textField_nombre_de_usuario.getText(), textField_email.getText(),
							textField_password.getText(), textField_nombre.getText(), textField_domicilio.getText(),
							textField_dni.getText(), textField_nacimiento.getText());
				}*/
			}
		});
		panel.add(btnCrearUsuario);
		
		JPanel panel_alta_establecimiento = new JPanel();
		framePrincipal.getContentPane().add(panel_alta_establecimiento);
		
		JLabel lblCuit = new JLabel("CUIT");
		panel_alta_establecimiento.add(lblCuit);
		
		textField_establecimiento_cuit = new JTextField();
		panel_alta_establecimiento.add(textField_establecimiento_cuit);
		textField_establecimiento_cuit.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		panel_alta_establecimiento.add(lblNewLabel);
		
		textField_establecimiento_nombre = new JTextField();
		panel_alta_establecimiento.add(textField_establecimiento_nombre);
		textField_establecimiento_nombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Domicilio");
		panel_alta_establecimiento.add(lblNewLabel_1);
		
		textField_establecimiento_direccion = new JTextField();
		panel_alta_establecimiento.add(textField_establecimiento_direccion);
		textField_establecimiento_direccion.setColumns(10);
		
		JButton btnNewButton = new JButton("Crear establecimiento");
		panel_alta_establecimiento.add(btnNewButton);
	}

}
