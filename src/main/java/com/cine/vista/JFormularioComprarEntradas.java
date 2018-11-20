package com.cine.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.controlador.ControladorFuncion;
import com.cine.controlador.ControladorPelicula;
import com.cine.modelo.Establecimiento;
import com.cine.modelo.Funcion;
import com.cine.modelo.Pelicula;
import com.cine.vista.modelo.ComboFecha;
import com.cine.vista.modelo.ComboHorario;

public class JFormularioComprarEntradas extends JFormularioBase {

	private static final long serialVersionUID = -763706419019323146L;
	
	private JTextField cuitEstablecimiento = new JTextField();
	private JTextField nombreEstablecimiento = new JTextField();
	private JTextField nombrePelicula = new JTextField();
	private JTextField banco;
	private JTextField numeroTarjeta;
	
	private	JComboBox<ComboFecha> fecha = new JComboBox<>();
	private JComboBox<ComboHorario> horario = new JComboBox();
	private JComboBox cantidadDeEntradas = new JComboBox();
	private JComboBox formaDePago = new JComboBox();
	
	private JSpinner mesYAnioVencimientoTarjeta;
	
	public JFormularioComprarEntradas() {
		
		getContentPane().setLayout(null);
		
		JLabel lblAltasalas = new JLabel("Comprar entradas");
		lblAltasalas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltasalas.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblAltasalas.setBounds(0, 13, 1024, 38);
		this.getContentPane().add(lblAltasalas);
		
		JLabel lblCuit = new JLabel("CUIT establecimiento");
		lblCuit.setBounds(223, 64, 229, 26);
		this.getContentPane().add(lblCuit);
		
		this.cuitEstablecimiento.setBounds(467, 64, 256, 26);
		this.cuitEstablecimiento.setColumns(10);
		this.getContentPane().add(cuitEstablecimiento);
		
		JButton btnBuscarEstablecimiento = new JButton("Buscar");
		btnBuscarEstablecimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (cuitEstablecimiento.getText() != null) {

					Establecimiento establecimiento = ControladorEstablecimiento.getInstance().buscar(Integer.parseInt(cuitEstablecimiento.getText()));
					popularEstablecimiento(establecimiento);
					
					JOptionPane.showMessageDialog(null, "Establecimiento encontrado.");
					
				} else {
					JOptionPane.showMessageDialog(null, "Establecimiento no encontrado.");
				}
			}
		});
		btnBuscarEstablecimiento.setBounds(745, 64, 115, 29);
		this.getContentPane().add(btnBuscarEstablecimiento);
		
		JLabel lblNombreEstablecimiento = new JLabel("Nombre establecimiento");
		lblNombreEstablecimiento.setBounds(223, 103, 229, 25);
		this.getContentPane().add(lblNombreEstablecimiento);
		
		this.nombreEstablecimiento = new JTextField();
		this.nombreEstablecimiento.setEnabled(false);
		this.nombreEstablecimiento.setBounds(467, 103, 256, 26);
		this.nombreEstablecimiento.setColumns(10);
		this.getContentPane().add(this.nombreEstablecimiento);
		
		JLabel lblNombrePelicula = new JLabel("Nombre pel\u00EDcula");
		lblNombrePelicula.setBounds(223, 145, 229, 20);
		this.getContentPane().add(lblNombrePelicula);

		nombrePelicula.setBounds(467, 142, 256, 26);
		this.getContentPane().add(nombrePelicula);
		nombrePelicula.setColumns(10);

		JButton btnBuscarPelicula = new JButton("Buscar");
		btnBuscarPelicula.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Pelicula pelicula = (Pelicula) ControladorPelicula.getInstance().buscarEnCache(nombrePelicula.getText());
				
				if (pelicula != null) {
					
					JOptionPane.showMessageDialog(null, "Pelicula encontrada.");
					
					List<Funcion> funciones = ControladorFuncion.getInstance().buscarEnPor(Integer.parseInt(cuitEstablecimiento.getText()),pelicula.getNombre());
					
					popularDia(funciones);
					popularHorario(funciones);
					popularCantidadEntradas();
					
				} else {
					JOptionPane.showMessageDialog(null, "Pelicula no encontrada.");
				}
			}
		});
		btnBuscarPelicula.setBounds(745, 141, 115, 29);
		this.getContentPane().add(btnBuscarPelicula);

		JLabel lblDia = new JLabel("D\u00EDa");
		lblDia.setBounds(223, 188, 229, 25);
		getContentPane().add(lblDia);
		
		fecha.setBounds(467, 187, 256, 26);
		getContentPane().add(fecha);

		JLabel lblNewLabel = new JLabel("Hora");
		lblNewLabel.setBounds(223, 230, 229, 25);
		getContentPane().add(lblNewLabel);
		
		horario.setBounds(467, 230, 256, 25);
		getContentPane().add(horario);
		
		JLabel lblCantidadDeEnetradas = new JLabel("Cantidad de entradas");
		lblCantidadDeEnetradas.setBounds(223, 277, 229, 26);
		getContentPane().add(lblCantidadDeEnetradas);
		
		cantidadDeEntradas.setBounds(467, 277, 256, 26);
		getContentPane().add(cantidadDeEntradas);
		
		JLabel lblUbicacion = new JLabel("Ubicacion");
		lblUbicacion.setBounds(223, 326, 229, 26);
		getContentPane().add(lblUbicacion);
		
		JLabel lblFormaDePago = new JLabel("Forma de pago");
		lblFormaDePago.setBounds(223, 773, 229, 25);
		getContentPane().add(lblFormaDePago);
		
		formaDePago.setBounds(467, 772, 256, 26);
		getContentPane().add(formaDePago);
		
		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setBounds(223, 811, 77, 26);
		getContentPane().add(lblBanco);
		
		banco = new JTextField();
		banco.setBounds(467, 813, 256, 22);
		getContentPane().add(banco);
		banco.setColumns(10);
		
		JLabel lblNumeroDeTarjeta = new JLabel("Numero de tarjeta");
		lblNumeroDeTarjeta.setBounds(223, 850, 229, 20);
		getContentPane().add(lblNumeroDeTarjeta);
		
		numeroTarjeta = new JTextField();
		numeroTarjeta.setBounds(467, 848, 256, 22);
		getContentPane().add(numeroTarjeta);
		numeroTarjeta.setColumns(10);
		
		JLabel lblMesYAo = new JLabel("Mes y a\u00F1o de vencimiento");
		lblMesYAo.setBounds(223, 889, 229, 20);
		getContentPane().add(lblMesYAo);
		
		SpinnerDateModel SFecha = new SpinnerDateModel();
		mesYAnioVencimientoTarjeta = new JSpinner(SFecha);
		DateEditor dateEditor = new DateEditor(mesYAnioVencimientoTarjeta, "MM/yyyy");
		mesYAnioVencimientoTarjeta.setEditor(dateEditor);
		mesYAnioVencimientoTarjeta.setBounds(467, 884, 256, 25);
		this.getContentPane().add(mesYAnioVencimientoTarjeta);
		
		JButton btnNewButton = new JButton("Continuar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(223, 953, 115, 25);
		getContentPane().add(btnNewButton);
	}

	private void popularEstablecimiento(Establecimiento establecimiento) {
		
		if (establecimiento != null) {
			this.nombreEstablecimiento.setText(establecimiento.getNombre());
		}
	}
	
	private void popularDia(List<Funcion> funciones) {
		
		Integer id = 0;
		
		for (Funcion funcion : funciones) {
			fecha.addItem(new ComboFecha(funcion.getFecha(), id++));
		}
	}
	
	private void popularHorario(List<Funcion> funciones) {
		
		Integer id = 0;
		
		for (Funcion funcion : funciones) {
			horario.addItem(new ComboHorario(funcion.getHora(), id++));
		}
	}

	private void popularCantidadEntradas() {
		
		for(int i = 1; i < 11; i++)
			cantidadDeEntradas.addItem(i);
	}
}
