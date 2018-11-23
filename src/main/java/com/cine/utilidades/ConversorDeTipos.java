package com.cine.utilidades;

public class ConversorDeTipos {

	public java.sql.Date deJavaDateToSqlDate(java.util.Date date) {
	    return new java.sql.Date(date.getTime());
	}
}
