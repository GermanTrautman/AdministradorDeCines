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

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.controlador.ControladorSala;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;
import com.cine.vista.modelo.ComboEstablecimiento;
import com.cine.vista.modelo.ComboEstado;

public class JFormularioModificarSala extends JFormularioBase {

	private static final long serialVersionUID = -5710795622494956896L;

	private JTextField nombre = new JTextField();

	private JComboBox<ComboEstablecimiento> comboEstablecimientos = new JComboBox<>();
	private JComboBox<ComboEstado> comboEstados = new JComboBox<>();

	private JButton btnGuardar = new JButton("Guardar");

	public JFormularioModificarSala() {

		this.getContentPane().setLayout(null);
		
		JLabel lblAltaSalas = new JLabel("Modificaci\u00F3n sala");
		lblAltaSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltaSalas.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltaSalas.setBounds(0, 122, 1024, 38);
		this.getContentPane().add(lblAltaSalas);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(223, 195, 229, 20);
		this.getContentPane().add(lblNombre);
		
		this.nombre.setBounds(467, 195, 256, 26);
		this.nombre.setColumns(10);
		this.getContentPane().add(nombre);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (nombre.getText() != null) {

					Sala sala = ControladorSala.getInstance().buscar(nombre.getText());
					popularEstablecimientos(sala);
					popularEstados(sala);
					habilitarCampos();
				}
			}
		});
		btnBuscar.setBounds(745, 194, 115, 29);
		this.getContentPane().add(btnBuscar);
		
		JButton btnLimpiarCampos = new JButton("Limpiar campos");
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				resetCampos();
			}
		});
		btnLimpiarCampos.setBounds(863, 194, 131, 29);
		getContentPane().add(btnLimpiarCampos);
		
		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(223, 241, 229, 20);
		this.getContentPane().add(lblEstablecimiento);

		this.comboEstablecimientos.setBounds(467, 238, 256, 26);
		this.comboEstablecimientos.setEnabled(false);
		this.getContentPane().add(comboEstablecimientos);
		
		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setBounds(223, 290, 229, 20);
		this.getContentPane().add(lblNewLabel);

		this.comboEstados.setBounds(467, 287, 256, 26);
		this.comboEstados.setEnabled(false);
		this.getContentPane().add(comboEstados);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (nombre.getText() != null) {
					
					ComboEstablecimiento establecimientoSeleccionado = (ComboEstablecimiento) comboEstablecimientos.getSelectedItem();
					Integer idEstablecimiento = establecimientoSeleccionado.getId();
					
					ComboEstado estadoSeleccionado = (ComboEstado) comboEstados.getSelectedItem();
					String estado = estadoSeleccionado.getEstado();
					
					ControladorSala.getInstance().modificacion(nombre.getText(), idEstablecimiento, estado);
					
					resetCampos();
					
					JOptionPane.showMessageDialog(null, "Sala moficada correctamente");
				}
			}
		});
		this.btnGuardar.setBounds(223, 670, 115, 29);
		this.getContentPane().add(btnGuardar);
		
		JButton btnAsientos = new JButton("Asientos");
		btnAsientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFrame asientos = new JFormularioAsientosModificacionSala(nombre.getText());
				asientos.setVisible(true);
			}
		});
		btnAsientos.setBounds(223, 343, 115, 29);
		getContentPane().add(btnAsientos);
	}
	
	private void popularEstados(Sala sala) {

		if (sala != null) {
	
			ControladorEstablecimiento.getInstance().obtenerEstablecimientos();
			
			for (Establecimiento establecimiento : ControladorEstablecimiento.getInstance().getEstablecimientos()) {
				
				ComboEstablecimiento comboEstablecimiento = new ComboEstablecimiento(establecimiento.getCuit(), establecimiento.getNombre());
				comboEstablecimientos.addItem(comboEstablecimiento);

				if (sala.getEstablecimiento().getCuit().equals(establecimiento.getCuit())) {
					comboEstablecimientos.setSelectedItem(comboEstablecimiento);
				}
				
			}
		}
	}

	private void popularEstablecimientos(Sala sala) {

		if (sala != null) {
			
			comboEstados.removeAllItems();
			
			ComboEstado activo = new ComboEstado(Estado.ACTIVO.getLabel(), 1);
			comboEstados.addItem(activo);
			
			if (sala.getEstado().getLabel().equals(Estado.ACTIVO.getLabel())) {
				comboEstados.setSelectedItem(activo);
			}

			ComboEstado inactivo = new ComboEstado(Estado.INACTIVO.getLabel(), 1);
			comboEstados.addItem(inactivo);

			if (sala.getEstado().getLabel().equals(Estado.INACTIVO.getLabel())) {
				comboEstados.setSelectedItem(inactivo);
			}
		}
	}
	
	private void habilitarCampos() {

		nombre.setEnabled(false);
		comboEstablecimientos.setEnabled(true);
		comboEstados.setEnabled(true);
	}
	
	private void resetCampos() {

		nombre.setText("");
		comboEstablecimientos.removeAllItems();
		comboEstados.removeAllItems();
		
		nombre.setEnabled(true);
		comboEstablecimientos.setEnabled(false);
		comboEstados.setEnabled(false);
	}
}