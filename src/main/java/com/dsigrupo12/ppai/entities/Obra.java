package com.dsigrupo12.ppai.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Obra {
	
	@Id
	@Column(name = "id_obra")
	private int id;
	
	private int codigoSensor;
	
	private String nombreObra;
	
	private String descripcion;
	
	private Double duracionExtendida;
	
	private Double duracionResumida;
	
	private LocalDate fechaCreacion;
	
	private LocalDate fechaPrimerIngreso;
	
	private Double peso;
	
	private Double valuacion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigoSensor() {
		return codigoSensor;
	}
	public void setCodigoSensor(int codigoSensor) {
		this.codigoSensor = codigoSensor;
	}
	public String getNombreObra() {
		return nombreObra;
	}
	public void setNombreObra(String nombreObra) {
		this.nombreObra = nombreObra;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getDuracionExtendida() {
		return duracionExtendida;
	}
	public void setDuracionExtendida(Double duracionExtendida) {
		this.duracionExtendida = duracionExtendida;
	}
	public Double getDuracionResumida() {
		return duracionResumida;
	}
	public void setDuracionResumida(Double duracionResumida) {
		this.duracionResumida = duracionResumida;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDate getFechaPrimerIngreso() {
		return fechaPrimerIngreso;
	}
	public void setFechaPrimerIngreso(LocalDate fechaPrimerIngreso) {
		this.fechaPrimerIngreso = fechaPrimerIngreso;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getValuacion() {
		return valuacion;
	}
	public void setValuacion(Double valuacion) {
		this.valuacion = valuacion;
	}
}
