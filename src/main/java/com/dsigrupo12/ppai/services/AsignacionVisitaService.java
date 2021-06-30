package com.dsigrupo12.ppai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsigrupo12.ppai.entities.AsignacionVisita;
import com.dsigrupo12.ppai.repositories.AsignacionVisitaRepository;

@Service
public class AsignacionVisitaService {

	@Autowired
	private AsignacionVisitaRepository repository;

	public AsignacionVisita save(AsignacionVisita av) {
		return repository.save(av);
	}
	
	
}
