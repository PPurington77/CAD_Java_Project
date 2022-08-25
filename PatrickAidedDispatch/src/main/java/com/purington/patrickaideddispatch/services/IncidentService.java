package com.purington.patrickaideddispatch.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purington.patrickaideddispatch.models.Incident;
import com.purington.patrickaideddispatch.repositories.IncidentRepository;

@Service
public class IncidentService {
	
	@Autowired
	private IncidentRepository incidentRepository;
	
	//CRUD
	
	//Create
	
	public Incident createIncident(Incident incident) {
		return incidentRepository.save(incident);
	}
	
	//Read All
	
	public List<Incident> allIncidents() {
		return incidentRepository.findAll();
	}
	
	//Read one
	
	public Incident getIncident(Long id) {
		Optional<Incident> optionalIncident = incidentRepository.findById(id);
		if(optionalIncident.isPresent()) {
			return optionalIncident.get();
		}
		else return null;
	}
	
	//Update
	
	public Incident updateIncident(Incident incident) {
		return incidentRepository.save(incident);
	}
	
	//Delete
	public void deleteIncident(Long id) {
		incidentRepository.deleteById(id);
	}
}
