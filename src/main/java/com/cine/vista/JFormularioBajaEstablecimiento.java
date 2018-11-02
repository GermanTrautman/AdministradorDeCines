package com.cine.vista;

import javax.swing.*;

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.modelo.Establecimiento;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class JFormularioBajaEstablecimiento extends JFormularioBase {

	private static final long serialVersionUID = 1488800648208098796L;

	private JTextField cuit = new JTextField();
	private JTextField nombre = new JTextField();
	private JTextField domicilio = new JTextField();
	private JTextField capacidad = new JTextField();
	
	public JFormularioBajaEstablecimiento() {
		
		getContentPane().setLayout(null);
		
		JLabel lblAltasalas = new JLabel("Baja Establecimiento");
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
				
				if (cuit.getText() != null) {

					Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(Integer.parseInt(cuit.getText()));
					popularCampos(establecimiento);
				}
			}
		});
		btnBuscar.setBounds(745, 194, 115, 29);
		this.getContentPane().add(btnBuscar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(223, 237, 229, 20);
		this.getContentPane().add(lblNombre);
		
		this.nombre = new JTextField();
		this.nombre.setEnabled(false);
		this.nombre.setBounds(467, 237, 256, 26);
		this.nombre.setColumns(10);
		this.getContentPane().add(this.nombre);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(223, 279, 229, 20);
		this.getContentPane().add(lblDomicilio);
		
		this.domicilio = new JTextField();
		this.domicilio.setEnabled(false);
		this.domicilio.setBounds(467, 279, 256, 26);
		this.domicilio.setColumns(10);
		this.getContentPane().add(this.domicilio);
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(223, 321, 229, 20);
		this.getContentPane().add(lblCapacidad);
		
		this.capacidad = new JTextField();
		this.capacidad.setEnabled(false);
		this.capacidad.setBounds(467, 321, 256, 26);
		this.capacidad.setColumns(10);
		this.getContentPane().add(this.capacidad);

		JButton btnBorrarEstablecimiento = new JButton("Eliminar");
		btnBorrarEstablecimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (cuit.getText() != null) {

					ControladorEstablecimiento.getInstance().baja(Integer.parseInt(cuit.getText()));
					
					JOptionPane.showMessageDialog(null, "Establecimiento borrado correctamente");
					
					resetCampos();
				}
			}
		});
		btnBorrarEstablecimiento.setBounds(223, 670, 115, 29);
		this.getContentPane().add(btnBorrarEstablecimiento);
	}
	
	private void popularCampos(Establecimiento establecimiento) {

		if (establecimiento != null) {
			
			this.nombre.setText(establecimiento.getNombre());
			this.domicilio.setText(establecimiento.getDomicilio());
			this.capacidad.setText(establecimiento.getCapacidad().toString());
		}
	}
	
	private void resetCampos() {
		
		this.cuit.setText("");
		this.nombre.setText("");
		this.domicilio.setText("");
		this.capacidad.setText("");
	}
}
