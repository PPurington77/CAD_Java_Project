package com.purington.patrickaideddispatch.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.purington.patrickaideddispatch.models.Unit;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {
	
	Optional<Unit> findById(Long id);
	
	List<Unit> findAll();
}
