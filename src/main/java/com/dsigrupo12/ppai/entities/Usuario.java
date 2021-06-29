package com.dsigrupo12.ppai.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario {
	
	@Id
	private String nombre;
	
	private long caducidad;
	
	private String contraseña;

	@OneToOne
	private Empleado empleado;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(long caducidad) {
		this.caducidad = caducidad;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
}
