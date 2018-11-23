package com.cine.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorFuncion;
import com.cine.controlador.ControladorSala;
import com.cine.dao.FuncionPersistente;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Pelicula;
import com.cine.modelo.Sala;
import com.cine.utilidades.Estado;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;


public class JFormularioAltaFuncion extends JFormularioBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxSala;
	private JComboBox<String> comboBoxPelicula;
	private JSpinner spinnerFecha;
	private JSpinner spinnerHora;
	private JComboBox<Estado> comboBoxEstado;
	private JComboBox<Object> comboBoxEstablecimiento;
	private List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
	private List<Sala> salasDeEstablecimiento = new ArrayList<Sala>();
	private List<Pelicula> peliculas = new ArrayList<Pelicula>();
	Establecimiento establecimientoSeleccionado = null;
	Sala salaSeleccionada = null;
	Pelicula peliculaSeleccionada = null;

	public JFormularioAltaFuncion() {
		establecimientos = FuncionPersistente.getInstance().getEstablecimientos();
		peliculas = FuncionPersistente.getInstance().getPeliculasDisponibles();
		this.getContentPane().setLayout(null);
		JLabel lblAltaFuncion = new JLabel("Alta Funcion");
		lblAltaFuncion.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltaFuncion.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltaFuncion.setBounds(0, 122, 1024, 38);
		getContentPane().add(lblAltaFuncion);

		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(223, 207, 229, 20);
		this.getContentPane().add(lblEstablecimiento);

		comboBoxEstablecimiento = new JComboBox<>();
		comboBoxEstablecimiento.setBounds(467, 204, 256, 26);
		this.getContentPane().add(comboBoxEstablecimiento);
		for (Establecimiento establecimiento : establecimientos) {
			comboBoxEstablecimiento.addItem(establecimiento.getNombre());
		}
		comboBoxEstablecimiento.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				comboBoxSala.setEnabled(true);
				comboBoxSala.removeAllItems();
				for (Establecimiento establecimiento : establecimientos) {
					if (establecimiento.getNombre().equals(comboBoxEstablecimiento.getSelectedItem())) {
						establecimientoSeleccionado = establecimiento;
					}
				}
				salasDeEstablecimiento = FuncionPersistente.getInstance()
						.getSalasDeEstablecimiento(establecimientoSeleccionado);
				for (Sala sala : salasDeEstablecimiento) {
					comboBoxSala.addItem(sala.getNombre());

				}

			}
		});

		JLabel lblSala = new JLabel("Sala");
		lblSala.setBounds(223, 243, 229, 20);
		this.getContentPane().add(lblSala);

		comboBoxSala = new JComboBox<>();
		comboBoxSala.setBounds(467, 240, 256, 26);
		this.getContentPane().add(comboBoxSala);
		comboBoxSala.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				comboBoxPelicula.setEnabled(true);
				for (Sala sala : salasDeEstablecimiento) {
					if (sala.getNombre().equals(comboBoxSala.getSelectedItem())) {
						salaSeleccionada = sala;
					}
				}

			}
		});
		comboBoxSala.setEnabled(false);

		JLabel lblPelicula = new JLabel("Pelicula");
		lblPelicula.setBounds(223, 280, 229, 20);
		this.getContentPane().add(lblPelicula);

		comboBoxPelicula = new JComboBox<>();
		comboBoxPelicula.setBounds(467, 277, 256, 26);
		for (Pelicula pelicula : peliculas) {
			comboBoxPelicula.addItem(pelicula.getNombre());
		}
		this.getContentPane().add(comboBoxPelicula);
		comboBoxPelicula.setEnabled(false);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(223, 322, 229, 20);
		this.getContentPane().add(lblFecha);

		SpinnerDateModel SFecha = new SpinnerDateModel();
		spinnerFecha = new JSpinner(SFecha);
		DateEditor dateEditor = new DateEditor(spinnerFecha, "dd/MMM/yyyy");
		spinnerFecha.setEditor(dateEditor);
		spinnerFecha.setBounds(467, 319, 256, 26);
		this.getContentPane().add(spinnerFecha);

		JLabel lblHora = new JLabel("Horario");
		lblHora.setBounds(223, 359, 229, 20);
		this.getContentPane().add(lblHora);

		SpinnerDateModel SHora = new SpinnerDateModel(new Time(1540868400000L), null, null, Calendar.HOUR_OF_DAY);
		spinnerHora = new JSpinner(SHora);
		JSpinner.DateEditor HourEditor = new DateEditor(spinnerHora, "HH:mm");
		spinnerHora.setEditor(HourEditor);
		spinnerHora.setBounds(467, 356, 256, 26);
		this.getContentPane().add(spinnerHora);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(223, 396, 229, 20);
		this.getContentPane().add(lblEstado);

		comboBoxEstado = new JComboBox<>();
		comboBoxEstado.setBounds(467, 393, 256, 26);
		this.getContentPane().add(comboBoxEstado);
		comboBoxEstado.setModel(new DefaultComboBoxModel<>(Estado.values()));

		JButton btnAgregarFuncion = new JButton("Agregar Funcion");
		btnAgregarFuncion.setBounds(223, 670, 151, 26);
		this.getContentPane().add(btnAgregarFuncion);

		btnAgregarFuncion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Date dFecha = (Date) spinnerFecha.getValue();	
				LocalDate fecha = dFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				Date sHora = (Date) spinnerHora.getValue();
				Time horario = new Time(sHora.getTime());
				;
				if (fecha.compareTo(LocalDate.now()) >= 0) {

					for (Pelicula pelicula : peliculas) {
						if (pelicula.getNombre().equals(comboBoxPelicula.getSelectedItem())) {
							peliculaSeleccionada = pelicula;
						}
					}
					salaSeleccionada = ControladorSala.getInstance().buscar(salaSeleccionada.getNombre());
					ControladorFuncion.getInstance().altaFuncion(fecha, salaSeleccionada, peliculaSeleccionada,
							Estado.valueOf(comboBoxEstado.getSelectedItem().toString().toUpperCase()), horario);
				}
				reset();
				setVisible(false);
			}
		});
	}

	private void reset() {
		this.comboBoxEstablecimiento.removeAllItems();
		this.comboBoxSala.removeAllItems();
		this.comboBoxPelicula.removeAllItems();

	}
}
