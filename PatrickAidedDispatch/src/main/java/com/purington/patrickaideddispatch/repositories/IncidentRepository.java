package com.purington.patrickaideddispatch.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.purington.patrickaideddispatch.models.Incident;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Long> {
	
	Optional<Incident> findById(Long id);
	
	List<Incident> findAll();
}
