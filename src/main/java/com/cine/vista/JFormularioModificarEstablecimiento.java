package com.cine.vista;

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.modelo.Establecimiento;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioModificarEstablecimiento extends JFormularioBase {

	private static final long serialVersionUID = 1L;

	private JTextField cuit = new JTextField();
	private JTextField nombre = new JTextField();
	private JTextField domicilio = new JTextField();
	private JTextField capacidad = new JTextField();

	private JButton btnGuardar = new JButton("Guardar");

	private ControladorEstablecimiento controladorCine = ControladorEstablecimiento.getInstance();

	public JFormularioModificarEstablecimiento() {

		getContentPane().setLayout(null);
		
		JLabel lblAltasalas = new JLabel("Modificaci\u00F3n Establecimientos");
		lblAltasalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltasalas.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltasalas.setBounds(0, 122, 1024, 38);
		this.getContentPane().add(lblAltasalas);

		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(223, 195, 229, 20);
		this.getContentPane().add(lblCuit);
		
		this.cuit = new JTextField();
		this.cuit.setBounds(467, 195, 256, 26);
		this.cuit.setColumns(10);
		this.getContentPane().add(this.cuit);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(Integer.parseInt(cuit.getText()));
				popularCampos(establecimiento);
			}
		});
		btnBuscar.setBounds(745, 194, 115, 29);
		this.getContentPane().add(btnBuscar);
		
		JButton btnLimpiarCampos = new JButton("Limpiar campos");
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				resetCampos();
			}
		});
		btnLimpiarCampos.setBounds(863, 194, 131, 29);
		getContentPane().add(btnLimpiarCampos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(223, 237, 229, 20);
		this.getContentPane().add(lblNombre);
		
		this.nombre = new JTextField();
		this.nombre.setBounds(467, 237, 256, 26);
		this.nombre.setColumns(10);
		this.nombre.setEnabled(false);
		this.getContentPane().add(this.nombre);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(223, 279, 229, 20);
		this.getContentPane().add(lblDomicilio);
		
		this.domicilio = new JTextField();
		this.domicilio.setBounds(467, 279, 256, 26);
		this.domicilio.setColumns(10);
		this.domicilio.setEnabled(false);
		this.getContentPane().add(this.domicilio);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(223, 321, 229, 20);
		this.getContentPane().add(lblCapacidad);
		
		this.capacidad = new JTextField();
		this.capacidad.setBounds(467, 321, 256, 26);
		this.capacidad.setColumns(10);
		this.capacidad.setEnabled(false);
		this.getContentPane().add(this.capacidad);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cuit.getText() != null && nombre.getText() != null && domicilio.getText() != null
						&& capacidad.getText() != null) {
					controladorCine.modificacion(Integer.parseInt(cuit.getText()), nombre.getText(),
							domicilio.getText(), Integer.parseInt(capacidad.getText()));

					JOptionPane.showMessageDialog(null, "Establecimiento modificado correctamente");
				}
			}
		});
		this.btnGuardar.setBounds(223, 670, 115, 29);
		this.getContentPane().add(btnGuardar);
		this.getContentPane().add(this.btnGuardar);
	}


	private void popularCampos(Establecimiento establecimiento) {

		if (establecimiento != null) {
			
			this.nombre.setText(establecimiento.getNombre());
			this.domicilio.setText(establecimiento.getDomicilio());
			this.capacidad.setText(establecimiento.getCapacidad().toString());

			this.cuit.setEnabled(false);
			this.nombre.setEnabled(true);
			this.domicilio.setEnabled(true);
			this.capacidad.setEnabled(true);
		}
	}
	
	private void resetCampos() {
		
		this.cuit.setText("");
		this.nombre.setText("");
		this.domicilio.setText("");
		this.capacidad.setText("");
		
		this.cuit.setEnabled(true);
		this.nombre.setEnabled(false);
		this.domicilio.setEnabled(false);
		this.capacidad.setEnabled(false);
	}
}