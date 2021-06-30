package com.dsigrupo12.ppai.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@ManyToOne
	@JoinColumn(name = "cargo_nom")
	private Cargo cargo;
	
	@OneToMany(mappedBy = "empleado")
	private List<AsignacionVisita> asignacionesVisitas;	
	
	public Empleado() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Empleado(int dni, String apellido, String nombre, Cargo cargo) {
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.cargo = cargo;
	}



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

	public List<AsignacionVisita> getAsignacionesVisitas() {
		return asignacionesVisitas;
	}

	public void setAsignacionesVisitas(List<AsignacionVisita> asignacionesVisitas) {
		this.asignacionesVisitas = asignacionesVisitas;
	}

	public boolean esGuia() {
		if (cargo.getNombre().equals("Guia")) {
			return true;
		}
		return false;
	}

	public boolean estaDisponible(LocalDateTime fechaHoraReserva, long duracionEstimada) {
		Boolean estaDisponible = true;
		
		for (AsignacionVisita av : asignacionesVisitas) {
			if (av.getFechaHoraInicio().isBefore(fechaHoraReserva) && av.getFechaHoraFin().isAfter(fechaHoraReserva.plusMinutes(duracionEstimada))) {
				estaDisponible = false;
				break;
			}
		}
		return estaDisponible;
	}
}
