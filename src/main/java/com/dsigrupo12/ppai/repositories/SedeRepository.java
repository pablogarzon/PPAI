package com.dsigrupo12.ppai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dsigrupo12.ppai.entities.Sede;

@Repository
public interface SedeRepository extends CrudRepository<Sede, String> {

}
