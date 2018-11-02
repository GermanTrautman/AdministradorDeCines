package com.cine.modelo;

import com.cine.dao.RolUsuarioPersistente;

public class RolUsuario {

    private Integer idUsuario;
    private Integer idRol;

    public RolUsuario(Integer idUsuario, Integer idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public RolUsuario() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }


    public void insertarRolUsuario() {
        RolUsuarioPersistente.getInstance().insertar(this);
    }

    public Integer buscarRolPorNombre(String nombre) {
        return RolUsuarioPersistente.getInstance().buscarPorNombreRol(nombre);
    }

    public void borrarRolUsuario() {
        RolUsuarioPersistente.getInstance().borrar(this.getIdUsuario());
    }

    public void actualizarRolUsuario(RolUsuario rolUsuario) {
        RolUsuarioPersistente.getInstance().actualizar(rolUsuario);
    }

    public RolUsuario buscarRolUsuario(Integer dni) {
        return RolUsuarioPersistente.getInstance().buscar(dni);
    }

    public Rol buscarRolPorId(Integer idRol) {
        return RolUsuarioPersistente.getInstance().buscarPorIdRol(idRol);
    }
}
