package com.cine.modelo;

import com.cine.Dao.UserDao;
import com.cine.Persistencia.PoolConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Usuario implements UserDao {


    private String nombreDeUsuario;
    private String email;
    private String password;
    private String nombre;
    private String domicilio;
    private Integer dni;
    private Date fechaDeNacimiento;
    private Rol rol;

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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario buscarUsuario(Integer dni) {
        try {
            ResultSet resultSet = ejecutarSelect("SELECT * FROM Usuario WHERE dni=" + this.dni);
            Usuario usuario = new Usuario();
            if (resultSet != null) {
                usuario.setDni(resultSet.getInt("dni"));
                usuario.setNombre(resultSet.getString("nombre"));
            }

            return usuario;
        } catch (Exception e) {
            System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar usuario con dni " + dni);
        }
    }

    public List<Usuario> listarUsuarios() {
        return null;
    }

    public boolean insertarUsuario(Usuario usuario) {
        try {
            PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO Usuario VALUES (?,?,?,?,?,?,NULL)");
            preparedStatement.setString(1, usuario.getNombreDeUsuario());
            preparedStatement.setString(2, null);
            preparedStatement.setString(3, null);
            preparedStatement.setString(4, usuario.getNombre());
            preparedStatement.setString(5, null);
            preparedStatement.setInt(6, usuario.getDni());
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return false;
    }

    public boolean borrarUsuario() {
        return false;
    }

    public ResultSet ejecutarSelect(String query) {
        try {
            Connection connection = conectarDb();
            return connection.createStatement().executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection conectarDb() {
        PoolConnection pool = PoolConnection.getPoolConnection();
        return pool.getConnection();
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
