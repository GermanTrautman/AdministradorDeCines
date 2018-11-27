package com.cine.vista;

import com.cine.vista.JFormularioBase;

import javax.swing.*;
import java.awt.*;

public class JFormularioModificarDescuento extends JFormularioBase {
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextField txtCantidad;

    /**
     * Create the panel.
     */
    public JFormularioModificarDescuento() {
        setLayout(null);

        txtNombre = new JTextField();
        txtNombre.setBounds(467, 195, 256, 26);
        add(txtNombre);
        txtNombre.setColumns(10);

        txtEmail = new JTextField();
        txtEmail.setBounds(467, 237, 256, 26);
        add(txtEmail);
        txtEmail.setColumns(10);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(467, 279, 256, 26);
        add(txtCantidad);
        txtCantidad.setColumns(10);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(223, 670, 115, 29);
        add(btnModificar);

        JLabel lblModificacionDescuentos = new JLabel("Modificaci\u00F3n Descuento");
        lblModificacionDescuentos.setHorizontalAlignment(SwingConstants.CENTER);
        lblModificacionDescuentos.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblModificacionDescuentos.setBounds(0, 122, 1024, 38);
        add(lblModificacionDescuentos);

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
        chckbx2x1.setBounds(467, 362, 256, 29);
        add(chckbx2x1);

        JCheckBox chckbxDescuento = new JCheckBox("Descuento");
        chckbxDescuento.setBounds(467, 404, 256, 29);
        add(chckbxDescuento);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(748, 194, 115, 29);
        add(btnBuscar);

    }
}