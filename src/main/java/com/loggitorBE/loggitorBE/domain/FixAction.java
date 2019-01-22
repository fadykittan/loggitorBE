package com.loggitorBE.loggitorBE.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FixAction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String name;

	// private List<DefinedEvent> definedEvents = new ArrayList<DefinedEvent>();

	// empty constructor
	public FixAction() {
	}

	public FixAction(String name) {
		super();
		this.name = name;
	}
	

	// setters and getters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	
}
