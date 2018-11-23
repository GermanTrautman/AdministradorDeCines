package com.cine.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner.DateEditor;

import com.cine.controlador.ControladorFuncion;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Funcion;
import com.cine.modelo.Pelicula;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;

public class JFormularioBajaFuncion extends JFormularioBase {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxSala;
	private JComboBox<String> comboBoxPelicula;
	private JSpinner spinnerFecha;
	private JSpinner spinnerHora;
	private JComboBox<Estado> comboBoxEstado;
	private JComboBox<Object> comboBoxEstablecimiento;
	Establecimiento establecimientoSeleccionado = null;
	Sala salaSeleccionada = null;
	Pelicula peliculaSeleccionada = null;
	private JTextField txtId = new JTextField();
	private Funcion funcion;
	private JButton btnBajaFuncion;

	public JFormularioBajaFuncion() {

		this.getContentPane().setLayout(null);

		JLabel lblBajaFuncion = new JLabel("Baja Funcion");
		lblBajaFuncion.setHorizontalAlignment(SwingConstants.CENTER);
		lblBajaFuncion.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblBajaFuncion.setBounds(0, 122, 1024, 38);
		getContentPane().add(lblBajaFuncion);

		JLabel lblIdFuncion = new JLabel("Id Funcion");
		lblIdFuncion.setBounds(223, 238, 229, 20);
		this.getContentPane().add(lblIdFuncion);

		txtId.setBounds(467, 235, 256, 26);
		this.getContentPane().add(txtId);
		txtId.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				funcion = (Funcion) ControladorFuncion.getInstance()
						.buscarEnCachePor(Integer.parseInt(txtId.getText()));
				if (funcion != null) {
					popularCampos(funcion);

					btnBajaFuncion.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Funcion no encontrada.");
				}

			}

		});
		btnBuscar.setBounds(745, 231, 115, 29);
		this.getContentPane().add(btnBuscar);

		JButton btnLimpiarCampos = new JButton("Limpiar Campos");
		btnLimpiarCampos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();

			}
		});
		btnLimpiarCampos.setBounds(870, 231, 115, 29);
		this.getContentPane().add(btnLimpiarCampos);

		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(223, 287, 229, 20);
		this.getContentPane().add(lblEstablecimiento);

		comboBoxEstablecimiento = new JComboBox<>();
		comboBoxEstablecimiento.setBounds(467, 284, 256, 26);
		comboBoxEstablecimiento.setEnabled(false);
		this.getContentPane().add(comboBoxEstablecimiento);
		JLabel lblSala = new JLabel("Sala");
		lblSala.setBounds(223, 335, 229, 20);
		this.getContentPane().add(lblSala);

		comboBoxSala = new JComboBox<>();
		comboBoxSala.setBounds(467, 332, 256, 26);
		this.getContentPane().add(comboBoxSala);
		comboBoxSala.setEnabled(false);

		JLabel lblPelicula = new JLabel("Pelicula");
		lblPelicula.setBounds(223, 384, 229, 20);
		this.getContentPane().add(lblPelicula);

		comboBoxPelicula = new JComboBox<>();
		comboBoxPelicula.setBounds(467, 381, 256, 26);
		this.getContentPane().add(comboBoxPelicula);
		comboBoxPelicula.setEnabled(false);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(223, 434, 229, 20);
		this.getContentPane().add(lblFecha);

		SpinnerDateModel SFecha = new SpinnerDateModel();
		spinnerFecha = new JSpinner(SFecha);
		DateEditor dateEditor = new DateEditor(spinnerFecha, "dd/MMM/yyyy");
		spinnerFecha.setEditor(dateEditor);
		spinnerFecha.setBounds(467, 431, 256, 26);
		this.getContentPane().add(spinnerFecha);
		spinnerFecha.setEnabled(false);

		JLabel lblHora = new JLabel("Horario");
		lblHora.setBounds(223, 481, 229, 20);
		this.getContentPane().add(lblHora);

		SpinnerDateModel SHora = new SpinnerDateModel(new Time(1540868400000L), null, null, Calendar.HOUR_OF_DAY);
		spinnerHora = new JSpinner(SHora);
		JSpinner.DateEditor HourEditor = new DateEditor(spinnerHora, "HH:mm");
		spinnerHora.setEditor(HourEditor);
		spinnerHora.setBounds(467, 478, 256, 26);
		this.getContentPane().add(spinnerHora);
		spinnerHora.setEnabled(false);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(223, 526, 229, 20);
		this.getContentPane().add(lblEstado);

		comboBoxEstado = new JComboBox<>();
		comboBoxEstado.setBounds(467, 523, 256, 26);
		this.getContentPane().add(comboBoxEstado);
		comboBoxEstado.setModel(new DefaultComboBoxModel<>(Estado.values()));
		comboBoxEstado.setEnabled(false);

		btnBajaFuncion = new JButton("Baja Funcion");
		btnBajaFuncion.setBounds(223, 670, 151, 26);
		this.getContentPane().add(btnBajaFuncion);

		btnBajaFuncion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ControladorFuncion.getInstance().bajaFuncion(Integer.parseInt(txtId.getText()));

				reset();
				setVisible(false);
			}
		});
	}

	private void popularCampos(Funcion funcion) {
		this.comboBoxEstablecimiento.addItem(funcion.getSala().getEstablecimiento().getNombre());
		this.comboBoxSala.addItem(funcion.getSala().getNombre());
		this.comboBoxPelicula.addItem(funcion.getPelicula().getNombre());
		this.spinnerFecha.setValue(Date.valueOf(funcion.getFecha()));
		this.spinnerHora.setValue(funcion.getHora());
		this.comboBoxEstado.addItem(funcion.getEstado());

	}

	private void reset() {
		this.comboBoxEstablecimiento.removeAllItems();
		this.comboBoxSala.removeAllItems();
		this.comboBoxPelicula.removeAllItems();

	}

}
