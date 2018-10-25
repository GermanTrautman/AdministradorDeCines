package com.cine.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.controlador.ControladorSala;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;
import com.cine.vista.modelo.ComboEstablecimiento;
import com.cine.vista.modelo.ComboEstado;
import com.cine.vista.modelo.ModeloTablaSala;

public class JFormularioModificarSala extends JFormularioBase {

	private static final long serialVersionUID = 1L;

	private JTextField nombre = new JTextField();
	private JTextField capacidad = new JTextField();

	private JComboBox<ComboEstablecimiento> comboEstablecimientos = new JComboBox<>();
	private JComboBox<ComboEstado> comboEstados = new JComboBox<>();
	
	private JButton btnAgregarEstablecimiento = new JButton("Guardar cambios");

	public JFormularioModificarSala(ModeloTablaSala modelo, String unNombre) {

		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		Sala sala = (Sala) ControladorSala.getInstance().buscarEnCache(unNombre);

		this.getContentPane().add(new JLabel("Nombre :"));
		this.getContentPane().add(nombre).setEnabled(false);

		this.getContentPane().add(new JLabel("Capacidad:"));
		this.getContentPane().add(capacidad);
		
		popularNombreYCapacidad(sala);
		
		this.getContentPane().add(new JLabel("Establecimiento :"));
		this.getContentPane().add(comboEstablecimientos);
		
		popularComboEstablecimientos();

		this.getContentPane().add(new JLabel("Estado :"));
		this.getContentPane().add(comboEstados);
		
		popularEstado();

		btnAgregarEstablecimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object itemEstablecimiento = comboEstablecimientos.getSelectedItem();
				Integer idEstablecimiento = ((ComboEstablecimiento)itemEstablecimiento).getId();
				
				Object itemEstado = comboEstados.getSelectedItem();
				String estadoEnletras = ((ComboEstado)itemEstado).getEstado();

				if (nombre.getText() != null && capacidad.getText() != null
						&& comboEstablecimientos.getSelectedItem() != null && comboEstados.getSelectedItem() != null) {
					
					ControladorSala.getInstance().modificacion(nombre.getText(), Integer.parseInt(capacidad.getText()),
							idEstablecimiento, estadoEnletras);

					JOptionPane.showMessageDialog(null, "Sala modificada correctamente");

					reset();

					modelo.fireTableDataChanged();
					
					dispose();
				}
			}
		});
		this.getContentPane().add(btnAgregarEstablecimiento);

		btnAgregarEstablecimiento.setMaximumSize(getMaximumSize());
	}


	private void popularNombreYCapacidad(Sala sala) {

		this.nombre.setText(sala.getNombre());
		this.capacidad.setText(sala.getCapacidad().toString());
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

	public void reset() {

		this.nombre.setText("");
		this.capacidad.setText("");
	}
}