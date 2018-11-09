package com.cine.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorSala;
import com.cine.modelo.Sala;
import com.cine.vista.modelo.ComboEstablecimiento;
import com.cine.vista.modelo.ComboEstado;

public class JFormularioBajaSala extends JFormularioBase {

	private static final long serialVersionUID = -1172525196735885232L;
	
	private JTextField nombre = new JTextField();

	private JComboBox<ComboEstablecimiento> comboEstablecimientos = new JComboBox<>();
	private JComboBox<ComboEstado> comboEstados = new JComboBox<>();
	
	private JButton btnVerAsientos = new JButton("Ver asientos");
	private JButton btnBorrar = new JButton("Borrar");
	
	public JFormularioBajaSala() {
		
		this.getContentPane().setLayout(null);
		
		JLabel lblAltaSalas = new JLabel("Baja Sala");
		lblAltaSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltaSalas.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltaSalas.setBounds(0, 13, 1024, 38);
		this.getContentPane().add(lblAltaSalas);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(223, 64, 229, 26);
		this.getContentPane().add(lblNombre);
		
		this.nombre.setBounds(467, 64, 256, 26);
		this.nombre.setColumns(10);
		this.getContentPane().add(nombre);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (nombre.getText() != null) {

					Sala sala = ControladorSala.getInstance().buscar(nombre.getText());
					popularCampos(sala);
				}
			}
		});
		btnBuscar.setBounds(745, 64, 115, 29);
		this.getContentPane().add(btnBuscar);

		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(223, 117, 229, 26);
		this.getContentPane().add(lblEstablecimiento);

		this.comboEstablecimientos.setBounds(467, 117, 256, 26);
		this.comboEstablecimientos.setEnabled(false);
		this.getContentPane().add(comboEstablecimientos);
		
		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setBounds(223, 169, 229, 25);
		this.getContentPane().add(lblNewLabel);

		this.comboEstados.setBounds(467, 168, 256, 26);
		this.comboEstados.setEnabled(false);
		this.getContentPane().add(comboEstados);
		

		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (nombre.getText() != null) {
				
					ControladorSala.getInstance().baja(nombre.getText());
					
					resetCampos();
					
					JOptionPane.showMessageDialog(null, "Sala borrada correctamente");
				}
			}
		});
		this.btnBorrar.setBounds(223, 670, 115, 29);
		this.getContentPane().add(btnBorrar);
		btnVerAsientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame verAsientos = new JFormularioVerAsientos(nombre.getText());
				verAsientos.setVisible(true);
			}
		});
		
		btnVerAsientos.setBounds(223, 232, 229, 29);
		getContentPane().add(btnVerAsientos);
	}
	
	private void popularCampos(Sala sala) {

		comboEstablecimientos.removeAllItems();
		comboEstablecimientos.addItem(new ComboEstablecimiento(sala.getEstablecimiento().getCuit(), sala.getEstablecimiento().getNombre()));
		
		comboEstados.removeAllItems();
		comboEstados.addItem(new ComboEstado(sala.getEstado().estado(), 1));
	}
	
	private void resetCampos() {

		nombre.setText("");
		comboEstablecimientos.removeAllItems();
		comboEstados.removeAllItems();
	}
}
