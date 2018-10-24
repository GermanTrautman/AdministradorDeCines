package com.cine.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import com.cine.controlador.ControladorPelicula;
import com.cine.utilidades.EstadoActivoInactivo;

public class JFormularioModificacionPelicula extends JFormularioBase {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDirector;
	private JTextField txtGenero;
	private JTextField txtDuracion;
	private JTextField txtIdioma;
	private JTextField txtObservaciones;
	JComboBox<String> comboBoxSubtitulo;
	JSpinner spinnerClasificacion;
	JComboBox<EstadoActivoInactivo> comboBoxEstado;
	private ControladorPelicula controladorPelicula = ControladorPelicula.getInstance();
	
	/**
	 * Launch the application.
	 */

	public JFormularioModificacionPelicula() {
		setTitle("Modificar Pelicula");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 355, 408);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel lblNombre = new JLabel("Nombre");
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDirector = new JLabel("Director");
		contentPane.add(lblDirector);
		
		txtDirector = new JTextField();
		contentPane.add(txtDirector);
		txtDirector.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero");
		contentPane.add(lblGenero);
		
		txtGenero = new JTextField();
		contentPane.add(txtGenero);
		txtGenero.setColumns(10);
		
		JLabel lblDuracion = new JLabel("Duracion");
		contentPane.add(lblDuracion);
		
		txtDuracion = new JTextField();
		contentPane.add(txtDuracion);
		txtDuracion.setColumns(10);
		
		JLabel lblIdioma = new JLabel("Idioma");
		contentPane.add(lblIdioma);
		
		txtIdioma = new JTextField();
		contentPane.add(txtIdioma);
		txtIdioma.setColumns(10);
		
		JLabel lblSubtitulos = new JLabel("Subtitulos");
		contentPane.add(lblSubtitulos);
		
		comboBoxSubtitulo = new JComboBox<String>();
		contentPane.add(comboBoxSubtitulo);
		comboBoxSubtitulo.setModel(new DefaultComboBoxModel<>(new String[] {"Si", "No"}));
		
		JLabel lblClasificacion = new JLabel("Clasificacion");
		contentPane.add(lblClasificacion);
		
		spinnerClasificacion = new JSpinner();
		contentPane.add(spinnerClasificacion);
		spinnerClasificacion.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(5), new Float(0.5)));
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		contentPane.add(lblObservaciones);
		
		txtObservaciones = new JTextField();
		contentPane.add(txtObservaciones);
		txtObservaciones.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		contentPane.add(lblEstado);
		
		comboBoxEstado = new JComboBox<>();
		contentPane.add(comboBoxEstado);
		comboBoxEstado.setModel(new DefaultComboBoxModel<>(EstadoActivoInactivo.values()));
		
		JButton btnModificarPelicula = new JButton("Modificar Pelicula");
		contentPane.add(btnModificarPelicula);
		btnModificarPelicula.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
			    String director = txtDirector.getText();
			    String genero = txtGenero.getText();
			    Integer duracion = Integer.parseInt(txtDuracion.getText());
			    String idioma = txtIdioma.getText();
			    String StringSubtitulos = comboBoxSubtitulo.getSelectedItem().toString();
			    Boolean subtitulos = (StringSubtitulos == "Si");
			    Float calificacion = (Float) spinnerClasificacion.getValue();
			    String observaciones = txtObservaciones.getText();
			    EstadoActivoInactivo estado = (EstadoActivoInactivo) comboBoxEstado.getSelectedItem();
			    if (nombre != null && director !=null && genero!=null && duracion != null && idioma!=null
			    		&& subtitulos != null && calificacion != null) {
			    	controladorPelicula.modificarPelicula(nombre, director, genero, duracion, idioma, subtitulos, calificacion, observaciones, estado);
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
		this.spinnerClasificacion.setValue(0);
	}
}
