package com.dsigrupo12.ppai.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estado {
	
	@Id
	@Column(name = "estado_nom", length = 50)
    private String nombre;
	
    private String descripcion;
    
    private String ambito;

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
	
	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public boolean esPendienteDeConfirmacion(){
        return this.nombre.equals("PENDIENTE");
    }
	
	public boolean esAmbitoReserva() {
		return this.ambito.equals("RESERVA");
	}
}
