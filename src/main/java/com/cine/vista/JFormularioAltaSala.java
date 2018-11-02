package com.cine.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import com.cine.vista.modelo.TablaAltaAsientos;

import javax.swing.JComboBox;
import javax.swing.JTable;

public class JFormularioAltaSala extends JFormularioBase {

	private static final long serialVersionUID = 463503988391452862L;

	private JTextField nombre = new JTextField();

	private JComboBox<ComboEstablecimiento> comboEstablecimientos = new JComboBox<>();
	private JComboBox<ComboEstado> comboEstados = new JComboBox<>();

	private JTable tblAsientos;
	private TablaAltaAsientos tablaAltaAsientos = new TablaAltaAsientos();

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

		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(223, 114, 225, 26);
		this.getContentPane().add(lblEstablecimiento);

		this.comboEstablecimientos.setBounds(464, 114, 256, 26);
		this.getContentPane().add(comboEstablecimientos);

		popularComboEstablecimientos();

		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setBounds(223, 169, 229, 23);
		this.getContentPane().add(lblNewLabel);

		this.comboEstados.setBounds(464, 167, 256, 26);
		this.getContentPane().add(comboEstados);

		popularEstado();

		JLabel lblNewLabel_1 = new JLabel("Asientos");
		lblNewLabel_1.setBounds(223, 228, 56, 16);
		getContentPane().add(lblNewLabel_1);

		tblAsientos = new JTable(tablaAltaAsientos);
		tblAsientos.setBounds(223, 257, 502, 400);
		tblAsientos.setRowSelectionAllowed(false);
		tblAsientos.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				int fila = tblAsientos.rowAtPoint(evt.getPoint());
				int columna = tblAsientos.columnAtPoint(evt.getPoint());

				AsientoFisico asientoFisico = new AsientoFisico(nombre.getText(), fila, columna, Estado.ACTIVO);

				if (ControladorSala.getInstance().getAsientosFisicosTemporales()[fila][columna] == null) {
					ControladorSala.getInstance().getAsientosFisicosTemporales()[fila][columna] = asientoFisico;
				} else {
					ControladorSala.getInstance().getAsientosFisicosTemporales()[fila][columna] = null;
				}
			}
		});
		getContentPane().add(tblAsientos);

		this.btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object itemEstablecimiento = comboEstablecimientos.getSelectedItem();
				Integer idEstablecimiento = ((ComboEstablecimiento) itemEstablecimiento).getId();

				Object itemEstado = comboEstados.getSelectedItem();
				String estadoEnletras = ((ComboEstado) itemEstado).getEstado();

				if (nombre.getText() != null && comboEstablecimientos.getSelectedItem() != null
						&& comboEstados.getSelectedItem() != null) {

					ControladorSala.getInstance().alta(nombre.getText(),
							ControladorSala.getInstance().getAsientosFisicosTemporales(), idEstablecimiento,
							estadoEnletras);

					JOptionPane.showMessageDialog(null, "Sala creada correctamente");

					reset();
				}
			}
		});
		this.btnGuardar.setBounds(223, 679, 115, 29);
		this.getContentPane().add(btnGuardar);
	}

	public void reset() {

		this.nombre.setText("");
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
