package com.cine.vista;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.cine.controlador.ControladorEstablecimiento;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFormularioAltaEstablecimiento extends JFormularioBase {

	private static final long serialVersionUID = 1L;

	private JTextField cuit = new JTextField();
	private JTextField nombre = new JTextField();
	private JTextField domicilio = new JTextField();
	private JTextField capacidad = new JTextField();
	
	private JButton btnAgregarEstablecimiento = new JButton("Agregar Establecimiento");

	public JFormularioAltaEstablecimiento() {

		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.getContentPane().add(new JLabel("CUIT:"));
		this.getContentPane().add(cuit);

		this.getContentPane().add(new JLabel("Nombre :"));
		this.getContentPane().add(nombre);

		this.getContentPane().add(new JLabel("Domicilio :"));
		this.getContentPane().add(domicilio);

		this.getContentPane().add(new JLabel("Capacidad:"));
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
		this.getContentPane().add(btnAgregarEstablecimiento);

		btnAgregarEstablecimiento.setMaximumSize(getMaximumSize());
	}

	public void reset() {

		this.cuit.setText("");
		this.nombre.setText("");
		this.domicilio.setText("");
		this.capacidad.setText("");
	}
}