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

        JMenuItem bajaUsuario = new JMenuItem("Baja");
        items.add(bajaUsuario);
        bajaUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFormularioBajaUsuario();
                f.setVisible(true);
            }
        });
        
        JMenuItem modificarUsuario = new JMenuItem("Modificacion");
        items.add(modificarUsuario);
        modificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFormularioListarUsuarios();
                f.setVisible(true);
            }
        });

        this.setJMenuBar(menu);
        
        JMenu mnRoles = new JMenu("Roles");
        menu.add(mnRoles);
        
        JMenuItem mntmAlta_4 = new JMenuItem("Alta");
        mnRoles.add(mntmAlta_4);
        
        JMenuItem mntmBaja_4 = new JMenuItem("Baja");
        mnRoles.add(mntmBaja_4);
        
        JMenuItem mntmModificacion_4 = new JMenuItem("Modificacion");
        mnRoles.add(mntmModificacion_4);
        
        JMenu mnEstablecimientos = new JMenu("Establecimientos");
        menu.add(mnEstablecimientos);
        
        JMenuItem mntmAlta = new JMenuItem("Alta");
        mntmAlta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		JFrame formularioAltaEStablecimiento = new JFormularioAltaEstablecimiento();
        		formularioAltaEStablecimiento.setVisible(true);
        	}
        });
        mnEstablecimientos.add(mntmAlta);
        
        JMenuItem mntmBaja = new JMenuItem("Baja");
        mntmBaja.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JFrame formularioBajaEStablecimiento = new JFormularioBajaEstablecimiento();
        		formularioBajaEStablecimiento.setVisible(true);
        	}
        });
        mnEstablecimientos.add(mntmBaja);
        
        JMenuItem mntmModificacion = new JMenuItem("Modificacion");
        mntmModificacion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JFrame formularioModificacionEstablecimiento = new JFormularioListarEstablecimientos();
        		formularioModificacionEstablecimiento.setVisible(true);
        	}
        });
        mnEstablecimientos.add(mntmModificacion);
        
        JMenu mnNewMenu = new JMenu("Salas");
        menu.add(mnNewMenu);
        
        JMenuItem mntmAlta_1 = new JMenuItem("Alta");
        mntmAlta_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		JFrame formularioAltaSala = new JFormularioAltaSala();
        		formularioAltaSala.setVisible(true);
        	}
        });
        mnNewMenu.add(mntmAlta_1);
        
        JMenuItem mntmBaja_1 = new JMenuItem("Baja");
        mntmBaja_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JFrame formularioBajaSala = new JFormularioBajaSala();
        		formularioBajaSala.setVisible(true);
        	}
        });
        mnNewMenu.add(mntmBaja_1);
        
        JMenuItem mntmModificacion_1 = new JMenuItem("Modificacion");
        mntmModificacion_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JFrame formularioListarSalas = new JFormularioListarSalas();
        		formularioListarSalas.setVisible(true);
        	}
        });
        mnNewMenu.add(mntmModificacion_1);
        
        JMenu mnPeliculas = new JMenu("Peliculas");
        menu.add(mnPeliculas);
        
        JMenuItem mntmAlta_2 = new JMenuItem("Alta");
        mntmAlta_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
                
        		JFrame formularioAltaPelicula = new JFormularioAltaPelicula();
        		formularioAltaPelicula.setVisible(true);
        	}
        });
        mnPeliculas.add(mntmAlta_2);
        
        JMenuItem mntmBaja_2 = new JMenuItem("Baja");
        mntmBaja_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JFrame formularioBajaPelicula = new JFormularioBajaPelicula();
        		formularioBajaPelicula.setVisible(true);
        	}
        });
        mnPeliculas.add(mntmBaja_2);
        
        JMenuItem mntmModificacion_2 = new JMenuItem("Modificacion");
        mntmModificacion_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JFrame formularioModificacionPelicula = new JFormularioModificacionPelicula();
        		formularioModificacionPelicula.setVisible(true);
        	}
        });
        mnPeliculas.add(mntmModificacion_2);
        
        JMenu mnFunciones = new JMenu("Funciones");
        menu.add(mnFunciones);
        
        JMenuItem mntmAlta_3 = new JMenuItem("Alta");
        mnFunciones.add(mntmAlta_3);
        
        JMenuItem mntmBaja_3 = new JMenuItem("Baja");
        mnFunciones.add(mntmBaja_3);
        
        JMenuItem mntmModificacion_3 = new JMenuItem("Modificacion");
        mnFunciones.add(mntmModificacion_3);
        
        JMenu mnDescuentos = new JMenu("Descuentos");
        menu.add(mnDescuentos);
        
        JMenuItem mntmAlta_5 = new JMenuItem("Alta");
        mnDescuentos.add(mntmAlta_5);
        
        JMenuItem mntmBaja_5 = new JMenuItem("Baja");
        mnDescuentos.add(mntmBaja_5);
        
        JMenuItem mntmModificacion_5 = new JMenuItem("Modificacion");
        mnDescuentos.add(mntmModificacion_5);
    }
}
