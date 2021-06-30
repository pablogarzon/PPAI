package com.dsigrupo12.ppai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsigrupo12.ppai.entities.Empleado;
import com.dsigrupo12.ppai.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	public Empleado findbyName(String nom) {
		for (Empleado empleado : empleadoRepository.findAll()) {
			if (empleado.getNombre().equals(nom)) {
				return empleado;
			}
		}
		return null;
	}
}
