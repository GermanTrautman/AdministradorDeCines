package com.cine.modelo;

import java.security.SecureRandom;
import java.util.List;

import com.cine.dao.VentaPersistente;
import com.cine.utilidades.FormaDePago;
import com.cine.utilidades.TipoVenta;

public class Venta {
	
	private static SecureRandom rnd = new SecureRandom();
	private static final String ABC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	private Integer id;
	private String codigo;
	private Usuario usuario;
	private Funcion funcion;
	private List<AsientoVirtual> asientosAdquiridos;
	private Double monto;
	private FormaDePago formaDePago;
	private Tarjeta tarjeta;
	private TipoVenta tipoVenta;

	public Venta(Usuario usuario, Funcion funcion, List<AsientoVirtual> asientosAdquiridos, Double monto, FormaDePago formaDePago, TipoVenta tipoVenta) {
		
		super();
		this.usuario = usuario;
		this.funcion = funcion;
		this.asientosAdquiridos = asientosAdquiridos;
		this.monto = monto;
		this.formaDePago = formaDePago;
		this.tipoVenta = tipoVenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public List<AsientoVirtual> getAsientosAdquiridos() {
		return asientosAdquiridos;
	}

	public void setAsientosAdquiridos(List<AsientoVirtual> asientosAdquiridos) {
		this.asientosAdquiridos = asientosAdquiridos;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public TipoVenta getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(TipoVenta tipoVenta) {
		this.tipoVenta = tipoVenta;
	}
	
	public void insertar() {
		VentaPersistente.getInstance().insertar(this);
	}
	
	public String stringAleatorio(int largo) {
		
	   StringBuilder stringBuilder = new StringBuilder(largo);
	   
	   for( int i = 0; i < largo; i++ ) {
		   
	      stringBuilder.append(ABC.charAt(rnd.nextInt(ABC.length())));
	   }
	   
	   return stringBuilder.toString();
	}
}
