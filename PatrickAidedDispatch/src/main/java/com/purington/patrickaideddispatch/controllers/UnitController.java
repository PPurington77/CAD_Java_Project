package com.purington.patrickaideddispatch.controllers;

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

import com.purington.patrickaideddispatch.models.Unit;
import com.purington.patrickaideddispatch.services.AdminService;
import com.purington.patrickaideddispatch.services.IncidentService;
import com.purington.patrickaideddispatch.services.UnitService;
import com.purington.patrickaideddispatch.services.UserService;

@Controller
@RequestMapping("/unit")
public class UnitController {

	@Autowired
	private UnitService unitService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IncidentService incidentService;
	
	//Display create unit page
	@GetMapping("/create")
	public String unit(@ModelAttribute("newUnit") Unit newUnit, Model model, HttpSession session) {
		
		//confirms admin is logged in
		if(session.getAttribute("auid")== null) {
			return "redirect:/";
		}
		
		//else go to page
		Long adminId = (Long) session.getAttribute("auid");
		model.addAttribute("admin", adminService.getOne(adminId));
		
		return "createUnit.jsp";
	}
	
	//Action to create a unit
	@PostMapping("/create")
	public String createUnit(@Valid @ModelAttribute("newUnit") Unit newUnit, BindingResult result, Model model, HttpSession session) {
		
		//confirm admin is logged in
		if(session.getAttribute("auid")== null) {
			return "redirect:/admin/login";
		}
		
		
		//checks for errors
		if(result.hasErrors()) {
			return "createUnit.jsp";
		}
		
		//else create and redirect
		Long adminId = (Long) session.getAttribute("auid");
		model.addAttribute("admin", adminService.getOne(adminId));
		unitService.createUnit(newUnit);
		
		return "redirect:/admin/dashboard";

	}
	
	//Display a single unit from admin
	@GetMapping("/view/{id}")
	public String viewUnit(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		//confirms admin is logged in
		if(session.getAttribute("auid") == null) {
			return "redirect:/admin/login";
		}
		
		model.addAttribute("unit", unitService.getUnit(id));
		
		return "unit.jsp";
		
	}
	
	//Display unit update page from admin
	@GetMapping("/update/{id}")
	public String updateUnit(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		//confirms admin is logged in
		if(session.getAttribute("auid") == null) {
			return "redirect:/admin/login";
		}
		
		model.addAttribute("unit", unitService.getUnit(id));
		Long adminId = (Long) session.getAttribute("auid");
		model.addAttribute("admin", adminService.getOne(adminId));
		
		return "unitUpdate.jsp";
	}
	
	//Display unit update page from user
	@GetMapping("/up/{id}")
	public String upUnit(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		//confirms user is logged in
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		
		model.addAttribute("unit", unitService.getUnit(id));
		Long userId = (Long) session.getAttribute("uuid");
		model.addAttribute("user", userService.getOne(userId));
		model.addAttribute("incident", incidentService.allIncidents());
		
		return "upUnit.jsp";
	}
	
	//Action to update a unit from admin
	@PutMapping("/update/{id}")
	public String unitUpdate(@Valid @ModelAttribute("unit") Unit unit, BindingResult result, @PathVariable("id") Long id, HttpSession session) {
		
		//check for errors
		if(result.hasErrors()) {
			return "unitUpdate.jsp";
		}
		
		//else update
		unitService.updateUnit(unit);
		
		return "redirect:/admin/dashboard";
	}
	
	//Action to update a unit from user
	@PutMapping("/up/{id}")
	public String unitUp(@Valid @ModelAttribute("unit") Unit unit, BindingResult result, @PathVariable("id") Long id, HttpSession session) {
		
		//check for errors
		if(result.hasErrors()) {
			return "unitUpdate.jsp";
		}
		
		//else update
		unitService.updateUnit(unit);
		
		return "redirect:/incident/dashboard";
	}
	
	//Delete signal
	@DeleteMapping("/delete/{id}")
	public String deleteSignal(@PathVariable("id") Long id, HttpSession session) {
		
		//confirms admin is logged in
		if(session.getAttribute("auid") == null) {
			return "redirect:/admin/login";
		}
		
		//else delete
		unitService.deleteUnit(id);
		
		return "redirect:/admin/dashboard";
	}
}
