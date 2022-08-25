package com.purington.patrickaideddispatch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purington.patrickaideddispatch.models.Signal;
import com.purington.patrickaideddispatch.repositories.SignalRepository;

@Service
public class SignalService {
	
	@Autowired
	private SignalRepository signalRepository;
	
	//CRUD
	
	//Create
	
	public Signal createSignal(Signal signal) {
		return signalRepository.save(signal);
	}
	
	//Read All
	
	public List<Signal> allSignals() {
		return signalRepository.findAll();
	}
	
	//Read One
	
	public Signal getSignal(Long id) {
		return signalRepository.findById(id).orElse(null);
	}
	
	//Update
	
	public Signal updateSignal(Signal signal) {
		return signalRepository.save(signal);
	}
	
	//Delete
	
	public void deleteSignal(Long id) {
		signalRepository.deleteById(id);
	}
}
