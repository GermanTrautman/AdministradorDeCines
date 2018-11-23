package com.cine.vista.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ComboFecha {

	private LocalDate fecha;
	private Integer id;

	public ComboFecha(LocalDate fecha, Integer id) {

		this.id = id;
		this.fecha = fecha;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return fecha.format(formatter);
//		Calendario calendario = new Calendario();
//		
//		return calendario.formatear(Formato.DIA_MES_ANIO, this.fecha);
	}
}
