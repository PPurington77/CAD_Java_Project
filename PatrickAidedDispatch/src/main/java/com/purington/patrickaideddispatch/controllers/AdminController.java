package com.purington.patrickaideddispatch.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.purington.patrickaideddispatch.models.Admin;
import com.purington.patrickaideddispatch.models.LoginUser;
import com.purington.patrickaideddispatch.services.AdminService;
import com.purington.patrickaideddispatch.services.SignalService;
import com.purington.patrickaideddispatch.services.UnitService;
import com.purington.patrickaideddispatch.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SignalService signalService;
	
	@Autowired UnitService unitService;
	
	
	//Display Admin login and registration page
	@GetMapping("/login")
	public String logReg(Model model, HttpSession session) {
		
		//checks if admin is already logged on
		if(session.getAttribute("auid") != null) {
			return "redirect:/admin/dashboard";
		}
		
		//bind empty admin and login user object to the jsp
		model.addAttribute("newAdmin", new Admin());
		model.addAttribute("newLogin", new LoginUser());
		
		return "admin.jsp";
	}
	
	//Action for creating an admin
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newAdmin") Admin newAdmin, BindingResult result, Model model, HttpSession session) {
		
		Admin admin = adminService.register(newAdmin, result);
		
		//check for errors w/ reg, bind new login to admin and redirect
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			
			return "admin.jsp";
		}
		
		//if no errors create admin session and redirect to dashboard
		session.setAttribute("auid", admin.getId());
		
		return "redirect:/admin/dashboard";
	}
	
	//Action for logging in an admin
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
			
		//creates user object
		Admin admin = adminService.login(newLogin, result);
			
		//checks for errors w/ login, binds new user, and redirects to show errors
		if(result.hasErrors()) {
			model.addAttribute("newAdmin", new Admin());
				
			return "admin.jsp";
		}
			
		//if no errors login user, create user session, and redirect to shelves
		session.setAttribute("auid", admin.getId());
			
		return "redirect:/admin/dashboard";
	}
	
	//logs out admin
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("auid");
		
		return "redirect:/";
	}
	
	//Display Admin Dashboard
	@GetMapping("/dashboard")
	public String adminDash(HttpSession session, Model model) {
		
		//check if admin is in session else redirect to home
		if(session.getAttribute("auid")== null) {
			return "redirect:/";
		}
		
		Long adminId = (Long) session.getAttribute("auid");
		model.addAttribute("admin", adminService.getOne(adminId));
		model.addAttribute("user", userService.allUsers());
		model.addAttribute("signal", signalService.allSignals());
		model.addAttribute("unit", unitService.allUnits());
		
		return "adminDashboard.jsp";
	}
	
	//Display Popup
	@GetMapping("/popup")
	public String popup() {
		
		return "autopopup.jsp";
	}
	
}
