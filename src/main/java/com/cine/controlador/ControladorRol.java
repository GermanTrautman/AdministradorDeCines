package com.cine.controlador;

import com.cine.dao.Cache;
import com.cine.dao.RolPersistente;
import com.cine.dao.UsuarioPersistente;
import com.cine.modelo.Rol;
import com.cine.modelo.Usuario;
import com.cine.utilidades.Estado;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ControladorRol implements Cache {

    private static final ControladorRol controladorRol = new ControladorRol();

    private List<Rol> rolList = new ArrayList<>();

    private RolPersistente rolPersistente = new RolPersistente();

    public static ControladorRol getInstance() {
        return controladorRol;
    }


    public void altaRol(String nombre, Estado estado) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        if (buscarEnCache(nombre) == null){

            Rol rol = (Rol) Class.forName(nombre).newInstance();
            rolList.add(rol);
            rolPersistente.insertar(rol);
        }else {
            throw new RuntimeException("No se permiten usuarios duplicados");
        }
    }


    public void bajaUsuario(Integer dni) {

        borrarDeCache(dni);

        if (rolPersistente.buscar(dni) != null) {
            rolPersistente.borrar(dni);
        }
    }

    public void modificacionUsuario(Integer dni, String nombreDeUsuario, String email, String password, String nombre, String domicilio, Date fechaDeNacimiento) {

        Usuario usuario = new Usuario(dni,nombreDeUsuario,email,password,nombre,domicilio,fechaDeNacimiento);

        actualizarCache(usuario);
        rolPersistente.actualizar(usuario);
    }


    @Override
    public Object buscarEnCache(Object key) {
        return rolList.stream()
                .filter(rol -> rol.getNombre().equals(key))
                .findAny()
                .orElse(null);
    }

    @Override
    public void agregarACache(Object entidad) {
        this.rolList.add((Rol) entidad);
    }

    @Override
    public void borrarDeCache(Object key) {
        this.rolList.removeIf(rol -> rol.getNombre().equals(key));

    }

    @Override
    public void actualizarCache(Object entidad) {
        Rol rol = (Rol) entidad;
        borrarDeCache(rol.getNombre());
        this.rolList.add(rol);
    }

    public void obtenerRolesDb() {
        rolList = (List<Rol>) (Object) rolPersistente.listar();
    }

    public List<Rol> getRoles(){
        return rolList;
    }
}
