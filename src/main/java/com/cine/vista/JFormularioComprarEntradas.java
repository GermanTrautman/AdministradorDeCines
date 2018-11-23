package com.cine.vista;

import com.cine.controlador.ControladorDescuentoEstablecimiento;
import com.cine.controlador.ControladorEstablecimiento;
import com.cine.controlador.ControladorFuncion;
import com.cine.controlador.ControladorPelicula;
import com.cine.modelo.*;
import com.cine.observer.IObservador;
import com.cine.utilidades.*;
import com.cine.vista.modelo.*;

import javax.swing.*;
import javax.swing.JSpinner.DateEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JFormularioComprarEntradas extends JFormularioBase implements IObservador {

	private static final long serialVersionUID = -763706419019323146L;

	private JTextField cuitEstablecimiento = new JTextField();
	private JTextField nombreEstablecimiento = new JTextField();
	private JTextField nombrePelicula = new JTextField();
	private JTextField numeroTarjeta;
	private JTextField asientosSeleccionados;

	private JComboBox<ComboFecha> fecha = new JComboBox<>();
	private JComboBox<ComboHorario> horario = new JComboBox<>();
	private JComboBox<Integer> cantidadDeEntradas = new JComboBox<>();
	private JComboBox<ComboFormaDePago> formaDePago = new JComboBox<>();
	private JComboBox<ComboDescuentos> descuentos = new JComboBox<>();
	private JComboBox<ComboBancos> bancos = new JComboBox<>();

	private JButton seleccionarAsientos = new JButton("Seleccionar asientos");
	private JButton continuar = new JButton("Continuar");

	private JSpinner mesYAnioVencimientoTarjeta;

	private Pelicula peliculaSeleccionada = null;
	private List<Funcion> funcionesSeleccionadas = new ArrayList<>();
	private Funcion funcionSeleccionada = null;


	public JFormularioComprarEntradas(Usuario usuario) {

		getContentPane().setLayout(null);

		JLabel lblComprarEntradas = new JLabel("Comprar entradas");
		lblComprarEntradas.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprarEntradas.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblComprarEntradas.setBounds(0, 13, 1024, 38);
		this.getContentPane().add(lblComprarEntradas);

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

					Establecimiento establecimiento = ControladorEstablecimiento.getInstance()
							.buscar(Integer.parseInt(cuitEstablecimiento.getText()));
					popularEstablecimiento(establecimiento);
					popularDescuentos(Integer.parseInt(cuitEstablecimiento.getText()));
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

				peliculaSeleccionada = (Pelicula) ControladorPelicula.getInstance()
						.buscarEnCache(nombrePelicula.getText());

				if (peliculaSeleccionada != null) {

					JOptionPane.showMessageDialog(null, "Pelicula encontrada.");

					funcionesSeleccionadas = ControladorFuncion.getInstance().buscarPor(
							Integer.parseInt(cuitEstablecimiento.getText()), peliculaSeleccionada.getNombre());

					popularDia();
					popularHorario();
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
		seleccionarAsientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFrame formularioAsientoCompra = new JFormularioAsientoCompra(funcionSeleccionada,usuario);
				formularioAsientoCompra.setVisible(true);


			}

		});
		seleccionarAsientos.addChangeListener(changeEvent -> actualizar());

		seleccionarAsientos.setBounds(223, 335, 177, 25);
		getContentPane().add(seleccionarAsientos);

		this.asientosSeleccionados = new JTextField();
		this.asientosSeleccionados.setEnabled(false);
		this.asientosSeleccionados.setBounds(467, 335, 256, 35);
		this.asientosSeleccionados.setColumns(10);
		this.getContentPane().add(this.asientosSeleccionados);

		JLabel lblFormaDePago = new JLabel("Forma de pago");
		lblFormaDePago.setBounds(223, 387, 229, 25);
		getContentPane().add(lblFormaDePago);

		formaDePago.setBounds(467, 386, 256, 26);
		getContentPane().add(formaDePago);

		popularFormaDePago();

		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setBounds(223, 439, 229, 26);
		getContentPane().add(lblBanco);

		bancos.setBounds(467, 437, 256, 26);
		getContentPane().add(bancos);

		popularBancos();

		JLabel lblNumeroDeTarjeta = new JLabel("Numero de tarjeta");
		lblNumeroDeTarjeta.setBounds(223, 489, 229, 20);
		getContentPane().add(lblNumeroDeTarjeta);

		numeroTarjeta = new JTextField();
		numeroTarjeta.setBounds(467, 489, 256, 22);
		getContentPane().add(numeroTarjeta);
		numeroTarjeta.setColumns(10);

		JLabel lblMesYAo = new JLabel("Mes y a\u00F1o de vencimiento");
		lblMesYAo.setBounds(223, 543, 229, 20);
		getContentPane().add(lblMesYAo);

		SpinnerDateModel SFecha = new SpinnerDateModel();
		mesYAnioVencimientoTarjeta = new JSpinner(SFecha);
		DateEditor dateEditor = new DateEditor(mesYAnioVencimientoTarjeta, "MM/yyyy");
		mesYAnioVencimientoTarjeta.setEditor(dateEditor);
		mesYAnioVencimientoTarjeta.setBounds(467, 541, 256, 25);
		this.getContentPane().add(mesYAnioVencimientoTarjeta);

		JLabel lblDescuentos = new JLabel("Forma de pago");
		lblDescuentos.setBounds(223, 650, 229, 25);
		getContentPane().add(lblDescuentos);

		descuentos.setBounds(467, 650, 256, 26);
		getContentPane().add(descuentos);



		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DescuentoStrategy descuentoStrategy = new DescuentoStrategy();
				ComboDescuentos comboDescuentos =(ComboDescuentos) descuentos.getModel().getSelectedItem();
				Double monto = descuentoStrategy.getStrategy(DescuentoProvider.valueOf(comboDescuentos.getDescuentoProvider().getTipo().toString()))
						.calcularMonto((Double.parseDouble(cantidadDeEntradas.getSelectedItem().toString())*100),comboDescuentos.getDescuentoProvider().getPorcentaje());

				ComboFormaDePago formaDePagoSeleccionada = (ComboFormaDePago) formaDePago.getSelectedItem();
				//To-do: Hay que obtener los asientos virtuales seleccionados de la pantalla de seleccion
				Venta venta = new Venta(usuario, funcionSeleccionada, new ArrayList<>(), monto, formaDePagoSeleccionada.getFormaDePago());
				venta = obtenerDatosTarjeta(venta);

				JFrame formularioResumenDePago = new JFormularioResumenDePago(venta);
				formularioResumenDePago.setVisible(true);
			}
		});

		continuar.setBounds(223, 766, 115, 25);
		getContentPane().add(continuar);
	}

	private void popularEstablecimiento(Establecimiento establecimiento) {

		if (establecimiento != null) {
			this.nombreEstablecimiento.setText(establecimiento.getNombre());
		}
	}

	private void popularDia() {

		Integer id = 0;

		for (Funcion funcion : funcionesSeleccionadas) {
			fecha.addItem(new ComboFecha(funcion.getFecha(), id++));
		}
	}

	private void popularHorario() {

		Integer id = 0;

		for (Funcion funcion : funcionesSeleccionadas) {
			horario.addItem(new ComboHorario(funcion.getHora(), id++));
		}
	}

	private void popularFormaDePago() {

		Integer id = 0;

		for (FormaDePago itemFormaDePago : FormaDePago.values()) {
			formaDePago.addItem(new ComboFormaDePago(itemFormaDePago, id++));
		}
	}

	List<Descuento> desc;

	private void popularDescuentos(Integer cuitEstablecimiento) {

		Integer id = 0;
		desc = ControladorDescuentoEstablecimiento.getInstance().obtenerDescuentosPorEstablecimiento(cuitEstablecimiento);

		for (Descuento itemDescuentos : desc) {
			descuentos.addItem(new ComboDescuentos(itemDescuentos, id++));
		}
	}

	private void popularBancos() {

		Integer id = 0;

		for (Banco itemBanco : Banco.values()) {
			bancos.addItem(new ComboBancos(itemBanco, id++));
		}
	}

	private void popularCantidadEntradas() {

		ComboFecha fechaSeleccionada = (ComboFecha) this.fecha.getSelectedItem();
		ComboHorario horarioSeleccionado = (ComboHorario) this.horario.getSelectedItem();

		funcionSeleccionada = buscarFuncionPorFechaYHora(fechaSeleccionada.getFecha(),
				horarioSeleccionado.getHorario());

		Integer asientosLibres = 0;

		for (int i = 1; i < AsientoVirtual.FILAS; i++) {

			for (int j = 1; j < AsientoVirtual.ASIENTOSPORFILA; j++) {

				if (funcionSeleccionada.getAsientoVirtual()[i][j] != null
						&& funcionSeleccionada.getAsientoVirtual()[i][j].getEstado().equals(EstadoVirtual.LIBRE)) {

					asientosLibres++;
					cantidadDeEntradas.addItem(asientosLibres);
				}
			}
		}
	}

	private Funcion buscarFuncionPorFechaYHora(Date fecha, LocalTime hora) {

		Funcion funcionBuscada = null;

		for (Funcion funcion : funcionesSeleccionadas) {

			if (funcion.getFecha().equals(fecha) && funcion.getHora().equals(hora)) {
				funcionBuscada = funcion;
			}
		}

		return funcionBuscada;
	}

	private Venta obtenerDatosTarjeta(Venta venta) {

		ComboBancos bancoSeleccionado = (ComboBancos) bancos.getSelectedItem();
		Long numeroDeTarjeta = Long.parseLong(numeroTarjeta.getText());
		Date mesYAnioVencimientoSeleccionado = (Date) mesYAnioVencimientoTarjeta.getValue();

		Tarjeta tarjeta = new Tarjeta(bancoSeleccionado.getBanco(), numeroDeTarjeta, mesYAnioVencimientoSeleccionado);
		venta.setTarjeta(tarjeta);

		return venta;
	}

	@Override
	public void actualizar() {
		asientosSeleccionados.setText(ControladorFuncion.getInstance().getAsientoVirtuals().values().toString());
	}
}
