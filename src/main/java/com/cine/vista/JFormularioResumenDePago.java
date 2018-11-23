package com.cine.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.cine.controlador.ControladorVenta;
import com.cine.modelo.AsientoVirtual;
import com.cine.modelo.Venta;

public class JFormularioResumenDePago extends JFormularioBase {

	private static final long serialVersionUID = -5812919621294989706L;

	private JLabel establecimiento = new JLabel();
	private JLabel pelicula = new JLabel();
	private JLabel dia = new JLabel();
	private JLabel horario = new JLabel();
	private JLabel entradas = new JLabel();
	private JLabel monto = new JLabel("New label");

	public JFormularioResumenDePago(Venta venta) {

		getContentPane().setLayout(null);

		popularCampos(venta);

		JLabel lblResumenDePago = new JLabel("Resumen de pago");
		lblResumenDePago.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumenDePago.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblResumenDePago.setBounds(0, 13, 1024, 38);
		this.getContentPane().add(lblResumenDePago);

		JLabel lblEstablecimiento = new JLabel("Establecimiento");
		lblEstablecimiento.setBounds(257, 141, 211, 22);
		getContentPane().add(lblEstablecimiento);

		JLabel lblNewLabel = new JLabel("Pelicula");
		lblNewLabel.setBounds(257, 191, 211, 16);
		getContentPane().add(lblNewLabel);

		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(257, 234, 211, 16);
		getContentPane().add(lblDia);

		establecimiento.setBounds(509, 144, 224, 16);
		getContentPane().add(establecimiento);

		pelicula.setBounds(509, 191, 224, 16);
		getContentPane().add(pelicula);

		dia.setBounds(509, 234, 224, 16);
		getContentPane().add(dia);

		JLabel lblHorario = new JLabel("Horario");
		lblHorario.setBounds(257, 280, 211, 16);
		getContentPane().add(lblHorario);

		horario.setBounds(509, 280, 224, 16);
		getContentPane().add(horario);

		JLabel lblNewLabel_5 = new JLabel("Entradas");
		lblNewLabel_5.setBounds(257, 321, 211, 16);
		getContentPane().add(lblNewLabel_5);

		entradas.setBounds(509, 321, 224, 16);
		getContentPane().add(entradas);

		JLabel lblNewLabel_7 = new JLabel("Monto");
		lblNewLabel_7.setBounds(257, 362, 211, 16);
		getContentPane().add(lblNewLabel_7);

		monto.setBounds(509, 362, 224, 16);
		getContentPane().add(monto);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnNewButton.setBounds(257, 436, 97, 25);
		getContentPane().add(btnNewButton);

		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ControladorVenta.getInstance().altaVenta(venta.getCodigo(), venta.getUsuario(), venta.getFuncion(),
						venta.getAsientosAdquiridos(), venta.getMonto(), venta.getFormaDePago(), venta.getTipoVenta(), venta.getTarjeta());
				
				JOptionPane.showMessageDialog(null, "El codigo de reserva es: " + venta.getCodigo());
				dispose();
			}
		});
		btnPagar.setBounds(509, 436, 97, 25);
		getContentPane().add(btnPagar);
	}

	private void popularCampos(Venta venta) {

		establecimiento.setText(venta.getFuncion().getSala().getEstablecimiento().getNombre());
		pelicula.setText(venta.getFuncion().getPelicula().getNombre());
		dia.setText(venta.getFuncion().getFecha().toString());
		horario.setText(venta.getFuncion().getHora().toString());

		String filaYAsiento = "";

		for (AsientoVirtual asientoVirtual : venta.getAsientosAdquiridos()) {

			filaYAsiento.concat("Fila: " + asientoVirtual.getFisicoAsociado().getFila().toString());
			filaYAsiento.concat(" - Asiento: " + asientoVirtual.getFisicoAsociado().getNumeroDeAsiento().toString());
			filaYAsiento.concat(" ");
		}

		entradas.setText(filaYAsiento);

		monto.setText("$" + venta.getMonto().toString());
	}
}
