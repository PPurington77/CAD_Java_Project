package com.purington.patrickaideddispatch.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.purington.patrickaideddispatch.models.Incident;
import com.purington.patrickaideddispatch.models.Signal;
import com.purington.patrickaideddispatch.models.Unit;
import com.purington.patrickaideddispatch.models.User;
import com.purington.patrickaideddispatch.services.IncidentService;
import com.purington.patrickaideddispatch.services.SignalService;
import com.purington.patrickaideddispatch.services.UnitService;
import com.purington.patrickaideddispatch.services.UserService;

@Controller
@RequestMapping("/incident")
public class IncidentController {
	
	@Autowired
	private IncidentService incidentService;
	
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private SignalService signalService;
	
	@Autowired
	private UserService userService;
	
	//Display Incident Dashboard
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		
		//check if user is logged in
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		
		//confirm admin isn't logged in
		if(session.getAttribute("auid") != null) {
			return "redirect:/admin/dashboard";
		}
		
		//else display dashboard for user
		model.addAttribute("signal", signalService.allSignals());
		model.addAttribute("unit", unitService.allUnits());
		model.addAttribute("incident", incidentService.allIncidents());
		Long userId = (Long) session.getAttribute("uuid");
		model.addAttribute("user", userService.getOne(userId));
		
		return "incidentDashboard.jsp";
	}
	
	//Display Create incident
	@GetMapping("/create")
	public String createIncident(@ModelAttribute("newIncident")Incident newIncident, Model model, HttpSession session) {
		
		//confirm user is logged in
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		
		List<Signal> signals = signalService.allSignals();
		model.addAttribute("signal", signals);
		Long userId = (Long) session.getAttribute("uuid");
		model.addAttribute("user", userService.getOne(userId));
		
		return "createIncident.jsp";
		
	}
	
	//Action creating an incident
	@PostMapping("/create")
	public String incidentCreate(@Valid @ModelAttribute("newIncident")Incident newIncident, BindingResult result, Model model, HttpSession session) {
		
		//confirms user is logged in
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		
		//check for errors
		if(result.hasErrors()) {
			List<Signal> signals = signalService.allSignals();
			model.addAttribute("signal", signals);
			Long userId = (Long) session.getAttribute("uuid");
			model.addAttribute("user", userService.getOne(userId));	
			return "createIncident.jsp";
		}
		//if no errors bind the user to the incident
		User user = userService.getOne((Long)session.getAttribute("uuid"));
		newIncident.setUser(user);;
		
		incidentService.createIncident(newIncident);
		
		return "redirect:/incident/dashboard";
	}
	
	//Display a single incident
	@GetMapping("/view/{id}")
	public String viewIncident(@PathVariable("id")Long id, Model model, HttpSession session) {
		
		//confirms user is logged in
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		
		model.addAttribute("incident", incidentService.getIncident(id));
		
		return "incident.jsp";
	}
	
	//Display incident update page
	@GetMapping("/update/{id}")	
	public String updateIncident(@PathVariable("id")Long id, Model model, HttpSession session) {
		
		//confirm user is logged in
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		
		List<Signal> signals = signalService.allSignals();
		model.addAttribute("signal", signals);
		model.addAttribute("incident", incidentService.getIncident(id));
		
		return "updateIncident.jsp";
		
	}
	
	//Action to update incident
	@PutMapping("/update/{id}")
	public String updateIncident(@Valid @ModelAttribute("incident")Incident incident, BindingResult result, Model model, HttpSession session) {
		
		//check for errors
		if(result.hasErrors()) {
			List<Signal> signals = signalService.allSignals();
			model.addAttribute("signal", signals);
			
			return "updateIncident.jsp";
		}
		
		//else update
		incidentService.updateIncident(incident);
		
		return "redirect:/incident/dashboard";
	}
	
	//Action to delete incident
	@DeleteMapping("/delete/{id}")
	public String deleteIncident(@PathVariable("id") Long id, HttpSession session) {
		
		//confirm user is logged in
		if(session.getAttribute("uuid") == null) {
			
			return "redirect:/";
		}
		
		//get incident w/ primary key
		Incident incident = incidentService.getIncident(id);
		List<Unit> units = incident.getUnits();
		
		//set incident id to null in units
		for(Unit unit : units) {
			unit.setIncident(null);
		}
		//else delete
		incidentService.deleteIncident(id);
		
		return "redirect:/incident/dashboard";
	}
}
