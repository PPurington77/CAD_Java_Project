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

import com.purington.patrickaideddispatch.models.Signal;
import com.purington.patrickaideddispatch.services.AdminService;
import com.purington.patrickaideddispatch.services.SignalService;

@Controller
@RequestMapping("/signal")
public class SignalController {
	
	@Autowired
	private SignalService signalService;
	
	@Autowired
	private AdminService adminService;
	
	//Display create signal page
	@GetMapping("/create")
	public String signal(@ModelAttribute("newSignal") Signal newSignal, Model model, HttpSession session) {
		
		//confirms admin is logged in
		if(session.getAttribute("auid")== null) {
			return "redirect:/";
		}
		
		//else go to page
		Long adminId = (Long) session.getAttribute("auid");
		model.addAttribute("admin", adminService.getOne(adminId));
		
		return "createSignal.jsp";
	}
	
	//Action create a signal
	@PostMapping("/create")
	public String createSignal(@Valid @ModelAttribute("newSignal") Signal newSignal, BindingResult result, Model model, HttpSession session) {
		
		//confirm admin is logged in
		if(session.getAttribute("auid")== null) {
			return "redirect:/admin/login";
		}
		
		//checks for errors
		if(result.hasErrors()) {
			return "createSignal.jsp";
		}
		
		//else create and redirect
		Long adminId = (Long) session.getAttribute("auid");
		model.addAttribute("admin", adminService.getOne(adminId));
		signalService.createSignal(newSignal);
		
		return "redirect:/admin/dashboard";

	}
	
	//Display a single Signal
	@GetMapping("/view/{id}")
	public String viewSignal(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		//confirms admin is logged in
		if(session.getAttribute("auid") == null) {
			return "redirect:/admin/login";
		}
		
		model.addAttribute("signal", signalService.getSignal(id));
		Long adminId = (Long) session.getAttribute("auid");
		model.addAttribute("admin", adminService.getOne(adminId));
		
		return "signal.jsp";
		
	}
	
	//Display signal update page
	@GetMapping("/update/{id}")
	public String updateSignal(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		//confirms admin is logged in
		if(session.getAttribute("auid") == null) {
			return "redirect:/admin/login";
		}
		
		model.addAttribute("signal", signalService.getSignal(id));
		Long adminId = (Long) session.getAttribute("auid");
		model.addAttribute("admin", adminService.getOne(adminId));
		
		return "updateSignal.jsp";
	}
	
	//Action to update a signal
	@PutMapping("/update/{id}")
	public String signalUpdate(@Valid @ModelAttribute("signal") Signal signal, BindingResult result, @PathVariable("id") Long id, HttpSession session) {
		
		//check for errors
		if(result.hasErrors()) {
			return "updateSignal.jsp";
		}
		
		//else update
		signalService.updateSignal(signal);
		
		return "redirect:/admin/dashboard";
	}
	
	//Delete signal
	@DeleteMapping("/delete/{id}")
	public String deleteSignal(@PathVariable("id") Long id, HttpSession session) {
		
		//confirms admin is logged in
		if(session.getAttribute("auid") == null) {
			return "redirect:/admin/login";
		}
		
		//else delete
		signalService.deleteSignal(id);
		
		return "redirect:/admin/dashboard";
	}
}
