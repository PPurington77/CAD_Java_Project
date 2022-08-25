package com.purington.patrickaideddispatch.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="incidents")
public class Incident {
	
	//Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Member Variables
	
	@NotNull(message="Field is Required")
	private int streetNumber;
	
	@NotEmpty(message="")
	@Size(min=1, max=2, message="Use Abbreviations")
	private String streetDirection;
	
	@NotEmpty(message="Field is Required")
	private String streetName;
	
	@NotEmpty(message="Field is Required")
	private String city;
	
	@NotEmpty(message="Field is Required")
	@Size(max=255)
	private String callerName;
	
	@NotEmpty(message="")
	@Size(min=9, max=10, message="Please enter a valid Phone number")
	private String phone;
	
	@NotNull
	private boolean contact;
	
	@NotNull
	private boolean isHandled;
	
	@NotEmpty(message="Field Required")
	private String description;
	
	//Data Creation Variables
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	//Constructors
	public Incident() {}
	
	//Data Creation Events
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
		
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	//Relationships
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="signal_id")
	private Signal signal;
	
	@OneToMany(mappedBy="incident",fetch=FetchType.LAZY)
	private List<Unit> units;
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetDirection() {
		return streetDirection;
	}

	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getContact() {
		return contact;
	}

	public void setContact(boolean contact) {
		this.contact = contact;
	}

	public boolean getIsHandled() {
		return isHandled;
	}

	public void setIsHandled(boolean isHandled) {
		this.isHandled = isHandled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Signal getSignal() {
		return signal;
	}

	public void setSignal(Signal signal) {
		this.signal = signal;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
	
	
}

