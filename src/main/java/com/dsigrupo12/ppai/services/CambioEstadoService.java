package com.dsigrupo12.ppai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsigrupo12.ppai.entities.CambioEstado;
import com.dsigrupo12.ppai.repositories.CambioEstadoRepository;

@Service
public class CambioEstadoService {
	
	@Autowired
	private CambioEstadoRepository cambioEstadoRepository;

	public CambioEstado save(CambioEstado ce) {
		return cambioEstadoRepository.save(ce);
	}
	
	
}
