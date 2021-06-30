package com.dsigrupo12.ppai.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsigrupo12.ppai.entities.AsignacionVisita;
import com.dsigrupo12.ppai.entities.CambioEstado;
import com.dsigrupo12.ppai.entities.Estado;
import com.dsigrupo12.ppai.entities.ReservaVisita;
import com.dsigrupo12.ppai.repositories.ReservaVisitaRepository;

@Service
public class ReservaVisitaService {
	
	@Autowired
	private ReservaVisitaRepository repository;

	@Autowired
	private AsignacionVisitaService asignacionVisitaService;

	@Autowired
	private CambioEstadoService cambioEstadoService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private SedeService sedeService;

	@Autowired
	private ExposicionService exposicionService;


	public int buscarUltimoNumero() {
		return (int) (repository.count() + 1);
	}

	public ReservaVisita registrar(String sede, List<Integer> exposiciones, List<String> guias, int cantidadAlumnos,
			LocalDateTime fechaHoraReserva, long duracion, int ultimoNro, Estado pendiente,
			LocalDateTime fechaHoraCreacion) {
		// TODO Auto-generated method stub
		ReservaVisita rv = new ReservaVisita();

		rv.setSede(sedeService.buscarSede(sede));
		rv.setExposiciones(exposicionService.buscarExposiciones(exposiciones));
		rv.setCambioEstado(Collections.singletonList(crearCambioEstado(pendiente, fechaHoraCreacion)));
		rv.setAsignacionVisita(crearAsignacionGuia(guias, fechaHoraReserva, duracion));
		rv.setFechaHoraReserva(fechaHoraReserva);
		rv.setFechaHoraCreacion(fechaHoraCreacion);
		rv.setCantidadAlumnos(cantidadAlumnos);
		rv.setDuracionEstimada(duracion);
		rv.setNumeroReserva(ultimoNro);

		repository.save(rv);

		return rv;
	}

	private CambioEstado crearCambioEstado(Estado pendiente, LocalDateTime fechaHoraCreacion) {
		CambioEstado ce = new CambioEstado();
		ce.setEstado(pendiente);
		ce.setFechaHoraInicio(fechaHoraCreacion);
		return cambioEstadoService.save(ce);
	}

	List<AsignacionVisita> crearAsignacionGuia(List<String> guias, LocalDateTime fechaHoraReserva, long duracion) {
		List<AsignacionVisita> asingaciones = new ArrayList<>();

		for (String g : guias) {
			AsignacionVisita av = new AsignacionVisita();

			av.setEmpleado(empleadoService.findbyName(g));
			av.setFechaHoraInicio(fechaHoraReserva);
			av.setFechaHoraFin(fechaHoraReserva.plusMinutes(duracion));

			asingaciones.add(asignacionVisitaService.save(av));
		}

		return asingaciones;
	}
}
