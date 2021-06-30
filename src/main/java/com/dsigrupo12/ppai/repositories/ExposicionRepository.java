package com.dsigrupo12.ppai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dsigrupo12.ppai.entities.Exposicion;

@Repository
public interface ExposicionRepository extends CrudRepository<Exposicion, Integer> {

}
