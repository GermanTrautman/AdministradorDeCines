package com.cine.main;

import com.cine.vista.JFormularioLogin;
import com.cine.vista.JFormularioPrincipal;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame panel = new JFormularioPrincipal("Administrador");
                    panel.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
