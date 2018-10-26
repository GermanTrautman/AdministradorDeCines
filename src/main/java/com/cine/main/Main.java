package com.cine.main;

import com.cine.controlador.ControladorPelicula;
import com.cine.controlador.ControladorEstablecimiento;
import com.cine.controlador.ControladorSala;
import com.cine.controlador.ControladorUsuario;
import com.cine.vista.JFormularioPrincipal;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ControladorPelicula.getInstance().obtenerPeliculas();
                    ControladorEstablecimiento.getInstance().obtenerEstablecimientos();
                    ControladorSala.getInstance().obtenerSalas();
                    ControladorUsuario.getInstance().obtenerUsuariosDb();
                    JFrame panel = new JFormularioPrincipal();
                    panel.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
