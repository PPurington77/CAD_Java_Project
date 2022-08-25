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

import com.purington.patrickaideddispatch.models.LoginUser;
import com.purington.patrickaideddispatch.models.User;
import com.purington.patrickaideddispatch.services.AdminService;
import com.purington.patrickaideddispatch.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
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
	
	//Action for registering the user
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		
		User user =userService.register(newUser, result);
		
		//check for errors w/ registration, bind new login user, and redirect to show errors
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			
			return "index.jsp";
		}
		
		//if no errors create user session and redirect to CAD page
		session.setAttribute("uuid", user.getId());
		
		return "redirect:/incident/dashboard";
	}
	
	//Action for logging in a user
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
			
		//creates user object
		User user = userService.login(newLogin, result);
			
		//checks for errors w/ login, binds new user, and redirects to show errors
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
				
			return "index.jsp";
		}
			
		//if no errors login user, create user session, and redirect to shelves
		session.setAttribute("uuid", user.getId());
			
		return "redirect:/incident/dashboard";
	}
	
	//logs out user
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("uuid");
			
		return "redirect:/";
	}
	
	//Displays a single user from the admin dashboard
	@GetMapping("/user/admin/view/{id}")
	public String viewUserAdmin(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		//confirms admin is logged in 
		if(session.getAttribute("auid") == null) {
			return "redirect:/admin/login";
		}
		
		Long adminId = (Long) session.getAttribute("auid");
		model.addAttribute("admin", adminService.getOne(adminId));
		model.addAttribute("user", userService.getOne(id));
		
		return "userAdminView.jsp";
	}
	
	//Action Delete User
	@DeleteMapping("user/delete/{id}")
	public String deleteUser(@PathVariable("id")Long id, HttpSession session) {
		
		//Confirm admin is logged in 
		if(session.getAttribute("auid") == null) {
			return "redirect:/admin/login";
		}
		
		//else delete
		userService.deleteUser(id);
		
		return "redirect:/admin/dashboard";
	}
	
	//Display User Helpdesk
	@GetMapping("/user/helpdesk")
	public String helpdesk(Model model, HttpSession session) {
		
		//confirm user is logged in
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
	
		//else display page
		Long userId = (Long) session.getAttribute("uuid");
		model.addAttribute("user", userService.getOne(userId));
		
		return "helpdesk.jsp";
	}
}
