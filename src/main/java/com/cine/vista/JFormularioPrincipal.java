package com.cine.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioPrincipal extends JFormularioBase {

    private static final long serialVersionUID = 1L;

    public JFormularioPrincipal() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menu = new JMenuBar();

        JMenu items = new JMenu("Usuarios");
        menu.add(items);

        JMenuItem opcAgregarUsuario = new JMenuItem("Alta");
        items.add(opcAgregarUsuario);
        opcAgregarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFrame f = new JFormularioAltaUsuario();
                f.setVisible(true);
            }
        });


        this.setJMenuBar(menu);
    }

}
