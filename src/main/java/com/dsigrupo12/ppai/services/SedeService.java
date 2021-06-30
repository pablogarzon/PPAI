package com.dsigrupo12.ppai.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsigrupo12.ppai.entities.Exposicion;
import com.dsigrupo12.ppai.entities.Sede;
import com.dsigrupo12.ppai.entities.TipoExposicion;
import com.dsigrupo12.ppai.repositories.SedeRepository;

@Service
public class SedeService {

	@Autowired
	private SedeRepository sedeRepository;
	
	public List<String> buscarSedes() {
		List<String> result = new ArrayList<>();
		for (Sede sede : sedeRepository.findAll()) {
			result.add(sede.getNombre());
		}
		return result;
	}
	
	public Sede buscarSede(String nombreSede) {
		return sedeRepository.findById(nombreSede).get();
	}
	
	public List<Exposicion> getExposicionesTemporalesVigentes(String sede, LocalDate fecha) {
		List<Exposicion> exposicionesVigentes = new ArrayList<>();
		
		Sede seleccionada = buscarSede(sede);
		
		for (Exposicion exposicion: seleccionada.getExposiciones()) {
			if (exposicion.getFechaInicio().isBefore(fecha) && exposicion.getFechaFin().isAfter(fecha)) {
				if(exposicion.getTipoExposicion() == TipoExposicion.TEMPORAL) {
					exposicionesVigentes.add(exposicion);
				}
			}
		}
		
		return exposicionesVigentes;
	}
}
