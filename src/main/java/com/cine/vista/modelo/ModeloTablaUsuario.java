package com.cine.vista.modelo;

import com.cine.controlador.ControladorCine;
import com.cine.controlador.ControladorUsuario;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaUsuario extends AbstractTableModel {

    private static final long serialVersionUID = -5065889673449492921L;

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        return ControladorCine.getInstance().getEstablecimientos().size() + 1;
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        if (fila == 0) {

            if (columna == 0) {
                return "DNI";
            }
            if (columna == 1) {
                return "Nombre";
            }
            if (columna == 2) {
                return "Nombre De Usuario";
            }
            if (columna == 3) {
                return "Email";
            }
            if (columna == 4) {
                return "Domicilio";
            }
            if (columna == 5) {
                return "Fecha de Nacimiento";
            }
            if (columna == 6) {
                return "Password";
            }
        } else {

            if (columna == 0) {
                return ControladorUsuario.getInstance().getUsuarios().get(fila - 1).getDni();
            } else if (columna == 1) {
                return ControladorUsuario.getInstance().getUsuarios().get(fila - 1).getNombre();
            } else if (columna == 2) {
                return ControladorUsuario.getInstance().getUsuarios().get(fila - 1).getNombreDeUsuario();
            } else if (columna == 3) {
                return ControladorUsuario.getInstance().getUsuarios().get(fila - 1).getEmail();
            } else if (columna == 4) {
                return ControladorUsuario.getInstance().getUsuarios().get(fila - 1).getDomicilio();
            } else if (columna == 5) {
                return ControladorUsuario.getInstance().getUsuarios().get(fila - 1).getFechaDeNacimiento();
            } else if (columna == 6) {
                return ControladorUsuario.getInstance().getUsuarios().get(fila - 1).getPassword();
            }
        }

        return "N/A";
    }
}
