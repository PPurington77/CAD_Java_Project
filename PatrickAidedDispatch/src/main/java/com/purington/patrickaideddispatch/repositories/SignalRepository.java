package com.purington.patrickaideddispatch.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.purington.patrickaideddispatch.models.Signal;

@Repository
public interface SignalRepository extends CrudRepository<Signal, Long>{
	
	Optional<Signal> findById(Long id);
	
	Optional<Signal> findByNumber(int number);
	
	List<Signal> findAll();
}
