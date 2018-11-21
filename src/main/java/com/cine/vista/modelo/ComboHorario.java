package com.cine.vista.modelo;

import java.time.LocalTime;

public class ComboHorario {

	private LocalTime horario;
	private Integer id;

	public ComboHorario(LocalTime horario, Integer id) {

		this.id = id;
		this.horario = horario;
	}

	public LocalTime getHorario() {
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
