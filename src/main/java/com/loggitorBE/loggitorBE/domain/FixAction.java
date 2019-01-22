package com.loggitorBE.loggitorBE.domain;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class FixAction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String name;

	//@OneToMany(cascade = CascadeType.ALL, mappedBy="fixAction")
	@OneToMany(mappedBy="fixAction")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private Set<DefinedEvent> definedEvents = new HashSet<DefinedEvent>();

	// empty constructor
	public FixAction() {
	}

	public FixAction(String name) {
		super();
		this.name = name;
	}


	public FixAction(String name, Set<DefinedEvent> definedEvents) {
		super();
		this.name = name;
		this.definedEvents = definedEvents;
	}

	public FixAction(String name, DefinedEvent definedEvent) {
		super();
		this.name = name;
		this.definedEvents.add(definedEvent);
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

	public Set<DefinedEvent> getDefinedEvents() {
		return definedEvents;
	}

	public void setDefinedEvents(Set<DefinedEvent> definedEvents) {
		this.definedEvents = definedEvents;
	}

	
	public void setDefinedEvent(DefinedEvent definedEvent) {
		this.definedEvents.add(definedEvent);
	}

	
	
}
