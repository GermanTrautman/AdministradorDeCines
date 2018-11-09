package com.cine.vista;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorSala;
import com.cine.modelo.AsientoFisico;
import com.cine.utilidades.Estado;

public class JFormularioAsientosAltaSala extends JFormularioBase {

	private static final long serialVersionUID = -2608943734882268555L;
	
	private AsientoFisico[][] asientos = new AsientoFisico[100][100];
	
	private JPanel panelDeAsientos = new JPanel();
	
	private JButton btnGuardar = new JButton();

	public JFormularioAsientosAltaSala(String nombreDeSala, Integer cantidadDeFilas, Integer cantidadDeAsientosPorFila) {

		getContentPane().setLayout(null);
		
		JLabel lblAltaAsientos = new JLabel("Alta asientos");
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

		for (int i = 0; i < cantidadDeFilas; i++) {

			for (int j = 0; j < cantidadDeAsientosPorFila; j++) {

				Integer numeroFila = i + 1;
				Integer numeroAsientoPorFila = j + 1;

				JToggleButton boton = new JToggleButton("F: " + numeroFila + " A: " + numeroAsientoPorFila);
				boton.setName(numeroFila.toString() + "," + numeroAsientoPorFila.toString());
				boton.addActionListener(actionListener);
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
