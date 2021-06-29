package com.dsigrupo12.ppai.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EstadoReserva {
	
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
	
	public boolean esPendienteDeConfirmacion(){
        if (this.nombre.equals("Pendiente")) {
            return true;
        } else {
            return false;
        }
    }
}
