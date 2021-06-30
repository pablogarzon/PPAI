package com.dsigrupo12.ppai.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsigrupo12.ppai.entities.Exposicion;
import com.dsigrupo12.ppai.repositories.ExposicionRepository;

@Service
public class ExposicionService {

	@Autowired
	private ExposicionRepository repository;

	public List<Exposicion> buscarExposiciones(List<Integer> exposiciones) {
		List<Exposicion> result = new ArrayList<>();
		
		for (Exposicion expo : repository.findAll()) {
			for (int id : exposiciones) {
				if (expo.getId() == id) {
					result.add(expo);
				}
			}
		}
		return result;
	}
	

}
