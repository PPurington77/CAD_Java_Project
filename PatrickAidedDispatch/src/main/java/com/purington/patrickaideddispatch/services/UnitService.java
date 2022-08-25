package com.purington.patrickaideddispatch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purington.patrickaideddispatch.models.Unit;
import com.purington.patrickaideddispatch.repositories.UnitRepository;

@Service
public class UnitService {

	@Autowired
	private UnitRepository unitRepository;
	
	//CRUD
	
	//Create
	
	public Unit createUnit(Unit unit) {
		return unitRepository.save(unit);
	}
	
	//Real All
	
	public List<Unit> allUnits() {
		return unitRepository.findAll();
	}
	
	//Read one
	
	public Unit getUnit(Long id) {
		return unitRepository.findById(id).orElse(null);
	}
	
	//Update
	
	public Unit updateUnit(Unit unit) {
		return unitRepository.save(unit);
	}
	
	//Delete
	
	public void deleteUnit(Long id) {
		unitRepository.deleteById(id);
	}
}
