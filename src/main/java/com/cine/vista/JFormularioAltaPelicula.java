package com.cine.vista;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.cine.controlador.ControladorPelicula;
import com.cine.utilidades.EstadoActivoInactivo;

public class JFormularioAltaPelicula extends JFormularioBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtNombre;
	private JTextField txtDirector;
	private JTextField txtGenero;
	private JTextField txtDuracion;
	private JTextField txtIdioma;
	private JTextField txtObservaciones;
	JComboBox<String> comboBoxSubtitulo;
	JSpinner spinnerCalificacion;
	JComboBox<EstadoActivoInactivo> comboBoxEstado;
	

	/**
	 * Create the frame.
	 */
	public JFormularioAltaPelicula() {
		
		

		//setContentPane(contentPane);
		this.getContentPane().setLayout(null);
		JLabel lblAltaSalas = new JLabel("Alta Pel\u00EDculas");
		lblAltaSalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltaSalas.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltaSalas.setBounds(0, 122, 1024, 38);
		getContentPane().add(lblAltaSalas);
		
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(223, 235, 229, 20);
		this.getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(467, 235, 256, 26);
		this.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(223, 277, 229, 20);
		this.getContentPane().add(lblDirector);
		
		txtDirector = new JTextField();
		this.getContentPane().add(txtDirector);
		txtDirector.setBounds(467, 277, 256, 26);
		txtDirector.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(223, 319, 229, 20);
		this.getContentPane().add(lblGenero);
		
		txtGenero = new JTextField();
		txtGenero.setBounds(467, 319, 256, 26);
		this.getContentPane().add(txtGenero);
		txtGenero.setColumns(10);
		
		JLabel lblDuracion = new JLabel("Duracion (minutos)");
		lblDuracion.setBounds(223, 361, 229, 20);
		this.getContentPane().add(lblDuracion);
		
		txtDuracion = new JTextField();
		txtDuracion.setBounds(467, 361, 256, 26);
		this.getContentPane().add(txtDuracion);
		txtDuracion.setColumns(10);
		
		JLabel lblIdioma = new JLabel("Idioma");
		lblIdioma.setBounds(223, 403, 229, 20);
		this.getContentPane().add(lblIdioma);
		
		txtIdioma = new JTextField();
		this.getContentPane().add(txtIdioma);
		txtIdioma.setBounds(467, 403, 256, 26);
		txtIdioma.setColumns(10);
		
		JLabel lblSubtitulos = new JLabel("Subtitulos");
		lblSubtitulos.setBounds(223, 445, 229, 20);
		this.getContentPane().add(lblSubtitulos);
		
		comboBoxSubtitulo = new JComboBox<String>();
		comboBoxSubtitulo.setBounds(467, 445, 256, 26);
		this.getContentPane().add(comboBoxSubtitulo);
		comboBoxSubtitulo.setModel(new DefaultComboBoxModel<>(new String[] {"Si", "No"}));
		
		JLabel lblCalificacion = new JLabel("Calificacion");
		lblCalificacion.setBounds(223, 487, 229, 20);
		this.getContentPane().add(lblCalificacion);
		
		spinnerCalificacion = new JSpinner();
		spinnerCalificacion.setBounds(467, 487, 256, 26);
		this.getContentPane().add(spinnerCalificacion);
		spinnerCalificacion.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(5), new Float(0.5)));
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(223, 532, 229, 20);
		this.getContentPane().add(lblObservaciones);
		
		txtObservaciones = new JTextField();
		this.getContentPane().add(txtObservaciones);
		txtObservaciones.setBounds(467, 528, 256, 29);
		txtObservaciones.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(223, 574, 229, 20);
		this.getContentPane().add(lblEstado);
		
		comboBoxEstado = new JComboBox<>();
		comboBoxEstado.setBounds(467, 571, 256, 26);
		this.getContentPane().add(comboBoxEstado);
		comboBoxEstado.setModel(new DefaultComboBoxModel<>(EstadoActivoInactivo.values()));
		
		JButton btnAgregarPelicula = new JButton("Agregar Pelicula");
		btnAgregarPelicula.setBounds(223, 670, 151, 26);
		this.getContentPane().add(btnAgregarPelicula);
		btnAgregarPelicula.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
			    String director = txtDirector.getText();
			    String genero = txtGenero.getText();
			    Integer duracion = Integer.parseInt(txtDuracion.getText());
			    String idioma = txtIdioma.getText();
			    String StringSubtitulos = comboBoxSubtitulo.getSelectedItem().toString();
			    Boolean subtitulos = (StringSubtitulos == "Si");
			    Float calificacion = (Float) spinnerCalificacion.getValue();
			    String observaciones = txtObservaciones.getText();
			    EstadoActivoInactivo estado = (EstadoActivoInactivo) comboBoxEstado.getSelectedItem();
			    if (nombre != null && director !=null && genero!=null && duracion != null && idioma!=null
			    		&& subtitulos != null && calificacion != null) {
			    	ControladorPelicula.getInstance().altaPelicula(nombre, director, genero, duracion, idioma, subtitulos, calificacion, observaciones, estado);
			    }
			    reset();
			    setVisible(false);
			}
		});
		
	}
	public void reset () {
		this.txtNombre.setText("");
		this.txtDirector.setText("");
		this.txtDuracion.setText("");
		this.txtGenero.setText("");
		this.txtIdioma.setText("");
		this.txtObservaciones.setText("");
		this.spinnerCalificacion.setValue(0);
	}
}
