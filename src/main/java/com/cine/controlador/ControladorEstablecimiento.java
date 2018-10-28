package com.cine.controlador;

import java.util.ArrayList;
import java.util.List;

import com.cine.dao.Cache;
import com.cine.dao.EstablecimientoPersistente;
import com.cine.modelo.Establecimiento;

public class ControladorEstablecimiento implements Cache {

	private static final ControladorEstablecimiento controladorEstablecimiento = new ControladorEstablecimiento();

	private List<Establecimiento> establecimientos = new ArrayList<>();

	private EstablecimientoPersistente establecimientoPersistente = new EstablecimientoPersistente();

	public static ControladorEstablecimiento getInstance() {
		return controladorEstablecimiento;
	}
	
	public List<Establecimiento> getEstablecimientos() {
		return establecimientos;
	}

	@SuppressWarnings("unchecked")
	public void obtenerEstablecimientos() {
		establecimientos = (List<Establecimiento>) (Object) establecimientoPersistente.listar();
	}
	
	public Establecimiento buscar(Integer cuit) {
		
		Establecimiento establecimiento = (Establecimiento) buscarEnCache(cuit);
		
		if (establecimiento == null) {
			
			establecimiento = (Establecimiento) establecimientoPersistente.buscar(cuit);
			
			if (establecimiento != null) {
				agregarACache(establecimiento);
			}
		}
	
		return establecimiento;
	}
	
	public void alta(Integer cuit, String nombre, String domicilio, Integer capacidad) {

		Establecimiento establecimiento = new Establecimiento(cuit, nombre, domicilio, capacidad);

		if (buscarEnCache(establecimiento.getCuit()) == null) {

			agregarACache(establecimiento);

			if (establecimientoPersistente.buscar(establecimiento.getCuit()) == null) {
				establecimiento.insertar();
			}
		}
	}

	public void baja(Integer cuit) {

		borrarDeCache(cuit);

		Establecimiento establecimiento = (Establecimiento) establecimientoPersistente.buscar(cuit);
		
		if (establecimiento != null) {
			establecimiento.eliminar();
		}
	}
	
	public void modificacion(Integer cuit, String nombre, String domicilio, Integer capacidad) {
		
		Establecimiento establecimientoModificado = new Establecimiento(cuit, nombre, domicilio, capacidad);
		
		actualizarCache(establecimientoModificado);
		establecimientoModificado.actualizar(cuit, nombre, domicilio, capacidad);
	}

	@Override
	public Object buscarEnCache(Object cuit) {

		return establecimientos.stream()
                .filter(establecimiento -> establecimiento.getCuit().equals(cuit))
                .findAny()
                .orElse(null);
	}

	@Override
	public void agregarACache(Object establecimiento) {
		establecimientos.add((Establecimiento) establecimiento);
	}

	@Override
	public void borrarDeCache(Object cuit) {
		this.establecimientos.removeIf(usuario -> usuario.getCuit().equals(cuit));
	}

	@Override
	public void actualizarCache(Object objetoEstablecimientoModificado) {

		Establecimiento establecimientoModificado = (Establecimiento) objetoEstablecimientoModificado;
		
		if (buscar(establecimientoModificado.getCuit()) != null) {
			
			for (Establecimiento establecimiento : establecimientos) {
				
				if (establecimiento.getCuit().equals(establecimientoModificado.getCuit())) {
					
					if (!establecimiento.getNombre().equals(establecimientoModificado.getNombre())) {
						establecimiento.setNombre(establecimientoModificado.getNombre());
					}
					
					if (!establecimiento.getDomicilio().equals(establecimientoModificado.getDomicilio())) {
						establecimiento.setDomicilio(establecimientoModificado.getDomicilio());
					}
					
					if (!establecimiento.getCapacidad().equals(establecimientoModificado.getCapacidad())) {
						establecimiento.setCapacidad(establecimientoModificado.getCapacidad());
					}
				}
			}
		}
	}
}
