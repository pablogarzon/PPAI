package com.dsigrupo12.ppai.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cargo {
    
	@Id
	@Column(name = "cargo_nom", length = 50)
    private String nombre;
    
    private String descripcion;
    
    public Cargo() {
		// TODO Auto-generated constructor stub
	}
    
	public Cargo(String nombre) {
		this.nombre = nombre;
	}



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
