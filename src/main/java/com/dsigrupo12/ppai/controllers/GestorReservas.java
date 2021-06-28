package com.dsigrupo12.ppai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsigrupo12.ppai.entities.EstadoReserva;
import com.dsigrupo12.ppai.repositories.EscuelaRepository;
import com.dsigrupo12.ppai.repositories.SedeRepository;
import com.dsigrupo12.ppai.repositories.TipoVisitaRepository;


@Controller
@RequestMapping("/")
public class GestorReservas {
	
	private EscuelaRepository escuelas;
	private SedeRepository sedes;
	private TipoVisitaRepository tipoVisitas;
	
	@Autowired
	public GestorReservas(EscuelaRepository escuelas, SedeRepository sedes, TipoVisitaRepository tipoVisitas) {
		this.escuelas = escuelas;
		this.sedes = sedes;
		this.tipoVisitas = tipoVisitas;
	}

	@GetMapping
	public String opcionRegReservaGuiada() {
		return "PantallaAdministrarReservas";
	}

	public List<String> buscarEscuelas() {
		return null;
	}
	
	public void tomarIngresoCantVisitantes() {
		
	}
	
	public List<String> buscarSede() {
		return null;
	}
	
	public List<String> buscarTiposVisita() {
		return null;
	}
	
	public List<String> buscarExposicionesTemporalesVigentes() {
		return null;
	}
	
	public Double calcularDuracionEstimada() {
		return null;
	}
	
	public void VerificarCapacidadMaximaSede() {
		
	}
	
	public int CalcularCantidadGuiasNecesarios() {
		return 0;
	}
	
	public String buscarEmpleadoLogueado() {
		return null;
	}
	
	public int buscarUltimoNroReserva() {
		return 0;
	}
	
	public EstadoReserva buscarEstadoReserva() {
		return null;
	}
	
	public Long obtenerFechaHoraSistema() {
		return null;
	}
	
	public void registrarReserva() {
		
	}
	
}
