package com.cine.modelo;

import com.cine.dao.UsuarioPersistente;

import java.util.List;

public class Usuario {

    private String nombreDeUsuario;
    private String email;
    private String password;
    private String nombre;
    private String domicilio;
    private Integer dni;
    private String fechaDeNacimiento;
    private List<Rol> rol;

    public Usuario(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, String fechaDeNacimiento) {
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

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
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


    public void insertarUsuario() {
        UsuarioPersistente.getInstance().insertar(this);
    }

    public void borrarUsuario() {
        UsuarioPersistente.getInstance().borrar(this.getDni());
    }

    public void actualizarUsuario(Usuario usuario) {
        UsuarioPersistente.getInstance().actualizar(usuario);
    }

    public Usuario buscarUsuario(Integer dni) {
       return (Usuario) UsuarioPersistente.getInstance().buscar(dni);
    }

    public Usuario buscarUsuarioPorUsrAndPass(String nombreDeUsuario,String password){
        return UsuarioPersistente.getInstance().buscarUsuarioPorUsrAndPass(nombreDeUsuario,password);
    }

}
