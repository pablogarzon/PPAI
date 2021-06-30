package com.dsigrupo12.ppai.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Escuela {

	@Id
	@Column(name = "escuela_nombre",length = 50)
	private String nombre;
	
	private String domicilio;
	
	private String mail;
	
	private String telefCelular;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefCelular() {
		return telefCelular;
	}

	public void setTelefCelular(String telefCelular) {
		this.telefCelular = telefCelular;
	}
}
