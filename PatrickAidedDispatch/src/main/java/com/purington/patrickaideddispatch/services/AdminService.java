package com.purington.patrickaideddispatch.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.purington.patrickaideddispatch.models.Admin;
import com.purington.patrickaideddispatch.models.LoginUser;
import com.purington.patrickaideddispatch.repositories.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	//CRUD BELOW
	
	//Create
	//Admin registration
	public Admin register(Admin newAdmin, BindingResult result) {
		
		//check if email is already in the DB
		if(adminRepository.findByEmail(newAdmin.getEmail()).isPresent()) {
			result.rejectValue("email", "unique", "Email already in use");
		}
		
		//check for pin
		if(newAdmin.getPin() != 1234) {
			result.rejectValue("pin", "matches", "Pin is not correct");;
		}
		
		//check for any other errors
		if(result.hasErrors()) {
			return null;
		}
		
		//hash pw and set password w/ BCrypt
		String hashed = BCrypt.hashpw(newAdmin.getPassword(), BCrypt.gensalt());
		newAdmin.setPassword(hashed);
		
		return adminRepository.save(newAdmin);
	}
	
	//Login admin
	public Admin login(LoginUser newLogin, BindingResult result) {
		
		//check for errors
		if(result.hasErrors()) {
			return null;
		}
		
		//check if email is in the db
		Optional<Admin> potentialAdmin = adminRepository.findByEmail(newLogin.getEmail());
		if(!potentialAdmin.isPresent()) {
			result.rejectValue("email", "unique", "Invalid Credentials");
			
			return null;
		}
		
		//check if password matches password in the db
		Admin admin = potentialAdmin.get();//gets admin object from the optional admin
		if(!BCrypt.checkpw(newLogin.getPassword(), admin.getPassword())) {
			result.rejectValue("password", "matches", "Invalid Credentials");
		}
		
		return admin;
	}
	
	//Read Admin
	public Admin getOne(Long id) {
		return adminRepository.findById(id).orElse(null);
	}
	
	public List<Admin>allAdmin() {
		return this.adminRepository.findAll();
	}
	
	//Update Admin
	public Admin updateAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	//Delete Admin
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}
}
