package com.dsigrupo12.ppai.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Obra {
	
	@Id
	@Column(name = "id_obra")
	public int id;
	
	public int codigoSensor;
	public String nombreObra;
	public Double duracionExtendida;
	
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
	public Double getDuracionExtendida() {
		return duracionExtendida;
	}
	public void setDuracionExtendida(Double duracionExtendida) {
		this.duracionExtendida = duracionExtendida;
	}
}
