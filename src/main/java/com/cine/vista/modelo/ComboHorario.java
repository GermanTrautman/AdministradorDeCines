package com.cine.vista.modelo;

import java.sql.Time;

public class ComboHorario {

	private Time horario;
	private Integer id;

	public ComboHorario(Time horario, Integer id) {

		this.id = id;
		this.horario = horario;
	}

	public Time getHorario() {
		return horario;
	}

	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.horario.toString();
	}
}
