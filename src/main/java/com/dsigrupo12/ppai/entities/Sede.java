package com.dsigrupo12.ppai.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	private int cantMaxPorGuia;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Empleado> empleados;
	
	@OneToMany(fetch = FetchType.EAGER)
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
	public int getCantMaxPorGuia() {
		return cantMaxPorGuia;
	}
	public void setCantMaxPorGuia(int cantMaxPorGuia) {
		this.cantMaxPorGuia = cantMaxPorGuia;
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
	
	public long calcularDuracionEstimada(int[] exposicionesSeleccionadas) {
		long result = 0L;
		
		for (Exposicion exposicion : this.getExposiciones()) {
			for (int i = 0; i < exposicionesSeleccionadas.length; i++) {
				if(exposicionesSeleccionadas[i] == exposicion.getId()) {
					result += exposicion.calcularDuracionEstimada();
				}
			}
			
		}
		
		return result;
	}
	
	public List<Empleado> getGuiasDispEnHorario(LocalDateTime fechaHoraReserva) {
		List<Empleado> result = new ArrayList<>();
		
		for (Empleado empleado : empleados) {
			if(empleado.esGuia() && empleado.estaDisponible(fechaHoraReserva)) {
				result.add(empleado);
			}
		}
		
		return result;
	}
}
