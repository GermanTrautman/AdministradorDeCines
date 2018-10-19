package com.cine.main;

import com.cine.modelo.Usuario;
import com.cine.vista.Principal;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.framePrincipal.setVisible(true);
                    Usuario usr = new Usuario();

                    usr.setNombreDeUsuario("Macri");
                    usr.setNombre("Gato");
                    usr.setDni(23453432);
                    usr.insertarUsuario(usr);

                    usr.buscarUsuario(39459185);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
