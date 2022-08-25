package com.purington.patrickaideddispatch.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {
	
	//member variables
	@NotEmpty(message="Please enter a valid email")
	@Email(message="")
	private String email;
	
	@NotEmpty(message="")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
	private String password;
	
	//constructor
	public LoginUser() {}

	//getters and setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
