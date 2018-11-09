package com.cine.vista;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorSala;
import com.cine.modelo.AsientoFisico;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class JFormularioAsientosModificacionSala extends JFormularioBase {

	private static final long serialVersionUID = 2848016781743397417L;

	private AsientoFisico[][] asientos = new AsientoFisico[100][100];
	
	private JPanel panelDeAsientos = new JPanel();
	
	private JButton btnGuardar = new JButton();

	private Integer cantidadDeFilas = 1;
	private Integer cantidadDeAsientosPorFila = 1;

	public JFormularioAsientosModificacionSala(String nombreDeSala) {
		
		Sala sala = ControladorSala.getInstance().buscar(nombreDeSala);
		
		obtenerCantidadDeFilasYAsientos(sala);

		getContentPane().setLayout(null);
		
		JLabel lblAltaAsientos = new JLabel("Modificacion asientos");
		lblAltaAsientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltaAsientos.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltaAsientos.setBounds(60, 30, 1024, 38);
		this.getContentPane().add(lblAltaAsientos);
		
		panelDeAsientos.setLayout(new GridLayout(cantidadDeFilas, cantidadDeAsientosPorFila));
		panelDeAsientos.setBounds(100, 100, 1000, 700);
		
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				JToggleButton boton = (JToggleButton) e.getSource();
				String filaYAsiento = boton.getName();
				
				agregarOQuitarSala(nombreDeSala, filaYAsiento);
			}
		};

		for (int i = 1; i <= cantidadDeFilas; i++) {

			for (int j = 1; j <= cantidadDeAsientosPorFila; j++) {

				JToggleButton boton = new JToggleButton("F: " + i + " A: " + j);
				boton.setName(i + "," + j);
				boton.addActionListener(actionListener);
				
				if (sala.getAsientos()[i][j] != null) {
					boton.doClick();
				}
				
				panelDeAsientos.add(boton);
			}
		}
		
		btnGuardar.setText("Guardar");
		btnGuardar.setBounds(550, 870, 115, 29);
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ControladorSala.getInstance().modificarAsientos(nombreDeSala, asientos);
				
				JOptionPane.showMessageDialog(null, "Se han guardado los asientos");
				
				dispose();
			}
		});
		
		getContentPane().add(panelDeAsientos);
		getContentPane().add(btnGuardar);
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
	
	private void agregarOQuitarSala(String nombreDeSala, String filaYAsiento) {
		
		String[] filaYAsientoPartido = filaYAsiento.split(Pattern.quote(","));
		Integer fila = Integer.parseInt(filaYAsientoPartido[0]);
		Integer numeroDeAsiento = Integer.parseInt(filaYAsientoPartido[1]);
		
		AsientoFisico asientoFisico = new AsientoFisico(nombreDeSala, fila, numeroDeAsiento, Estado.ACTIVO);
		
		if (asientos != null) {
			
			if (asientos[fila][numeroDeAsiento] == null) {
				asientos[fila][numeroDeAsiento] = asientoFisico;
			} else {
				asientos[fila][numeroDeAsiento] = null;
			}
		}
	}
}
