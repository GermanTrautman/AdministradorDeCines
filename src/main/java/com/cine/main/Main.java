package com.cine.main;

import com.cine.controlador.ControladorEstablecimiento;
import com.cine.vista.JFormularioPrincipal;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ControladorEstablecimiento.getInstance().obtenerEstablecimientos();
                    JFrame panel = new JFormularioPrincipal();
                    panel.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
