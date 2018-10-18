package com.cine.main;

import com.cine.modelo.Usuario;
import com.cine.vista.Principal;

import java.awt.*;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Usuario usr = new Usuario();
                    Principal window = new Principal();
                    window.framePrincipal.setVisible(true);
                    Usuario usuario = new Usuario();
                    ResultSet lala = usuario.buscarUsuario();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
