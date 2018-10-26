package com.cine.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import com.cine.controlador.ControladorPelicula;
import com.cine.modelo.Pelicula;
import com.cine.utilidades.EstadoActivoInactivo;
import com.cine.vista.modelo.ModeloTablePelicula;

public class JFormularioModificacionPelicula extends JFormularioBase {

	private static final long serialVersionUID = 1L;
//	private JPanel contentPane = new JPanel();
	private JTextField txtId = new JTextField();
	private JTextField txtNombre = new JTextField();
	private JTextField txtDirector = new JTextField();
	private JTextField txtGenero = new JTextField();
	private JTextField txtDuracion = new JTextField();
	private JTextField txtIdioma = new JTextField();
	private JTextField txtObservaciones = new JTextField();
	JComboBox<String> comboBoxSubtitulo = new JComboBox<String>();
	JSpinner spinnerClasificacion = new JSpinner();
	JComboBox<EstadoActivoInactivo> comboBoxEstado = new JComboBox<EstadoActivoInactivo>();
	private ControladorPelicula controladorPelicula = ControladorPelicula.getInstance();
	
	/**
	 * Launch the application.
	 */

	public JFormularioModificacionPelicula(ModeloTablePelicula modelo, Integer id) {
		setTitle("Modificar Pelicula");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(150, 150, 361, 409);

//		setContentPane(contentPane);
		
		getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		Pelicula pelicula = (Pelicula) controladorPelicula.buscarEnCache(id);
		
		JLabel lblId = new JLabel("Id");
		this.getContentPane().add(lblId);
		this.getContentPane().add(txtId).setEnabled(false);
		
		JLabel lblNombre = new JLabel("Nombre");
		this.getContentPane().add(lblNombre);
		this.getContentPane().add(txtNombre);
//		txtNombre.setColumns(10);
		
		JLabel lblDirector = new JLabel("Director");
		this.getContentPane().add(lblDirector);
		this.getContentPane().add(txtDirector);
		
		JLabel lblGenero = new JLabel("Genero");
		this.getContentPane().add(lblGenero);
		this.getContentPane().add(txtGenero);
		
		JLabel lblDuracion = new JLabel("Duracion");
		this.getContentPane().add(lblDuracion);
		this.getContentPane().add(txtDuracion);
		
		JLabel lblIdioma = new JLabel("Idioma");
		this.getContentPane().add(lblIdioma);
		this.getContentPane().add(txtIdioma);
		
		JLabel lblSubtitulos = new JLabel("Subtitulos");
		this.getContentPane().add(lblSubtitulos);
		this.getContentPane().add(comboBoxSubtitulo);
		comboBoxSubtitulo.setModel(new DefaultComboBoxModel<>(new String[] {"Si", "No"}));
		
		JLabel lblClasificacion = new JLabel("Clasificacion");
		this.getContentPane().add(lblClasificacion);
		
		this.getContentPane().add(spinnerClasificacion);
		spinnerClasificacion.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(5), new Float(0.5)));
		
		JLabel lblObservaciones = new JLabel("Observaciones");
		this.getContentPane().add(lblObservaciones);
		this.getContentPane().add(txtObservaciones);
		
		JLabel lblEstado = new JLabel("Estado");
		this.getContentPane().add(lblEstado);
		this.getContentPane().add(comboBoxEstado);
		comboBoxEstado.setModel(new DefaultComboBoxModel<>(EstadoActivoInactivo.values()));
		
		popularCampos(pelicula);
		
		JButton btnModificarPelicula = new JButton("Modificar Pelicula");
		this.getContentPane().add(btnModificarPelicula);
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
			    	controladorPelicula.modificarPelicula(id,nombre, director, genero, duracion, idioma, subtitulos, calificacion, observaciones, estado);
			    }
			    reset();
			    modelo.fireTableDataChanged();
			    dispose();
			}
		});
		
	}
	private void popularCampos(Pelicula pelicula) {
		this.txtId.setText(pelicula.getId().toString());
		this.txtNombre.setText(pelicula.getNombre());
		this.txtDirector.setText(pelicula.getDirector());
		this.txtGenero.setText(pelicula.getGenero());
		this.txtDuracion.setText(pelicula.getDuracion().toString());
		this.txtIdioma.setText(pelicula.getIdioma());
		if (pelicula.getSubtitulos()) {
			this.comboBoxSubtitulo.setSelectedItem("Si");
		}else this.comboBoxSubtitulo.setSelectedItem("No");
		this.spinnerClasificacion.setValue(pelicula.getCalificacion());
		this.txtObservaciones.setText(pelicula.getObservaciones());
		this.comboBoxEstado.setSelectedItem(pelicula.getEstado());
		
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
