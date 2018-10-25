package com.cine.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.controlador.ControladorSala;
import com.cine.modelo.Establecimiento;
import com.cine.utilidades.Estado;
import com.cine.vista.modelo.ComboEstablecimiento;
import com.cine.vista.modelo.ComboEstado;

import javax.swing.JComboBox;

public class JFormularioAltaSala extends JFormularioBase {

	private static final long serialVersionUID = 463503988391452862L;

	private JTextField nombre = new JTextField();
	private JTextField capacidad = new JTextField();

	private JComboBox<ComboEstablecimiento> comboEstablecimientos = new JComboBox<>();
	private JComboBox<ComboEstado> comboEstados = new JComboBox<>();

	private JButton btnGuardarSala = new JButton("Guardar");

	public JFormularioAltaSala() {

		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.getContentPane().add(new JLabel("Nombre:"));
		this.getContentPane().add(nombre);

		this.getContentPane().add(new JLabel("Capacidad :"));
		this.getContentPane().add(capacidad);

		this.getContentPane().add(new JLabel("Establecimiento :"));
		this.getContentPane().add(comboEstablecimientos);
		
		popularComboEstablecimientos();

		this.getContentPane().add(new JLabel("Estado :"));
		this.getContentPane().add(comboEstados);
		
		popularEstado();

		this.btnGuardarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object itemEstablecimiento = comboEstablecimientos.getSelectedItem();
				Integer idEstablecimiento = ((ComboEstablecimiento)itemEstablecimiento).getId();
				
				Object itemEstado = comboEstados.getSelectedItem();
				String estadoEnletras = ((ComboEstado)itemEstado).getEstado();
				
				if (nombre.getText() != null && capacidad.getText() != null
						&& comboEstablecimientos.getSelectedItem() != null && comboEstados.getSelectedItem() != null) {
					ControladorSala.getInstance().alta(nombre.getText(), Integer.parseInt(capacidad.getText()),
							idEstablecimiento, estadoEnletras);
				}
			}
		});

		this.getContentPane().add(btnGuardarSala);

		this.btnGuardarSala.setMaximumSize(getMaximumSize());
	}

	public void reset() {

		this.nombre.setText("");
		this.capacidad.setText("");
		this.comboEstablecimientos.setSelectedIndex(0);
		//this.estado.setSelectedIndex(0);
	}
	
	private void popularComboEstablecimientos() {
		
		for (Establecimiento establecimiento : ControladorEstablecimiento.getInstance().getEstablecimientos()) {
			comboEstablecimientos.addItem(new ComboEstablecimiento(establecimiento.getCuit(), establecimiento.getNombre()));
		}
	}
	
	private void popularEstado() {

		comboEstados.addItem(new ComboEstado(Estado.ACTIVO.estado(), 1));
		comboEstados.addItem(new ComboEstado(Estado.INACTIVO.estado(), 2));
	}
}
