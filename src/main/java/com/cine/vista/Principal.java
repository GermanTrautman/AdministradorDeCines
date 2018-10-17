package com.cine.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cine.controlador.ControladorUsuario;

import javax.swing.JButton;

public class Principal {

	private ControladorUsuario controladorUsuario = new ControladorUsuario();

	private JFrame framePrincipal;

	private JPanel panel;

	private JTextField textField_nombre_de_usuario;
	private JTextField textField_email;
	private JTextField textField_password;
	private JTextField textField_nombre;
	private JTextField textField_domicilio;
	private JTextField textField_dni;
	private JTextField textField_nacimiento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.framePrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		framePrincipal = new JFrame();
		framePrincipal.setBounds(100, 100, 1920, 1080);
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

		JMenuItem mntmModificacion = new JMenuItem("Modificación");
		mnUsuarios.add(mntmModificacion);

		panel = new JPanel();
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

				if (textField_nombre_de_usuario.getText() != null && textField_email.getText() != null
						&& textField_email.getText() != null && textField_password.getText() != null
						&& textField_nombre.getText() != null && textField_domicilio.getText() != null
						&& textField_dni.getText() != null && textField_nacimiento.getText() != null) {
					controladorUsuario.altaUsuario(textField_nombre_de_usuario.getText(), textField_email.getText(),
							textField_password.getText(), textField_nombre.getText(), textField_domicilio.getText(),
							textField_dni.getText(), textField_nacimiento.getText());
				}
			}
		});
		panel.add(btnCrearUsuario);
	}

}
