package com.dsigrupo12.ppai.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class PublicoDestino {
	
	@Id
	private int id;
	
	private String nombre;
	
	private String caracteristicas;
	
	@ManyToMany
	private List<Exposicion> exposicion;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public List<Exposicion> getExposicion() {
		return exposicion;
	}

	public void setExposicion(List<Exposicion> exposicion) {
		this.exposicion = exposicion;
	}
}
