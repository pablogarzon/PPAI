package com.dsigrupo12.ppai.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cargo {
    
	@Id
    private String nombre;
    
    private String descripcion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
