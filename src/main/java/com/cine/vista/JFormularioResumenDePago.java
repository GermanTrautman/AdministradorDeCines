package com.cine.vista;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.cine.modelo.Venta;
import javax.swing.JTextField;

public class JFormularioResumenDePago extends JFormularioBase {

	private static final long serialVersionUID = -5812919621294989706L;

	public JFormularioResumenDePago(Venta venta) {

		getContentPane().setLayout(null);

		JLabel lblResumenDePago = new JLabel("Resumen de pago");
		lblResumenDePago.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumenDePago.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblResumenDePago.setBounds(0, 13, 1024, 38);
		this.getContentPane().add(lblResumenDePago);
		
		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(257, 141, 211, 22);
		getContentPane().add(lblEstablecimiento);
		
		JLabel lblNewLabel = new JLabel("Pelicula");
		lblNewLabel.setBounds(257, 191, 211, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(257, 234, 211, 16);
		getContentPane().add(lblDia);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(509, 144, 224, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(509, 191, 224, 16);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(509, 234, 224, 16);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblHorario = new JLabel("Horario");
		lblHorario.setBounds(257, 280, 211, 16);
		getContentPane().add(lblHorario);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(509, 280, 224, 16);
		getContentPane().add(lblNewLabel_4);
	}
}
