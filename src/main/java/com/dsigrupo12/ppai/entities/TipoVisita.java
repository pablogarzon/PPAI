package com.dsigrupo12.ppai.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoVisita {
	
	@Id
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
