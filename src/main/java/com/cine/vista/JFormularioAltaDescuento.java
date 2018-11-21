package com.cine.vista;

import com.cine.controlador.ControladorDescuentoEstablecimiento;
import com.cine.controlador.ControladorVenta;
import com.cine.modelo.DescuentoTipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Date;

public class JFormularioAltaDescuento extends JFormularioBase {
    private final JSpinner spinnerFechaFin;
    private JTextField txtNombre;
    private JTextField txtCuitEstablecimiento;
    private JTextField txtPorcentaje;
    private JTextField txtCantidad;
    private JCheckBox chckbx2x1;
    private JCheckBox chckbxDescuento;
    private JSpinner spinnerFechaInicio;

    /**
     * Create the panel.
     */
    public JFormularioAltaDescuento() {
        setLayout(null);

        txtCuitEstablecimiento = new JTextField();
        txtCuitEstablecimiento.setBounds(467, 195, 256, 26);
        add(txtCuitEstablecimiento);
        txtCuitEstablecimiento.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setBounds(467, 237, 256, 26);
        add(txtNombre);
        txtNombre.setColumns(10);

        txtPorcentaje = new JTextField();
        txtPorcentaje.setBounds(467, 279, 256, 26);
        add(txtPorcentaje);
        txtPorcentaje.setColumns(10);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(467, 324, 256, 26);
        add(txtCantidad);
        txtCantidad.setColumns(10);

        SpinnerDateModel SFecha = new SpinnerDateModel();
        spinnerFechaInicio = new JSpinner(SFecha);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerFechaInicio, "dd/MMM/yyyy");
        spinnerFechaInicio.setEditor(dateEditor);
        spinnerFechaInicio.setBounds(467, 380, 256, 26);
        this.getContentPane().add(spinnerFechaInicio);

        SpinnerDateModel SFechaFin = new SpinnerDateModel();
        spinnerFechaFin = new JSpinner(SFechaFin);
        JSpinner.DateEditor dateEditor1 = new JSpinner.DateEditor(spinnerFechaFin, "dd/MMM/yyyy");
        spinnerFechaFin.setEditor(dateEditor1);
        spinnerFechaFin.setBounds(467, 420, 256, 26);
        this.getContentPane().add(spinnerFechaFin);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(223, 670, 115, 29);
        add(btnCrear);

        btnCrear.addActionListener(actionEvent -> {
            ControladorVenta.getInstance().altaDescuento(txtNombre.getText(), Integer.parseInt(txtPorcentaje.getText()), Integer.parseInt(txtCantidad.getText()), isSelected());

            ControladorDescuentoEstablecimiento.getInstance().altaDescuentoEstablecimiento(Integer.parseInt(txtCuitEstablecimiento.getText()),
                    ControladorVenta.getInstance().buscarDescuentoPorNombre(txtNombre.getText()).getIdDescuento(),
                    ((Date) spinnerFechaInicio.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    ((Date) spinnerFechaFin.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            JOptionPane.showMessageDialog(null, "Descuento creado correctamente");
        });


        JLabel lblAltaDescuentos = new JLabel("Alta Descuento");
        lblAltaDescuentos.setHorizontalAlignment(SwingConstants.CENTER);
        lblAltaDescuentos.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblAltaDescuentos.setBounds(0, 122, 1024, 38);
        add(lblAltaDescuentos);

        JLabel lblCuitEstablecimiento = new JLabel("CUIT establecimiento");
        lblCuitEstablecimiento.setBounds(223, 195, 229, 20);
        add(lblCuitEstablecimiento);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(223, 237, 229, 20);
        add(lblNombre);

        JLabel lblPorcentaje = new JLabel("Porcentaje");
        lblPorcentaje.setBounds(223, 279, 229, 20);
        add(lblPorcentaje);

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setBounds(223, 324, 229, 20);
        add(lblCantidad);

        JLabel lblVigencia = new JLabel("Vigencia");
        lblVigencia.setBounds(223, 380, 229, 20);
        add(lblVigencia);

        chckbx2x1 = new JCheckBox("2x1");
        chckbx2x1.setBounds(467, 480, 256, 29);
        add(chckbx2x1);

        chckbxDescuento = new JCheckBox("Descuento");
        chckbxDescuento.setBounds(467, 530, 256, 29);
        add(chckbxDescuento);

        chckbxDescuento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtPorcentaje.setEnabled(true);
                txtCantidad.setEnabled(false);
                txtCantidad.setText("1");
            }
        });


        chckbx2x1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                txtCantidad.setEnabled(true);
                txtPorcentaje.setEnabled(false);
                txtPorcentaje.setText("50");
            }
        });
    }

    public DescuentoTipo isSelected() {

        if (chckbx2x1.isSelected()) {
            return DescuentoTipo.DOSXUNO;
        }
        if (chckbxDescuento.isSelected()) {
            return DescuentoTipo.PORCENTAJE;
        }
        return null;
    }
}