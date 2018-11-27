package com.cine.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorPelicula;
import com.cine.modelo.Pelicula;
import com.cine.utilidades.Estado;

public class JFormularioBajaPelicula extends JFormularioBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtId = new JTextField();
	private JTextField txtNombre = new JTextField();
	private JTextField txtDirector = new JTextField();
	private JTextField txtGenero = new JTextField();
	private JTextField txtDuracion = new JTextField();
	private JTextField txtIdioma = new JTextField();
	private JComboBox<String> comboBoxSubtitulo = new JComboBox<>();
	private JSpinner spinnerCalificacion = new JSpinner();
	private JTextField txtObservaciones = new JTextField();
	private JComboBox<Estado> comboBoxEstado = new JComboBox<>();
	private ControladorPelicula controladorPelicula = ControladorPelicula.getInstance();
	private JButton btnBajaPelicula = new JButton("Eliminar Pelicula");
	
	
	/**
	 * Create the frame.
	 */
	public JFormularioBajaPelicula() {
		setTitle("Baja Pelicula");

		this.getContentPane().setLayout(null);

		JLabel lblBajaPelicula = new JLabel("Baja Pelicula");
		lblBajaPelicula.setHorizontalAlignment(SwingConstants.CENTER);
		lblBajaPelicula.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblBajaPelicula.setBounds(0, 122, 1024, 38);
		this.getContentPane().add(lblBajaPelicula);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(223, 235, 229, 20);
		this.getContentPane().add(lblNombre);

		txtNombre.setBounds(467, 235, 256, 26);
		this.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pelicula pelicula = (Pelicula) ControladorPelicula.getInstance().buscarEnCache(txtNombre.getText());
				if (pelicula != null) {
					popularCampos(pelicula);
					btnBajaPelicula.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Pelicula no encontrada.");
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

		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(223, 277, 229, 20);
		this.getContentPane().add(lblDirector);
		txtDirector.setBounds(467, 277, 256, 26);
		txtDirector.setEnabled(false);
		this.getContentPane().add(txtDirector);

		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(223, 319, 229, 20);
		this.getContentPane().add(lblGenero);
		txtGenero.setBounds(467, 319, 256, 26);
		txtGenero.setEnabled(false);
		this.getContentPane().add(txtGenero);

		JLabel lblDuracion = new JLabel("Duracion (minutos)");
		lblDuracion.setBounds(223, 361, 229, 20);
		this.getContentPane().add(lblDuracion);
		txtDuracion.setBounds(467, 361, 256, 26);
		txtDuracion.setEnabled(false);
		this.getContentPane().add(txtDuracion);

		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setBounds(223, 403, 229, 20);
		this.getContentPane().add(lblIdioma);
		txtIdioma.setEnabled(false);
		txtIdioma.setBounds(467, 403, 256, 26);
		this.getContentPane().add(txtIdioma);

		JLabel lblSubtitulos = new JLabel("Subtitulos");
		lblSubtitulos.setBounds(223, 445, 229, 20);
		this.getContentPane().add(lblSubtitulos);
		comboBoxSubtitulo.setEnabled(false);
		comboBoxSubtitulo.setBounds(467, 445, 256, 26);
		this.getContentPane().add(comboBoxSubtitulo);
		comboBoxSubtitulo.setModel(new DefaultComboBoxModel<>(new String[] { "Si", "No" }));

		JLabel lblCalificacion = new JLabel("Calificacion");
		lblCalificacion.setBounds(223, 487, 229, 20);
		this.getContentPane().add(lblCalificacion);

		spinnerCalificacion.setEnabled(false);
		spinnerCalificacion.setBounds(467, 487, 256, 26);
		this.getContentPane().add(spinnerCalificacion);
		spinnerCalificacion.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(5), new Float(0.5)));

		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(223, 532, 229, 20);
		this.getContentPane().add(lblObservaciones);
		txtObservaciones.setEnabled(false);
		txtObservaciones.setBounds(467, 528, 256, 29);
		this.getContentPane().add(txtObservaciones);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(223, 574, 229, 20);
		this.getContentPane().add(lblEstado);
		comboBoxEstado.setEnabled(false);
		comboBoxEstado.setBounds(467, 571, 256, 26);
		this.getContentPane().add(comboBoxEstado);
		comboBoxEstado.setModel(new DefaultComboBoxModel<>(Estado.values()));

//		popularCampos(pelicula);

		
		btnBajaPelicula.setBounds(223, 673, 202, 26);
		btnBajaPelicula.setEnabled(false);
		this.getContentPane().add(btnBajaPelicula);
		btnBajaPelicula.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nombre = txtNombre.getText();

				if (nombre != null || nombre != "") {
					controladorPelicula.bajaPelicula(nombre);
					JOptionPane.showMessageDialog(null, "Pelicula Eliminada correctamente");
				}
				reset();

			}
		});

	}

	private void popularCampos(Pelicula pelicula) {
		this.txtId.setText(pelicula.getId().toString());
		this.txtNombre.setText(pelicula.getNombre());
		this.txtNombre.setEnabled(false);
		this.txtDirector.setText(pelicula.getDirector());
		this.txtDirector.setEnabled(true);
		this.txtGenero.setText(pelicula.getGenero());
		this.txtGenero.setEnabled(true);
		this.txtDuracion.setText(pelicula.getDuracion().toString());
		this.txtDuracion.setEnabled(true);
		this.txtIdioma.setText(pelicula.getIdioma());
		this.txtIdioma.setEnabled(true);
		if (pelicula.getSubtitulos()) {
			this.comboBoxSubtitulo.setSelectedItem("Si");
		} else
			this.comboBoxSubtitulo.setSelectedItem("No");
		this.comboBoxSubtitulo.setEnabled(true);
		this.spinnerCalificacion.setValue(pelicula.getCalificacion());
		this.spinnerCalificacion.setEnabled(true);
		this.txtObservaciones.setText(pelicula.getObservaciones());
		this.txtObservaciones.setEnabled(true);
		this.comboBoxEstado.setSelectedItem(pelicula.getEstado());
		this.comboBoxEstado.setEnabled(true);

	}

	public void reset() {
		this.txtNombre.setText("");
		this.txtNombre.setEnabled(true);
		this.txtDirector.setText("");
		this.txtDirector.setEnabled(false);
		this.txtDuracion.setText("");
		this.txtDuracion.setEnabled(false);
		this.txtGenero.setText("");
		this.txtGenero.setEnabled(false);
		this.txtIdioma.setText("");
		this.txtIdioma.setEnabled(false);
		this.comboBoxSubtitulo.setEnabled(false);
		this.txtObservaciones.setText("");
		this.txtObservaciones.setEnabled(false);
		this.spinnerCalificacion.setValue(0);
		this.spinnerCalificacion.setEnabled(false);
		this.comboBoxEstado.setEnabled(false);
		this.btnBajaPelicula.setEnabled(false);
	}
}
