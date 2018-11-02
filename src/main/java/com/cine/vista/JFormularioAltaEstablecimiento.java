package com.cine.vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorEstablecimiento;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class JFormularioAltaEstablecimiento extends JFormularioBase {

	private static final long serialVersionUID = 1L;

	private JTextField cuit = new JTextField();
	private JTextField nombre = new JTextField();
	private JTextField domicilio = new JTextField();
	private JTextField capacidad = new JTextField();
	
	private JButton btnAgregarEstablecimiento = new JButton("Guardar");

	public JFormularioAltaEstablecimiento() {

		getContentPane().setLayout(null);
		
		JLabel lblAltasalas = new JLabel("Alta Establecimiento");
		lblAltasalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltasalas.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltasalas.setBounds(0, 122, 1024, 38);
		this.getContentPane().add(lblAltasalas);

		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(223, 195, 229, 20);
		this.getContentPane().add(lblCuit);
		
		this.cuit.setBounds(467, 195, 256, 26);
		this.cuit.setColumns(10);
		this.getContentPane().add(cuit);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(223, 237, 229, 20);
		this.getContentPane().add(lblNombre);

		this.nombre.setBounds(467, 237, 256, 26);
		this.nombre.setColumns(10);
		this.getContentPane().add(nombre);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(223, 279, 229, 20);
		this.getContentPane().add(lblDomicilio);
		
		this.domicilio.setBounds(467, 279, 256, 26);
		this.domicilio.setColumns(10);
		this.getContentPane().add(domicilio);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(223, 321, 229, 20);
		this.getContentPane().add(lblCapacidad);

		this.capacidad.setBounds(467, 321, 256, 26);
		this.capacidad.setColumns(10);
		this.getContentPane().add(capacidad);

		btnAgregarEstablecimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cuit.getText() != null && nombre.getText() != null && domicilio.getText() != null
						&& capacidad.getText() != null) {
					
					ControladorEstablecimiento.getInstance().alta(Integer.parseInt(cuit.getText()), nombre.getText(),
							domicilio.getText(), Integer.parseInt(capacidad.getText()));
					
					JOptionPane.showMessageDialog(null, "Establecimiento creado correctamente");

					reset();
				}
			}
		});
		this.btnAgregarEstablecimiento.setBounds(223, 670, 115, 29);
		this.getContentPane().add(btnAgregarEstablecimiento);
	}

	public void reset() {

		this.cuit.setText("");
		this.nombre.setText("");
		this.domicilio.setText("");
		this.capacidad.setText("");
	}
}