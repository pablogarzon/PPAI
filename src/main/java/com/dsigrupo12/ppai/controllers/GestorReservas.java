package com.dsigrupo12.ppai.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsigrupo12.ppai.entities.Empleado;
import com.dsigrupo12.ppai.entities.Escuela;
import com.dsigrupo12.ppai.entities.Estado;
import com.dsigrupo12.ppai.entities.Exposicion;
import com.dsigrupo12.ppai.entities.PublicoDestino;
import com.dsigrupo12.ppai.entities.Sede;
import com.dsigrupo12.ppai.entities.TipoExposicion;
import com.dsigrupo12.ppai.entities.TipoVisita;
import com.dsigrupo12.ppai.repositories.EscuelaRepository;
import com.dsigrupo12.ppai.repositories.SedeRepository;


@Controller
@RequestMapping("/")
public class GestorReservas {
	
	private EscuelaRepository escuelas;
	private SedeRepository sedes;
	
	@Autowired
	public GestorReservas(EscuelaRepository escuelas, SedeRepository sedes) {
		this.escuelas = escuelas;
		this.sedes = sedes;
	}

	@GetMapping
	public ModelAndView opcionRegReservaGuiada(ModelAndView mv) {
		mv.addObject("escuelas", buscarEscuelas());
		mv.addObject("sedes", buscarSedes());
		mv.addObject("tiposVisita", buscarTiposVisita());
		mv.setViewName("PantallaAdministrarReservas");
		return mv;
	}

	private List<String> buscarEscuelas() {
		List<String> result = new ArrayList<>();
		for (Escuela escuela : escuelas.findAll()) {
			result.add(escuela.getNombre());
		}
		result.add("test");
		return result;
	}
	
	private List<String> buscarSedes() {
		List<String> result = new ArrayList<>();
		for (Sede sede : sedes.findAll()) {
			result.add(sede.getNombre());
		}
		result.add("test");
		return result;
	}
	
	private List<String> buscarTiposVisita() {
		List<String> result = new ArrayList<>();
		for (TipoVisita tv : TipoVisita.values()) {
			result.add(tv.getNombre());
		}
		return result;
	}
	
	@GetMapping(path = "/exposiciones")
	public @ResponseBody Map<?, ?> tomarSeleccionTipoVisita(
			@RequestParam("sede") String nombreSede, 
			@RequestParam("tv") String nombreTV) {
		
		Map<String, List> exposiciones = new HashMap();
		
		Sede seleccionada = new Sede();// sedes.findById(nombreSede).get();		
		TipoVisita tipoVisita = TipoVisita.getByName(nombreTV);
		
		PublicoDestino publicoDestino = new PublicoDestino();
		publicoDestino.setNombre("ATP");
		Exposicion exposicion = new Exposicion();
		exposicion.setNombre("arte austriaco");
		exposicion.setId(1);
		exposicion.setHoraApertura(LocalTime.now());
		exposicion.setHoraCierre(LocalTime.now());
		exposicion.setFechaInicio(LocalDate.now().minusDays(10));
		exposicion.setFechaFin(LocalDate.now().plusDays(10));
		exposicion.setPublicoDestino(publicoDestino);
		exposicion.setTipoExposicion(TipoExposicion.TEMPORAL);
		seleccionada.setExposiciones(Collections.singletonList(exposicion));
		
		if (tipoVisita == TipoVisita.POR_EXPOSICION) {
			exposiciones.put("exposiciones", buscarExposicionesTemporalesVigentes(seleccionada));
		}
		
		return exposiciones;
	}
	
	private List<Map<String, String>> buscarExposicionesTemporalesVigentes(Sede seleccionada) {
		List<Map<String, String>> result = new ArrayList<>();
		
		LocalDate fechaActual = obtenerFechaHoraSistema().toLocalDate();
		
		for (Exposicion exposicion : seleccionada.getExposicionesTemporalesVigentes(fechaActual)) {
			Map<String, String> expo = new HashMap<>();

			expo.put("id", String.valueOf(exposicion.getId()));
			expo.put("nombre", exposicion.getNombre());
			expo.put("publicoDestino", exposicion.getPublicoDestino().getNombre());
			expo.put("horaApertura", exposicion.getHoraApertura().toString());
			expo.put("horaCierre", exposicion.getHoraCierre().toString());
			
			result.add(expo);
		}
		
		return result;
	}
	
	private LocalDateTime obtenerFechaHoraSistema() {
		return LocalDateTime.now();
	}
	
	@GetMapping(path = "/guias")
	public @ResponseBody Map<?, ?> tomarSelecionFechaHoraReserva(
			@RequestParam("sede") String nombreSede, 
			@RequestParam("cantV") int cantAsistentesIngresada, 
			@RequestParam("fecha") String fechaIngresada, 
			@RequestParam("hora") String horaIngresada) {
		
		Map<String, Object> result = new HashMap<>();
		
		LocalDateTime fechaHoraReserva = LocalDateTime.parse(fechaIngresada + "T" + horaIngresada + ":00");
		
		/*Double duracionEstimada = calcularDuracionEstimada(seleccionada);
		
		verificarCapacidadMaximaSede(seleccionada, cantAsistentesIngresada, fechaHoraReserva);	
		
		int cantGuias = calcularCantidadGuiasNecesarios(seleccionada, cantAsistentesIngresada);		
		
		List<Empleado> guias = obtenerGuias(seleccionada, fechaHoraReserva, duracionEstimada);
		*/
		
		int cantGuias = 2;
		
		List<String> guias = new ArrayList<>();
		guias.add("Juan Arango");
		guias.add("Camila Sierra");
		guias.add("Jorge Perez");
		guias.add("Caren Margaretti");
		guias.add("Antonella Gold");
		guias.add("Claudio Capputo");
		
		result.put("cantidadNecesaria", cantGuias);
		result.put("guias", guias);
		
		return result;
	}
	
	private Double calcularDuracionEstimada(Sede seleccionada) {
		return seleccionada.calcularDuracionEstimada(); 
	}
	
	private boolean verificarCapacidadMaximaSede(Sede seleccionada, int cantIngresada, LocalDateTime fechaHoraReserva) {
		int cantMax = seleccionada.getCantMaximaVisitantes();
		if(cantMax <= cantIngresada) {
			int sumaAsistentesOtrasExpoParaLaFecha = seleccionada.sumarCantidadVisitantes(fechaHoraReserva);
			return sumaAsistentesOtrasExpoParaLaFecha <= cantIngresada;
		}
		return false;
	}
	
	private int calcularCantidadGuiasNecesarios(Sede seleccionada, int cantAsistentesIngresada) {		
		
		return 0;
	}
	
	private List<String> obtenerGuias(Sede seleccionada, LocalDateTime fechaHoraReserva, Double duracionEstimada) {
		List<String> result = new ArrayList<>();
		for (Empleado e : seleccionada.getGuiasDispEnHorario(fechaHoraReserva)) {
			result.add(e.getNombre());
		}
		return result;
	}
	
	@PostMapping
	public void tomarConfirmacionReserva() {
		buscarEmpleadoLogueado();
		buscarUltimoNroReserva();
		buscarEstadoReserva();
		registrarReserva();
	}
	
	private String buscarEmpleadoLogueado() {
		return null;
	}
	
	private int buscarUltimoNroReserva() {
		return 0;
	}
	
	private Estado buscarEstadoReserva() {
		return null;
	}
	
	private void registrarReserva() {
		
	}
	
}
