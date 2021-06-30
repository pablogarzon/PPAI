package com.dsigrupo12.ppai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dsigrupo12.ppai.entities.Sesion;

@Repository
public interface SesionRepository extends CrudRepository<Sesion, Integer> {

}
