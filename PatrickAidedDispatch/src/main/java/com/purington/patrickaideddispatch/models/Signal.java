package com.purington.patrickaideddispatch.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="signals")
public class Signal {
	
	//Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Member Variables
	@NotNull
	private int priority;
	
	@NotNull
	private int number;
	
	@NotEmpty(message="Field Required")
	@Size(max=100)
	private String name;
	
	//Data Creation Variables
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	//Constructors
	public Signal() {}
	
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
	@OneToMany(mappedBy="signal", fetch=FetchType.LAZY)
	private List<Incident> incidents;
	
	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	
	
}
