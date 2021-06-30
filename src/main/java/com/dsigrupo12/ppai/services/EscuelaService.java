package com.dsigrupo12.ppai.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsigrupo12.ppai.entities.Escuela;
import com.dsigrupo12.ppai.repositories.EscuelaRepository;

@Service
public class EscuelaService {
	
	@Autowired
	private EscuelaRepository repository;
	
	public List<String> buscarEscuelas() {
		List<String> result = new ArrayList<>();
		for (Escuela escuela : repository.findAll()) {
			result.add(escuela.getNombre());
		}
		return result;
	}
}
