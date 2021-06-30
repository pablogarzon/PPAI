package com.dsigrupo12.ppai.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sede {
	
	@Id
	@Column(name = "sede_nom", length = 50)
	private String nombre;
	
	private int cantMaximaVisitantes;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Empleado> empleados;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
	
	public List<Exposicion> getExposicionesTemporalesVigentes(LocalDate fecha) {
		List<Exposicion> exposicionesVigentes = new ArrayList<>();
		for (Exposicion exposicion: this.exposiciones) {
			if (exposicion.getFechaInicio().isBefore(fecha) && exposicion.getFechaFin().isAfter(fecha)) {
				if(exposicion.getTipoExposicion() == TipoExposicion.TEMPORAL) {
					exposicionesVigentes.add(exposicion);
				}
			}
		}
		return exposicionesVigentes;
	}
	
	public Double calcularDuracionEstimada() {
		Double result = 0.0;
		
		for (Exposicion exposicion : this.exposiciones) {
			result += exposicion.calcularDuracionEstimada();
		}
		
		return result;
	}
	public int sumarCantidadVisitantes(LocalDateTime fechaHoraReserva) {
		// TODO Auto-generated method stub
		buscarExposicionesFecha(fechaHoraReserva);
		
		return 0;
	}
	
	private List<Exposicion> buscarExposicionesFecha(LocalDateTime fechaHoraReserva) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Empleado> getGuiasDispEnHorario(LocalDateTime fechaHoraReserva) {
		// TODO Auto-generated method stub
		for (Empleado empleado : empleados) {
			if(empleado.esGuia()) {
				if (empleado.estaDisponible(fechaHoraReserva)) {
					
				}
			}
		}
		return null;
	}
}
