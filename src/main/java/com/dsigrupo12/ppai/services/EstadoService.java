package com.dsigrupo12.ppai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsigrupo12.ppai.entities.Estado;
import com.dsigrupo12.ppai.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repository;

	public Estado buscarEstadoPendienteConfirmacion() {
		for (Estado e : repository.findAll()) {
			if (e.esAmbitoReserva() && e.esPendienteDeConfirmacion()) {
				return e; 
			}
		}
		return null;
	}
}
