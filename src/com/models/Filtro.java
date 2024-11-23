package com.models;

import java.util.Objects;

/**
 * Clase que representa un filtro
 * 
 * Proporciona el constructor específico para los filtros
 * 
 * 
 * 
 * @author [Carlos Arroyo Caballero]
 * @version 1.0
 * 
 * @see com.controllers.Filter
 */
public class Filtro {

	private String name;
	private String type;
	
	/**
	 * Constructor
	 * 
	 * @param name El nombre del filtro
	 * @param type El tipo de filtro
	 */
	public Filtro(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Devuelve el nombre del filtro
	 * 
	 * @return El nombre del filtro
	 */
	public String getName() {
		return name;
	}

	/**
	 * Devuelve el tipo del filtro
	 * 
	 * @return El tipo del filtro
	 */
	public String getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Filtro filtro = (Filtro) o;
	    return Objects.equals(type, filtro.type) && Objects.equals(name, filtro.name);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(type, name);
	}
	
	
}
