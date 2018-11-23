package com.cine.vista;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorVenta;
import com.cine.modelo.Venta;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFormularioImprimir extends JFormularioBase {

	private static final long serialVersionUID = -2891267752258479075L;
	
	private JTextField dni;
	private JTextField codigo;

	public JFormularioImprimir() {

		getContentPane().setLayout(null);
		
		JLabel lblImprimir = new JLabel("Imprimir entradas");
		lblImprimir.setHorizontalAlignment(SwingConstants.CENTER);
		lblImprimir.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblImprimir.setBounds(0, 122, 1024, 38);
		this.getContentPane().add(lblImprimir);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(349, 230, 145, 16);
		getContentPane().add(lblDni);
		
		dni = new JTextField();
		dni.setBounds(517, 227, 171, 22);
		getContentPane().add(dni);
		dni.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(349, 286, 145, 16);
		getContentPane().add(lblCodigo);
		
		codigo = new JTextField();
		codigo.setBounds(517, 283, 171, 22);
		getContentPane().add(codigo);
		codigo.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (dni.getText() != null && codigo.getText() != null) {
					
					Venta venta = ControladorVenta.getInstance().buscarVentaPor(Integer.parseInt(dni.getText()), codigo.getText());
					
					if (venta != null) {
						
						JOptionPane.showMessageDialog(null, "Imprimiendo ...");
					
					} else {
						
						JOptionPane.showMessageDialog(null, "Se produjo un error buscando la entrada. Por favor, Verifique los campos");
					}
					
					dispose();
				}
			}
		});
		btnBuscar.setBounds(441, 351, 97, 25);
		getContentPane().add(btnBuscar);
	}
}
