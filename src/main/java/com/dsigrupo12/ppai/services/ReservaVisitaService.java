package com.dsigrupo12.ppai.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsigrupo12.ppai.entities.AsignacionVisita;
import com.dsigrupo12.ppai.entities.CambioEstado;
import com.dsigrupo12.ppai.entities.Empleado;
import com.dsigrupo12.ppai.entities.Estado;
import com.dsigrupo12.ppai.entities.Exposicion;
import com.dsigrupo12.ppai.entities.ReservaVisita;
import com.dsigrupo12.ppai.entities.Sede;
import com.dsigrupo12.ppai.repositories.CambioEstadoRepository;
import com.dsigrupo12.ppai.repositories.ReservaVisitaRepository;

@Service
public class ReservaVisitaService {

	@Autowired
	private ReservaVisitaRepository repository;

	@Autowired
	private AsignacionVisitaRepository asignacionVisitaRepository;
	
	@Autowired
	private CambioEstadoRepository cambioEstadoRepository;

	public int buscarUltimoNumero() {
		return (int) (repository.count() + 1);
	}

	public ReservaVisita registrar(Sede sede, List<Exposicion> exposiciones, List<Empleado> guias, int cantidadAlumnos,
			LocalDateTime fechaHoraReserva, long duracion, int ultimoNro, Estado pendiente, LocalDateTime fechaHoraCreacion) {
		// TODO Auto-generated method stub
		ReservaVisita rv = new ReservaVisita();
		
		rv.setSede(sede);
		rv.setExposiciones(exposiciones);
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
		return cambioEstadoRepository.save(ce);
	}

	List<AsignacionVisita> crearAsignacionGuia(List<Empleado> guias, LocalDateTime fechaHoraReserva, long duracion) {
		List<AsignacionVisita> asingaciones = new ArrayList<>();
		
		for (Empleado empleado : guias) {
			AsignacionVisita av = new AsignacionVisita();
			av.setEmpleado(empleado);
			av.setFechaHoraInicio(fechaHoraReserva);
			av.setFechaHoraFin(fechaHoraReserva.plusMinutes(duracion));
			
			asingaciones.add(asignacionVisitaRepository.save(av));
		} 
		
		return asingaciones;
	}
}
