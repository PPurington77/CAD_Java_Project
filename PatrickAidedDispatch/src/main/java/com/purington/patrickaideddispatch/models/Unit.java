package com.purington.patrickaideddispatch.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="unit")
public class Unit {
	
	//Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Member Variables
	@NotEmpty(message="Field Required")
	@Size(max=3, message="")
	private String name;
	
	@NotNull
	private boolean isDispatched;
	
	//Data Creation Variables
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	//Constructor
	public Unit() {}
	
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
	@JoinColumn(name="incident_id", nullable=true)
	private Incident incident;

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean getIsDispatched() {
		return isDispatched;
	}

	public void setIsDispatched(boolean isDispatched) {
		this.isDispatched = isDispatched;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}



	
	
}
