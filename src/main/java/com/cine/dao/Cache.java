package com.cine.dao;

public interface Cache {
	
    Object buscarEnCache(Integer key);

    void agregarACache(Object entidad);
    void borrarDeCache(Integer key);
    void actualizarCache(Object entidad);
}
