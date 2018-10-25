package com.cine.modelo;

import java.sql.Date;
import java.util.List;

public class Usuario {

    private String nombreDeUsuario;
    private String email;
    private String password;
    private String nombre;
    private String domicilio;
    private Integer dni;
    private Date fechaDeNacimiento;
    private List<Rol> rol;

    public Usuario(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, Date fechaDeNacimiento) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Usuario() {
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public List<Rol> getRol() {
        return rol;
    }

    public void setRol(List<Rol> rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreDeUsuario='" + nombreDeUsuario + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", dni=" + dni +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", rol=" + rol +
                '}';
    }
}
