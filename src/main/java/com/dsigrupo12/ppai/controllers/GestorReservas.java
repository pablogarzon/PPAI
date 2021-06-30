package com.dsigrupo12.ppai.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsigrupo12.ppai.entities.Empleado;
import com.dsigrupo12.ppai.entities.Estado;
import com.dsigrupo12.ppai.entities.Exposicion;
import com.dsigrupo12.ppai.entities.Sede;
import com.dsigrupo12.ppai.entities.TipoVisita;
import com.dsigrupo12.ppai.services.EscuelaService;
import com.dsigrupo12.ppai.services.EstadoService;
import com.dsigrupo12.ppai.services.ReservaVisitaService;
import com.dsigrupo12.ppai.services.SedeService;

@Controller
@RequestMapping("/")
public class GestorReservas {

	private EscuelaService escuelaService;
	private SedeService sedeService;
	private ReservaVisitaService reservaVisitaService;
	private EstadoService estadoService;

	@Autowired
	public GestorReservas(EscuelaService escuelaService, SedeService sedeService,
			ReservaVisitaService reservaVisitaService, EstadoService estadoService) {
		this.escuelaService = escuelaService;
		this.sedeService = sedeService;
		this.reservaVisitaService = reservaVisitaService;
		this.estadoService = estadoService;
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
		return escuelaService.buscarEscuelas();
	}

	private List<String> buscarSedes() {
		return sedeService.buscarSedes();
	}

	private List<String> buscarTiposVisita() {
		List<String> result = new ArrayList<>();
		for (TipoVisita tv : TipoVisita.values()) {
			result.add(tv.getNombre());
		}
		return result;
	}

	@GetMapping(path = "/exposiciones")
	public @ResponseBody Map<?, ?> tomarSeleccionTipoVisita(@RequestParam("sede") String nombreSede,
			@RequestParam("tv") String nombreTV) {

		Map<String, List> exposiciones = new HashMap();

		TipoVisita tipoVisita = TipoVisita.getByName(nombreTV);

		if (tipoVisita == TipoVisita.POR_EXPOSICION) {
			exposiciones.put("exposiciones", buscarExposicionesTemporalesVigentes(nombreSede));
		}

		return exposiciones;
	}

	private List<Map<String, String>> buscarExposicionesTemporalesVigentes(String nombreSede) {
		List<Map<String, String>> result = new ArrayList<>();

		LocalDate fechaActual = obtenerFechaHoraSistema().toLocalDate();

		for (Exposicion exposicion : sedeService.getExposicionesTemporalesVigentes(nombreSede, fechaActual)) {
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
	public ResponseEntity<Map<?, ?>> tomarSelecionFechaHoraReserva(@RequestParam("sede") String nombreSede,
			@RequestParam("cantV") int cantAsistentesIngresada, @RequestParam("fecha") String fechaIngresada,
			@RequestParam("hora") String horaIngresada, @RequestParam("expos") int[] exposicionesSeleccionadas) {

		Map<String, Object> result = new HashMap<>();

		Sede seleccionada = sedeService.buscarSede(nombreSede);

		LocalDateTime fechaHoraReserva = LocalDateTime.parse(fechaIngresada + "T" + horaIngresada + ":00");

		long duracionEstimada = calcularDuracionEstimada(seleccionada, exposicionesSeleccionadas);

		boolean noSobrepasa = verificarCapacidadMaximaSede(seleccionada, cantAsistentesIngresada);

		if (noSobrepasa) {
			result.put("error", "Cantidad visitiantes sobrepasada");
			return new ResponseEntity<Map<?, ?>>(result, HttpStatus.BAD_REQUEST);
		}

		int cantGuias = calcularCantidadGuiasNecesarios(seleccionada, cantAsistentesIngresada);

		List<String> guias = obtenerGuias(seleccionada, fechaHoraReserva, duracionEstimada);

		result.put("cantidadNecesaria", cantGuias);
		result.put("guias", guias);

		return new ResponseEntity<Map<?, ?>>(result, HttpStatus.OK);
	}

	private long calcularDuracionEstimada(Sede seleccionada, int[] exposicionesSeleccionadas) {
		return seleccionada.calcularDuracionEstimada(exposicionesSeleccionadas);
	}

	private boolean verificarCapacidadMaximaSede(Sede seleccionada, int cantIngresada) {
		int cantMax = seleccionada.getCantMaximaVisitantes();
		return cantMax <= cantIngresada;
	}

	private int calcularCantidadGuiasNecesarios(Sede seleccionada, int cantAsistentesIngresada) {
		int cant = (int) Math.ceil(seleccionada.getCantMaximaVisitantes() / seleccionada.getCantMaxPorGuia());
		return cant;
	}

	private List<String> obtenerGuias(Sede seleccionada, LocalDateTime fechaHoraReserva, long duracionEstimada) {
		List<String> result = new ArrayList<>();
		for (Empleado e : seleccionada.getGuiasDispEnHorario(fechaHoraReserva, duracionEstimada)) {
			result.add(e.getNombre());
		}
		return result;
	}

	@PostMapping
	public ResponseEntity<?> tomarConfirmacionReserva(@RequestBody Map<String, Object> data) {
		
		String sede = data.get("sede").toString();
		List<Integer> exposiciones = (List<Integer>) data.get("exposiciones");
		List<String> guias = (List<String>) data.get("guias");
		int cantVisitantes = Integer.parseInt(data.get("cantVisitantes").toString());
		long duracion = Integer.parseInt(data.get("duracion").toString());
		
		LocalDateTime fechaHoraReserva = LocalDateTime.parse(data.get("fecha").toString() + "T" + data.get("hora").toString() + ":00");

		Empleado logueado = buscarEmpleadoLogueado();
		int ultimoNro = buscarUltimoNroReserva();
		Estado pendiente = buscarEstadoNvaReserva();
		LocalDateTime fhActual = obtenerFechaHoraSistema();

		registrarReserva(sede, exposiciones, guias, cantVisitantes, fechaHoraReserva, duracion, ultimoNro, pendiente, fhActual);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	private Empleado buscarEmpleadoLogueado() {
		Empleado e = new Empleado();
		e.setNombre("Jose");
		e.setApellido("Luque");
		return new Empleado();
	}

	private int buscarUltimoNroReserva() {
		return reservaVisitaService.buscarUltimoNumero();
	}

	private Estado buscarEstadoNvaReserva() {
		return estadoService.buscarEstadoPendienteConfirmacion();
	}

	private void registrarReserva(String sede, List<Integer> exposiciones, List<String> guias, int cantidadAlumnos,
			LocalDateTime fechaHoraReserva, long duracion, int ultimoNro, Estado pendiente, LocalDateTime fhActual) {
		reservaVisitaService.registrar(sede, exposiciones, guias, cantidadAlumnos, fechaHoraReserva, duracion, ultimoNro, pendiente, fhActual);
	}
}
