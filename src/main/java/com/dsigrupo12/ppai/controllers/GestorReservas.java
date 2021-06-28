package com.dsigrupo12.ppai.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsigrupo12.ppai.entities.Escuela;
import com.dsigrupo12.ppai.entities.EstadoReserva;
import com.dsigrupo12.ppai.entities.Sede;
import com.dsigrupo12.ppai.entities.TipoVisita;
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
	public ModelAndView opcionRegReservaGuiada(ModelAndView mv) {
		mv.addObject("escuelas", buscarEscuelas());
		mv.setViewName("PantallaAdministrarReservas");
		return mv;
	}

	public List<String> buscarEscuelas() {
		List<String> result = new ArrayList<>();
		for (Escuela escuela : escuelas.findAll()) {
			result.add(escuela.getNombre());
		}
		result.add("test");
		return result;
	}
	
	public void tomarIngresoCantVisitantes() {
		
	}
	
	public List<String> buscarSede() {
		List<String> result = new ArrayList<>();
		for (Sede sede : sedes.findAll()) {
			result.add(sede.getNombre());
		}
		result.add("test");
		return result;
	}
	
	public List<String> buscarTiposVisita() {
		List<String> result = new ArrayList<>();
		for (TipoVisita tv : tipoVisitas.findAll()) {
			result.add(tv.getNombre());
		}
		result.add("test");
		return result;
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
