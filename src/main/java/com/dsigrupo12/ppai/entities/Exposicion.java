package com.dsigrupo12.ppai.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Exposicion {

	@Id
	private int id;
	
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private LocalTime horaApertura;
	private LocalTime horaCierre;

	@ManyToMany
	@JoinTable(name = "exposicion_pd", joinColumns = @JoinColumn(name = "exposicion_id"), inverseJoinColumns = @JoinColumn(name = "pd_id"))
	private List<PublicoDestino> publicoDestino;

	@ManyToOne
	@JoinColumn(name = "nom_sede")
	private Sede sede;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalTime getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}

	public LocalTime getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}

	public List<PublicoDestino> getPublicoDestino() {
		return publicoDestino;
	}

	public void setPublicoDestino(List<PublicoDestino> publicoDestino) {
		this.publicoDestino = publicoDestino;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
}
