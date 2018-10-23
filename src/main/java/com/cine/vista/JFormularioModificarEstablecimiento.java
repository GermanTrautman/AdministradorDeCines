package com.cine.vista;

import com.cine.controlador.ControladorCine;
import com.cine.modelo.Establecimiento;
import com.cine.vista.modelo.ModeloTablaEstablecimiento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioModificarEstablecimiento extends JFormularioBase {

	private static final long serialVersionUID = 1L;

	private JTextField cuit = new JTextField();
	private JTextField nombre = new JTextField();
	private JTextField domicilio = new JTextField();
	private JTextField capacidad = new JTextField();

	private JButton btnAgregarEstablecimiento = new JButton("Guardar cambios");

	private ControladorCine controladorCine = ControladorCine.getInstance();

	public JFormularioModificarEstablecimiento(ModeloTablaEstablecimiento modelo, Integer unCuit ) {

		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		Establecimiento establecimiento = (Establecimiento) controladorCine.buscarEnCache(unCuit);

		this.getContentPane().add(new JLabel("CUIT:"));
		this.getContentPane().add(cuit).setEnabled(false);

		this.getContentPane().add(new JLabel("Nombre :"));
		this.getContentPane().add(nombre);

		this.getContentPane().add(new JLabel("Domicilio :"));
		this.getContentPane().add(domicilio);

		this.getContentPane().add(new JLabel("Capacidad:"));
		this.getContentPane().add(capacidad);

		popularCampos(establecimiento);

		btnAgregarEstablecimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cuit.getText() != null && nombre.getText() != null && domicilio.getText() != null
						&& capacidad.getText() != null) {
					controladorCine.modificacionEstablecimiento(Integer.parseInt(cuit.getText()), nombre.getText(),
							domicilio.getText(), Integer.parseInt(capacidad.getText()));

					JOptionPane.showMessageDialog(null, "Establecimiento modificado correctamente");

					reset();

					modelo.fireTableDataChanged();
					
					dispose();
				}
			}
		});
		this.getContentPane().add(btnAgregarEstablecimiento);

		btnAgregarEstablecimiento.setMaximumSize(getMaximumSize());
	}


	private void popularCampos(Establecimiento establecimiento) {

		this.cuit.setText(establecimiento.getCuit().toString());
		this.nombre.setText(establecimiento.getNombre());
		this.domicilio.setText(establecimiento.getDomicilio());
		this.capacidad.setText(establecimiento.getCapacidad().toString());
	}

	public void reset() {

		this.cuit.setText("");
		this.nombre.setText("");
		this.domicilio.setText("");
		this.capacidad.setText("");
	}
}