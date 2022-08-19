package com.purington.pad.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.purington.pad.models.LoginUser;
import com.purington.pad.models.User;
import com.purington.pad.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//autowire whatever other tables you have relationships with here
	
	//Display 
	@RequestMapping("/")
	public String index() {
		return "redirect:/user/login";
	}
	
	//Display User login page
	@GetMapping("/user/login")
	public String loginRegistration(Model model, HttpSession session) {
		
		//checks if user is already logged in 
		if(session.getAttribute("uuid") != null) {
			return "redirect:/incident/dashboard";
		}
		
		//Bind empty User and LoginUser object to the JSP
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "index.jsp";
	}
}
