package com.dsigrupo12.ppai.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dsigrupo12.ppai.entities.Empleado;
import com.dsigrupo12.ppai.entities.Escuela;
import com.dsigrupo12.ppai.entities.EstadoReserva;
import com.dsigrupo12.ppai.entities.Exposicion;
import com.dsigrupo12.ppai.entities.Sede;
import com.dsigrupo12.ppai.entities.TipoVisita;
import com.dsigrupo12.ppai.repositories.EscuelaRepository;
import com.dsigrupo12.ppai.repositories.SedeRepository;


@Controller
@RequestMapping("/")
public class GestorReservas {
	
	private EscuelaRepository escuelas;
	private SedeRepository sedes;
	private TipoVisita[] tipoVisitas;
	
	@Autowired
	public GestorReservas(EscuelaRepository escuelas, SedeRepository sedes) {
		this.escuelas = escuelas;
		this.sedes = sedes;
		this.tipoVisitas = TipoVisita.values();
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
		for (TipoVisita tv : tipoVisitas) {
			result.add(tv.getNombre());
		}
		result.add("test");
		return result;
	}
	
	public LocalDateTime obtenerFechaHoraSistema() {
		return LocalDateTime.now();
	}
	
	public List<Exposicion> buscarExposicionesTemporalesVigentes(Sede seleccionada) {
		LocalDateTime fechaHoraActual = obtenerFechaHoraSistema();
		List<Exposicion> etv = seleccionada.getExposicionesTemporalesVigentes(fechaHoraActual);
		return etv;
	}
	
	public Double calcularDuracionEstimada(Sede seleccionada) {
		return seleccionada.calcularDuracionEstimada(); 
	}
	
	public boolean VerificarCapacidadMaximaSede(Sede seleccionada, int cantIngresada, LocalDate fechaHoraReserva) {
		int cantMax = seleccionada.getCantMaximaVisitantes();
		if(cantMax <= cantIngresada) {
			int sumaAsistentesOtrasExpoParaLaFecha = seleccionada.sumarCantidadVisitantes(fechaHoraReserva);
			return sumaAsistentesOtrasExpoParaLaFecha <= cantIngresada;
		}
		return false;
	}
	
	public int CalcularCantidadGuiasNecesarios(Sede seleccionada, LocalDate fechaHoraReserva) {
		List<Empleado> guias = seleccionada.getGuiasDispEnHorario(fechaHoraReserva);
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
	
	public void registrarReserva() {
		
	}
	
}
