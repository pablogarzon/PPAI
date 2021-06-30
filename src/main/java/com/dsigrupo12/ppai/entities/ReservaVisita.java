package com.dsigrupo12.ppai.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ReservaVisita {
	
	@Id
	@Column(name = "reserva_nro")
	private int numeroReserva;

	private int cantidadAlumnos;
	
	private int cantidadAlumnosConfirmada;
	
	private double duracionEstimada;
	
	private LocalDateTime fechaHoraCreacion;
	
	private LocalDateTime fechaHoraReserva;
	
	private LocalTime horaFinReal;
	
	private LocalTime horaInicioReal;

	@OneToOne
	@JoinColumn(name = "escuela_nombre")
	private Escuela escuela;

	@OneToMany
	@JoinColumn(name = "asignacion_visita_id")
	private List<AsignacionVisita> asignacionVisita;

	@OneToMany
	@JoinColumn(name = "cambio_estado_Id")
	private List<CambioEstado> cambioEstado;
	
	@ManyToOne
	@JoinColumn(name = "sede_nom")
	private Sede sede;
	
	@OneToMany
	@JoinColumn(name = "exposicion_id")
	private List<Exposicion> exposiciones;

	public int getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(int numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public int getCantidadAlumnos() {
		return cantidadAlumnos;
	}

	public void setCantidadAlumnos(int cantidadAlumnos) {
		this.cantidadAlumnos = cantidadAlumnos;
	}

	public int getCantidadAlumnosConfirmada() {
		return cantidadAlumnosConfirmada;
	}

	public void setCantidadAlumnosConfirmada(int cantidadAlumnosConfirmada) {
		this.cantidadAlumnosConfirmada = cantidadAlumnosConfirmada;
	}

	public double getDuracionEstimada() {
		return duracionEstimada;
	}

	public void setDuracionEstimada(double duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}

	public LocalDateTime getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}

	public LocalDateTime getFechaHoraReserva() {
		return fechaHoraReserva;
	}

	public void setFechaHoraReserva(LocalDateTime fechaHoraReserva) {
		this.fechaHoraReserva = fechaHoraReserva;
	}

	public LocalTime getHoraFinReal() {
		return horaFinReal;
	}

	public void setHoraFinReal(LocalTime horaFinReal) {
		this.horaFinReal = horaFinReal;
	}

	public LocalTime getHoraInicioReal() {
		return horaInicioReal;
	}

	public void setHoraInicioReal(LocalTime horaInicioReal) {
		this.horaInicioReal = horaInicioReal;
	}

	public Escuela getEscuela() {
		return escuela;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}
	
	public List<AsignacionVisita> getAsignacionVisita() {
		return asignacionVisita;
	}

	public void setAsignacionVisita(List<AsignacionVisita> asignacionVisita) {
		this.asignacionVisita = asignacionVisita;
	}

	public List<CambioEstado> getCambioEstado() {
		return cambioEstado;
	}

	public void setCambioEstado(List<CambioEstado> cambioEstado) {
		this.cambioEstado = cambioEstado;
	}

	public List<Exposicion> getExposiciones() {
		return exposiciones;
	}

	public void setExposiciones(List<Exposicion> exposiciones) {
		this.exposiciones = exposiciones;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public  boolean esEnFecha() {
		return false;
    }
}
