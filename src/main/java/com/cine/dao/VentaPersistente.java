package com.cine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cine.controlador.ControladorFuncion;
import com.cine.controlador.ControladorUsuario;
import com.cine.modelo.AsientoVirtual;
import com.cine.modelo.Funcion;
import com.cine.modelo.Usuario;
import com.cine.modelo.Venta;
import com.cine.utilidades.FormaDePago;
import com.cine.utilidades.TipoVenta;

public class VentaPersistente implements Persistencia {
	
	public static VentaPersistente instancia;
	
    public VentaPersistente() {
    }

    public static VentaPersistente getInstance() {
    	
        if (instancia == null) {
        
        	instancia = new VentaPersistente();
        }
        
        return instancia;
    }
	
	@Override
	public Object buscar(Object stringCodigo) {
		
		String codigo = (String) stringCodigo;

		try {
			
            Venta venta = null;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("SELECT * FROM TPO.dbo.Venta WHERE Codigo = ?");
			preparedStatement.setString(1, codigo);
			ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	
            	Usuario usuario = ControladorUsuario.getInstance().buscarUsuarioEnDb(resultSet.getInt("DNIUsuario"));
            	Funcion funcion = ControladorFuncion.getInstance().buscarPor(resultSet.getInt("IdFuncion"));
            	List<AsientoVirtual> asientosAdquiridos = new ArrayList<>();
            	FormaDePago formaDePago = FormaDePago.valueOf(resultSet.getString("FormaDePago").toUpperCase());
            	TipoVenta tipoDeVenta = TipoVenta.valueOf(resultSet.getString("Tipo").toUpperCase());
            	
            	venta = new Venta(usuario, funcion, asientosAdquiridos, resultSet.getDouble("Monto"), formaDePago, tipoDeVenta);
            	venta.setId(resultSet.getInt("Id"));
            	venta.setCodigo(resultSet.getString("Codigo"));
            }
            
            return venta;
            
        } catch (SQLException e) {
            
        	System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar venta con codigo " + codigo);
            
        } finally {
        	
			liberarConexion();
		}
	}
	
	public Object buscarPor(Integer dni, String codigo) {
		
		try {
			
            Venta venta = null;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("SELECT * FROM TPO.dbo.Venta WHERE DNIUsuario = ? AND Codigo = ?");
			preparedStatement.setInt(1, dni);
			preparedStatement.setString(2, codigo);
			ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	
            	Usuario usuario = ControladorUsuario.getInstance().buscarUsuarioEnDb(resultSet.getInt("DNIUsuario"));
            	Funcion funcion = ControladorFuncion.getInstance().buscarPor(resultSet.getInt("IdFuncion"));
            	List<AsientoVirtual> asientosAdquiridos = new ArrayList<>();
            	FormaDePago formaDePago = FormaDePago.valueOf(resultSet.getString("FormaDePago").toUpperCase());
            	TipoVenta tipoDeVenta = TipoVenta.valueOf(resultSet.getString("Tipo").toUpperCase());
            	
            	venta = new Venta(usuario, funcion, asientosAdquiridos, resultSet.getDouble("Monto"), formaDePago, tipoDeVenta);
            	venta.setId(resultSet.getInt("Id"));
            	venta.setCodigo(resultSet.getString("Codigo"));
            }
            
            return venta;
            
        } catch (SQLException e) {
            
        	System.out.println("Error Query: " + e.getMessage());
            throw new RuntimeException("Error intentando buscar venta con codigo " + codigo);
            
        } finally {
        	
			liberarConexion();
		}
	}

	@Override
	public List<Object> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertar(Object objetoVenta) {

		try {
			
			Venta venta = (Venta) objetoVenta;
			
			PreparedStatement preparedStatement = conectarDb().prepareStatement("INSERT INTO TPO.dbo.Venta (Codigo, DNIUsuario, IdFuncion, Monto, FormaDePago, Tipo) VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, venta.getCodigo());
			preparedStatement.setInt(2, venta.getUsuario().getDni());
			preparedStatement.setInt(3, venta.getFuncion().getId());
			preparedStatement.setDouble(4, venta.getMonto());
			preparedStatement.setString(5, venta.getFormaDePago().getLabel());
			preparedStatement.setString(6, venta.getTipoVenta().getLabel());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} finally {
			
			liberarConexion();
		}
	}
	
	@Override
	public void actualizar(Object entidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrar(Object key) {
		// TODO Auto-generated method stub
	
	}
}
