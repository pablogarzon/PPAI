package com.dsigrupo12.ppai.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Empleado {

	@Id
	private int dni;
	
	private String codValidacion;
	
	private String apellido;
	
	private String nombre;
	
	private String domicilio;
	
	private String mail;
	
	private String sexo;
	
	private String telefono;
	
	private String cuit;
	
	private LocalDateTime fechaIngreso;
	
	private LocalDateTime fechaNacimiento;
	
	@OneToOne
	private Cargo cargo;
	
	@ManyToOne
	@JoinColumn(name = "nom_sede")
	private Sede sede;
	
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getCodValidacion() {
		return codValidacion;
	}

	public void setCodValidacion(String codValidacion) {
		this.codValidacion = codValidacion;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public boolean esGuia() {
		if (cargo.getNombre().equals("Guia")) {
			return true;
		}
		return false;
	}

	public boolean estaDisponible(LocalDateTime fechaHoraReserva) {
		// TODO Auto-generated method stub
		return false;
	}
}
