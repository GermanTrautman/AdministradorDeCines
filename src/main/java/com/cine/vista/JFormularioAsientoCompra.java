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

import com.cine.controlador.ControladorFuncion;
import com.cine.modelo.AsientoVirtual;
import com.cine.modelo.Funcion;
import com.cine.utilidades.EstadoVirtual;

public class JFormularioAsientoCompra extends JFormularioBase {

	private static final long serialVersionUID = -5861942336470487170L;
	
	private AsientoVirtual[][] asientosVirtualesTemporales = new AsientoVirtual[AsientoVirtual.FILAS][AsientoVirtual.ASIENTOSPORFILA];
	
	private JPanel panelDeAsientos = new JPanel();
	
	private JButton btnGuardar = new JButton();

	private Integer cantidadMinimaDeFilas = AsientoVirtual.FILAS;
	private Integer cantidadMinimaDeAsientosPorFila = AsientoVirtual.ASIENTOSPORFILA;
	private Integer cantidadMaximaDeFilas = 1;
	private Integer cantidadMaximaDeAsientosPorFila = 1;
	
	public JFormularioAsientoCompra(Funcion funcion) {
		
		clonarMatriz(funcion);
		
		obtenerCantidadDeFilasYAsientos(funcion);

		getContentPane().setLayout(null);
		
		JLabel lblSeleccionDeAsientos = new JLabel("Seleccion de asientos");
		lblSeleccionDeAsientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionDeAsientos.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblSeleccionDeAsientos.setBounds(60, 30, 1024, 38);
		this.getContentPane().add(lblSeleccionDeAsientos);
		
		panelDeAsientos.setLayout(new GridLayout(cantidadMaximaDeFilas - cantidadMinimaDeFilas + 1, cantidadMaximaDeAsientosPorFila - cantidadMinimaDeAsientosPorFila + 1));
		panelDeAsientos.setBounds(100, 100, 1000, 700);
		
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				JToggleButton boton = (JToggleButton) e.getSource();
				String filaYAsiento = boton.getName();
				
				reservarOLiberarAsiento(funcion, filaYAsiento);
			}
		};

		for (int i = cantidadMinimaDeFilas; i <= cantidadMaximaDeFilas; i++) {

			for (int j = cantidadMinimaDeAsientosPorFila; j <= cantidadMaximaDeAsientosPorFila; j++) {

				JToggleButton boton = new JToggleButton("F: " + i + " A: " + j);
				boton.setName(i + "," + j);
				boton.addActionListener(actionListener);
				
				if (funcion.getAsientoVirtual()[i][j] == null) {
					boton.setEnabled(false);
				} else if (funcion.getAsientoVirtual()[i][j].getEstado().equals(EstadoVirtual.OCUPADO)){
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
				
				ControladorFuncion.getInstance().modificarAsientos(funcion.getId(), asientosVirtualesTemporales);
				
				JOptionPane.showMessageDialog(null, "Se han guardado los asientos");
				
				dispose();
			}
		});
		
		getContentPane().add(panelDeAsientos);
		getContentPane().add(btnGuardar);
	}

	private void clonarMatriz(Funcion funcion) {

		for (int i = 1; i < AsientoVirtual.FILAS; i++) {
			for (int j = 1; j < AsientoVirtual.ASIENTOSPORFILA; j++) {
				asientosVirtualesTemporales[i][j] = funcion.getAsientoVirtual()[i][j];
			}
		}
	}

	private void obtenerCantidadDeFilasYAsientos(Funcion funcion) {
		
		for (int i = 1; i < AsientoVirtual.FILAS; i++) {
			
			for (int j = 1; j < AsientoVirtual.ASIENTOSPORFILA; j++) {
				
				if (funcion.getAsientoVirtual()[i][j] != null) {
					
					if (i < cantidadMinimaDeFilas) {
						cantidadMinimaDeFilas = i;
					}
					
					if (j < cantidadMinimaDeAsientosPorFila) {
						cantidadMinimaDeAsientosPorFila = j;
					}
					
					if (i > cantidadMaximaDeFilas) {
						cantidadMaximaDeFilas = i;
					}
					
					if (j > cantidadMaximaDeAsientosPorFila) {
						cantidadMaximaDeAsientosPorFila = j;
					}
				}
			}
		}
	}
	
	private void reservarOLiberarAsiento(Funcion funcion, String filaYAsiento) {
		
		String[] filaYAsientoPartido = filaYAsiento.split(Pattern.quote(","));
		Integer fila = Integer.parseInt(filaYAsientoPartido[0]);
		Integer numeroDeAsiento = Integer.parseInt(filaYAsientoPartido[1]);
		
		if (asientosVirtualesTemporales[fila][numeroDeAsiento] != null) {
			
			if (asientosVirtualesTemporales[fila][numeroDeAsiento].getEstado().equals(EstadoVirtual.LIBRE)) {
				
				asientosVirtualesTemporales[fila][numeroDeAsiento].setEstado(EstadoVirtual.OCUPADO);
				
			} else {
				
				asientosVirtualesTemporales[fila][numeroDeAsiento].setEstado(EstadoVirtual.LIBRE);
			}
		}
	}
}
