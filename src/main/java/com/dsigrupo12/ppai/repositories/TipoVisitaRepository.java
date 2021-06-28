package com.dsigrupo12.ppai.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dsigrupo12.ppai.entities.TipoVisita;

@Repository
public interface TipoVisitaRepository extends CrudRepository<TipoVisita, String> {

}
