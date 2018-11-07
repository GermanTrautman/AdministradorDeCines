package com.cine.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import com.cine.controlador.ControladorSala;
import com.cine.modelo.AsientoFisico;
import com.cine.utilidades.Estado;

public class JFormularioAsientos extends JFormularioBase {

	private static final long serialVersionUID = -2608943734882268555L;
	
	private Set<AsientoFisico> asientos = new HashSet<>();
	
	private JButton btnGuardar = new JButton();

	public JFormularioAsientos(String nombreDeSala, Integer cantidadDeFilas, Integer cantidadDeAsientosPorFila) {

		getContentPane().setLayout(new GridLayout(cantidadDeFilas, cantidadDeAsientosPorFila));

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
				getContentPane().add(boton);
			}
		}
		
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ControladorSala.getInstance().modificarAsientos(nombreDeSala, asientos);
			}
		});
	}

	private void agregarOQuitarSala(String nombreDeSala, String filaYAsiento) {
		
		String[] filaYAsientoPartido = filaYAsiento.split(Pattern.quote(","));
		Integer fila = Integer.parseInt(filaYAsientoPartido[0]);
		Integer numeroDeAsiento = Integer.parseInt(filaYAsientoPartido[1]);
		
		AsientoFisico asientoFisico = new AsientoFisico(nombreDeSala, fila, numeroDeAsiento, Estado.ACTIVO);
		
		boolean seAgregoElAsiento = asientos.add(asientoFisico);
		
		if (!seAgregoElAsiento) {
			asientos.remove(asientoFisico);
		}
	}
}
