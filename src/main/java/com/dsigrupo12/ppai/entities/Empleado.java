package com.dsigrupo12.ppai.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Empleado {

	@Id
	private int dni;
	
	@ManyToOne
	@JoinColumn(name = "nom_sede")
	private Sede sede;

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public boolean esGuia() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean estaDisponible(LocalDate fechaHoraReserva) {
		// TODO Auto-generated method stub
		return false;
	}
}
