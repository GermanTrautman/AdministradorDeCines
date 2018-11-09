package com.cine.vista;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorSala;
import com.cine.modelo.Sala;

public class JFormularioAsientosBajaSala extends JFormularioBase {

	private static final long serialVersionUID = -2608943734882268555L;
	
	private JPanel panelDeAsientos = new JPanel();
	
	private Integer cantidadDeFilas = 1;
	private Integer cantidadDeAsientosPorFila = 1;

	public JFormularioAsientosBajaSala(String nombreDeSala) {
		
		Sala sala = ControladorSala.getInstance().buscar(nombreDeSala);
		
		obtenerCantidadDeFilasYAsientos(sala);
		
		getContentPane().setLayout(null);
		
		JLabel lblAltaAsientos = new JLabel("Asientos");
		lblAltaAsientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltaAsientos.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltaAsientos.setBounds(60, 30, 1024, 38);
		this.getContentPane().add(lblAltaAsientos);
		
		panelDeAsientos.setLayout(new GridLayout(cantidadDeFilas, cantidadDeAsientosPorFila));
		panelDeAsientos.setBounds(100, 100, 1000, 700);

		for (int i = 1; i <= cantidadDeFilas; i++) {

			for (int j = 1; j <= cantidadDeAsientosPorFila; j++) {

				JToggleButton boton = new JToggleButton("F: " + i + " A: " + j);
				
				if (sala.getAsientos()[i][j] == null) {
					boton.setEnabled(false);
				}
				
				boton.setName(i + "," + j);
				panelDeAsientos.add(boton);
			}
		}
		
		getContentPane().add(panelDeAsientos);
	}

	private void obtenerCantidadDeFilasYAsientos(Sala sala) {
		
		int filaMasGrande = 0;
		int numeroDeAsientosMasGrande = 0;
		
		for (int i = 0; i < sala.getAsientos().length; i++) {
			
			for (int j = 0; j < sala.getAsientos().length; j++) {
				
				if (sala.getAsientos()[i][j] != null) {
					
					if (i > filaMasGrande) {
						cantidadDeFilas = i;
					}
					
					if (j > numeroDeAsientosMasGrande) {
						cantidadDeAsientosPorFila = j;
					}
				}
			}
		}
		
	}
}