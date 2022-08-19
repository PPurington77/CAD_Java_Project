package com.purington.pad.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="admins")
public class Admin {
	
	//Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//Member VAriables
	@NotEmpty
    @Size(min=3, max=30, message="First name is required")
    private String firstName;
    
    @NotEmpty
    @Size(min=3, max=30, message="Last Name is required!")
    private String lastName;
    
    @NotEmpty
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    @Transient
    @NotEmpty
    @Size(min=8, max=128, message="Must match Password")
    private String confirm;
    
    //pin will be provided in order to make admin account
    @Transient
    @NotNull(message="Must match provided Pin")
    private int pin;
    
    //Data Creation Variables
    @Column(updatable=false)
    private Date createdAt;
    
    private Date updatedAt;
    
    //Constructors
    public Admin() {}
    
    //Relationships
    @OneToMany(mappedBy="admin", fetch=FetchType.LAZY)
    private List<User> user;
    
    //Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
}
