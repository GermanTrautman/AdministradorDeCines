package com.cine.vista.modelo;

import java.util.Date;

import com.cine.utilidades.Calendario;
import com.cine.utilidades.Calendario.Formato;

public class ComboFecha {

	private Date fecha;
	private Integer id;

	public ComboFecha(Date fecha, Integer id) {

		this.id = id;
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		
		Calendario calendario = new Calendario();
		
		return calendario.formatear(Formato.DIA_MES_ANIO, this.fecha);
	}
}
