package com.cine.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFormularioPrincipal extends JFormularioBase {

    private static final long serialVersionUID = 1L;

    public JFormularioPrincipal(String roleName) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menu = new JMenuBar();

        JMenu items = new JMenu("Usuarios");
        JMenu itemOpcion = new JMenu("Opciones");
        menu.add(itemOpcion);

        if (roleName.equals("Administrador")){

            menu.add(items);
        }

        JMenuItem opcAgregarUsuario = new JMenuItem("Alta");
        items.add(opcAgregarUsuario);
        opcAgregarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFrame f = new JFormularioAltaUsuario();
                f.setVisible(true);
            }
        });

        JMenuItem opcDesLoggear = new JMenuItem("Salir");
        itemOpcion.add(opcDesLoggear);
        opcDesLoggear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                hide();
                JFrame f = new JFormularioLogin();
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
                JFrame f = new JFormularioModificarUsuario();
                f.setVisible(true);
            }
        });

        this.setJMenuBar(menu);
        
        JMenu mnEstablecimientos = new JMenu("Establecimientos");
        if (roleName == "Administrador"){

            menu.add(mnEstablecimientos);
        }

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
        		
        		JFrame formularioModificacionEstablecimiento = new JFormularioModificarEstablecimiento();
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
        		
        		JFrame formularioListarSalas = new JFormularioModificarSala();
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
        		
        		JFrame formularioModificacionPelicula = new JFormularioModificacionPelicula();//JFormularioListarPelicula();
        		formularioModificacionPelicula.setVisible(true);
        	}
        });
        mnPeliculas.add(mntmModificacion_2);
        
        JMenu mnFunciones = new JMenu("Funciones");
        menu.add(mnFunciones);
        
        JMenuItem mntmAlta_3 = new JMenuItem("Alta");
        mntmAlta_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFrame formularioAltaFuncion = new JFormularioAltaFuncion();
				formularioAltaFuncion.setVisible(true);
				
			}
		});
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
        
        JMenu mnEntradas = new JMenu("Entradas");
        menu.add(mnEntradas);
        
        JMenuItem mntmComprar = new JMenuItem("Comprar");
        mntmComprar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		JFrame formularioComprarEntradas = new JFormularioComprarEntradas();
        		formularioComprarEntradas.setVisible(true);
        	}
        });
        mnEntradas.add(mntmComprar);
    }
}
