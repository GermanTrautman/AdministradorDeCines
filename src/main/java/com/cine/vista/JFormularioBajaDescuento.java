package com.cine.vista;

import javax.swing.*;
import java.awt.*;

public class JFormularioBajaDescuento extends JFormularioBase {
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextField txtCantidad;

    /**
     * Create the panel.
     */
    public JFormularioBajaDescuento() {
        setLayout(null);

        txtNombre = new JTextField();
        txtNombre.setBounds(467, 195, 256, 26);
        add(txtNombre);
        txtNombre.setColumns(10);

        txtEmail = new JTextField();
        txtEmail.setEnabled(false);
        txtEmail.setBounds(467, 237, 256, 26);
        add(txtEmail);
        txtEmail.setColumns(10);

        txtCantidad = new JTextField();
        txtCantidad.setEnabled(false);
        txtCantidad.setBounds(467, 279, 256, 26);
        add(txtCantidad);
        txtCantidad.setColumns(10);

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(223, 670, 115, 29);
        add(btnBorrar);

        JLabel lblBajaDescuentos = new JLabel("Baja Descuento");
        lblBajaDescuentos.setHorizontalAlignment(SwingConstants.CENTER);
        lblBajaDescuentos.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblBajaDescuentos.setBounds(0, 122, 1024, 38);
        add(lblBajaDescuentos);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(223, 198, 229, 20);
        add(lblNombre);

        JLabel lblPorcentaje = new JLabel("Porcentaje");
        lblPorcentaje.setBounds(223, 240, 229, 20);
        add(lblPorcentaje);

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setBounds(223, 282, 229, 20);
        add(lblCantidad);

        JLabel lblDomicilio = new JLabel("Domicilio");
        lblDomicilio.setBounds(223, 366, 229, 20);
        add(lblDomicilio);

        JCheckBox chckbx2x1 = new JCheckBox("2x1");
        chckbx2x1.setEnabled(false);
        chckbx2x1.setBounds(467, 362, 256, 29);
        add(chckbx2x1);

        JCheckBox chckbxDescuento = new JCheckBox("Descuento");
        chckbxDescuento.setEnabled(false);
        chckbxDescuento.setBounds(467, 404, 256, 29);
        add(chckbxDescuento);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(748, 194, 115, 29);
        add(btnBuscar);

    }
}