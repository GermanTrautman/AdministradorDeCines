package com.cine.main;

import com.cine.controlador.ControladorCine;
import com.cine.vista.JFormularioPrincipal;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ControladorCine.getInstance().obtenerEstablecimientos();
                    JFrame panel = new JFormularioPrincipal();
                    panel.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
