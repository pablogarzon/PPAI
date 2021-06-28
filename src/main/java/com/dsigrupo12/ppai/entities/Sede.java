package com.dsigrupo12.ppai.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sede {
	
	@Id
	@Column(name = "nom_sede")
	private String nombre;
	
	private int cantMaximaVisitantes;
	
	@OneToMany(mappedBy = "sede", fetch = FetchType.LAZY)
	private List<Empleado> empleados;
	
	@OneToMany(mappedBy = "sede", fetch = FetchType.EAGER)
	private List<Exposicion> exposiciones;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantMaximaVisitantes() {
		return cantMaximaVisitantes;
	}
	public void setCantMaximaVisitantes(int cantMaximaVisitantes) {
		this.cantMaximaVisitantes = cantMaximaVisitantes;
	}
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	public List<Exposicion> getExposiciones() {
		return exposiciones;
	}
	public void setExposiciones(List<Exposicion> exposiciones) {
		this.exposiciones = exposiciones;
	}
	
	public List<Exposicion> getExposicionesTemporalesVigentes(LocalDate fechaInicio, LocalDate fechaFin) {
		List<Exposicion> exposicionesVigentes = new ArrayList<>();
		for (Exposicion exposicion: this.exposiciones) {
			if (exposicion.getFechaInicio().isAfter(fechaInicio) && exposicion.getFechaFin().isBefore(fechaFin)) {
				exposicionesVigentes.add(exposicion);
			}
		}
		return exposicionesVigentes;
	}
}
