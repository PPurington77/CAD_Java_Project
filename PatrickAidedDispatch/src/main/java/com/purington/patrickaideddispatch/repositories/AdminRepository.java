package com.purington.patrickaideddispatch.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.purington.patrickaideddispatch.models.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
	
	Optional<Admin> findByEmail(String email);
	
	List<Admin> findAll();
}
