package com.cine.main;

import com.cine.vista.JFormularioLogin;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame panel = new JFormularioLogin();
                    panel.setVisible(true);
                    //JFormularioBase j = new JFormularioPrincipal("Administrador");
                    //j.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
