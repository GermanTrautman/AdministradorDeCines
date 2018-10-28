package com.cine.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

	private static final long serialVersionUID = 1L;

	private JTextField nombre = new JTextField();
	private JTextField capacidad = new JTextField();

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
		
		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(223, 237, 229, 20);
		this.getContentPane().add(lblCapacidad);

		this.capacidad.setBounds(467, 237, 256, 26);
		this.capacidad.setColumns(10);
		this.capacidad.setEnabled(false);
		this.getContentPane().add(capacidad);
		
		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(223, 279, 229, 20);
		this.getContentPane().add(lblEstablecimiento);

		this.comboEstablecimientos.setBounds(467, 279, 256, 26);
		this.comboEstablecimientos.setEnabled(false);
		this.getContentPane().add(comboEstablecimientos);
		
		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setBounds(223, 321, 229, 20);
		this.getContentPane().add(lblNewLabel);

		this.comboEstados.setBounds(467, 321, 256, 26);
		this.comboEstados.setEnabled(false);
		this.getContentPane().add(comboEstados);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (nombre.getText() != null) {
					
					ComboEstablecimiento establecimientoSeleccionado = (ComboEstablecimiento) comboEstablecimientos.getSelectedItem();
					Integer idEstablecimiento = establecimientoSeleccionado.getId();
					
					ComboEstado estadoSeleccionado = (ComboEstado) comboEstados.getSelectedItem();
					String estado = estadoSeleccionado.getEstado();
					
					ControladorSala.getInstance().modificacion(nombre.getText(), Integer.parseInt(capacidad.getText()), idEstablecimiento, estado);
					
					resetCampos();
					
					JOptionPane.showMessageDialog(null, "Sala moficada correctamente");
				}
			}
		});
		this.btnGuardar.setBounds(223, 670, 115, 29);
		this.getContentPane().add(btnGuardar);
	}
	
	private void popularEstados(Sala sala) {

		if (sala != null) {

			capacidad.setText(sala.getCapacidad().toString());
	
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
			
			ComboEstado activo = new ComboEstado(Estado.ACTIVO.estado(), 1);
			comboEstados.addItem(activo);
			
			if (sala.getEstado().estado().equals(Estado.ACTIVO.estado())) {
				comboEstados.setSelectedItem(activo);
			}

			ComboEstado inactivo = new ComboEstado(Estado.INACTIVO.estado(), 1);
			comboEstados.addItem(inactivo);

			if (sala.getEstado().estado().equals(Estado.INACTIVO.estado())) {
				comboEstados.setSelectedItem(inactivo);
			}
		}
	}
	
	private void habilitarCampos() {

		nombre.setEnabled(false);
		capacidad.setEnabled(true);
		comboEstablecimientos.setEnabled(true);
		comboEstados.setEnabled(true);
	}
	
	private void resetCampos() {

		nombre.setText("");
		capacidad.setText("");
		comboEstablecimientos.removeAllItems();
		comboEstados.removeAllItems();
		
		nombre.setEnabled(true);
		capacidad.setEnabled(false);
		comboEstablecimientos.setEnabled(false);
		comboEstados.setEnabled(false);
	}
}