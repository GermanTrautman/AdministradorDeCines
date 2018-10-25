package com.cine.dao;

public interface Cache {
	
    Object buscarEnCache(Object key);

    void agregarACache(Object entidad);
    void borrarDeCache(Object key);
    void actualizarCache(Object entidad);
}
