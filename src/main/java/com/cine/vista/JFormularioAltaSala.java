package com.cine.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.controlador.ControladorSala;
import com.cine.modelo.AsientoFisico;
import com.cine.modelo.Establecimiento;
import com.cine.utilidades.Estado;
import com.cine.vista.modelo.ComboEstablecimiento;
import com.cine.vista.modelo.ComboEstado;
import com.cine.vista.modelo.TablaAsientos;

import javax.swing.JComboBox;
import javax.swing.JTable;

public class JFormularioAltaSala extends JFormularioBase {

	private static final long serialVersionUID = 463503988391452862L;

	private JTextField nombre = new JTextField();
	private JTextField capacidad = new JTextField();

	private JComboBox<ComboEstablecimiento> comboEstablecimientos = new JComboBox<>();
	private JComboBox<ComboEstado> comboEstados = new JComboBox<>();

	private JTable tblAsientos;
	private List<AsientoFisico> asientos;

	private JButton btnGuardar = new JButton("Guardar");

	public JFormularioAltaSala() {

		this.getContentPane().setLayout(null);

		JLabel lblAltaSalas = new JLabel("Alta Sala");
		lblAltaSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltaSalas.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltaSalas.setBounds(0, 13, 1024, 38);
		this.getContentPane().add(lblAltaSalas);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(223, 64, 229, 26);
		this.getContentPane().add(lblNombre);

		this.nombre.setBounds(464, 64, 256, 26);
		this.nombre.setColumns(10);
		this.getContentPane().add(nombre);

		JLabel lblCapacidad = new JLabel("Capacidad");
		lblCapacidad.setBounds(223, 103, 229, 26);
		this.getContentPane().add(lblCapacidad);

		this.capacidad.setBounds(464, 103, 256, 26);
		this.capacidad.setColumns(10);
		this.getContentPane().add(capacidad);

		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(223, 142, 225, 26);
		this.getContentPane().add(lblEstablecimiento);

		this.comboEstablecimientos.setBounds(464, 142, 256, 26);
		this.getContentPane().add(comboEstablecimientos);

		popularComboEstablecimientos();

		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setBounds(223, 184, 229, 23);
		this.getContentPane().add(lblNewLabel);

		this.comboEstados.setBounds(464, 181, 256, 26);
		this.getContentPane().add(comboEstados);

		popularEstado();

		this.btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object itemEstablecimiento = comboEstablecimientos.getSelectedItem();
				Integer idEstablecimiento = ((ComboEstablecimiento) itemEstablecimiento).getId();

				Object itemEstado = comboEstados.getSelectedItem();
				String estadoEnletras = ((ComboEstado) itemEstado).getEstado();

				if (nombre.getText() != null && capacidad.getText() != null
						&& comboEstablecimientos.getSelectedItem() != null && comboEstados.getSelectedItem() != null) {

					ControladorSala.getInstance().alta(nombre.getText(), Integer.parseInt(capacidad.getText()),
							idEstablecimiento, estadoEnletras);

					JOptionPane.showMessageDialog(null, "Sala creada correctamente");

					reset();
				}
			}
		});
		this.btnGuardar.setBounds(223, 679, 115, 29);
		this.getContentPane().add(btnGuardar);

		JLabel lblNewLabel_1 = new JLabel("Asientos");
		lblNewLabel_1.setBounds(223, 228, 56, 16);
		getContentPane().add(lblNewLabel_1);

		tblAsientos = new JTable(new TablaAsientos());
		tblAsientos.setBounds(223, 257, 502, 400);
		tblAsientos.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				int fila = tblAsientos.rowAtPoint(evt.getPoint());
				int columna = tblAsientos.columnAtPoint(evt.getPoint());

				if (fila >= 0 && columna >= 0) {

					AsientoFisico asientoFisico = asientos.stream().filter(
							asiento -> asiento.getFila().equals(fila) && asiento.getNumeroDeAsiento().equals(columna))
							.findAny().orElse(null);
					
					if (asientoFisico == null) {
						asientos.add(new AsientoFisico(fila, columna, Estado.ACTIVO));
					} else {
						asientos.removeIf(asiento -> asiento.getFila().equals(fila) && asiento.getNumeroDeAsiento().equals(columna));
					}
				}
			}
		});
		getContentPane().add(tblAsientos);
	}

	public void reset() {

		this.nombre.setText("");
		this.capacidad.setText("");
		this.comboEstablecimientos.setSelectedIndex(0);
		this.comboEstados.setSelectedIndex(0);
	}

	private void popularComboEstablecimientos() {

		ControladorEstablecimiento.getInstance().obtenerEstablecimientos();

		for (Establecimiento establecimiento : ControladorEstablecimiento.getInstance().getEstablecimientos()) {
			comboEstablecimientos
					.addItem(new ComboEstablecimiento(establecimiento.getCuit(), establecimiento.getNombre()));
		}
	}

	private void popularEstado() {

		comboEstados.addItem(new ComboEstado(Estado.ACTIVO.estado(), 1));
		comboEstados.addItem(new ComboEstado(Estado.INACTIVO.estado(), 2));
	}
}
