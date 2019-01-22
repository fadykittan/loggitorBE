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
	String actionName;

	//@OneToMany(cascade = CascadeType.ALL, mappedBy="fixAction")
	@OneToMany(mappedBy="fixAction")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private Set<DefinedEvent> definedEvents = new HashSet<DefinedEvent>();

	// empty constructor
	public FixAction() {
	}

	public FixAction(String actionName) {
		super();
		this.actionName = actionName;
	}


	public FixAction(String actionName, Set<DefinedEvent> definedEvents) {
		super();
		this.actionName = actionName;
		this.definedEvents = definedEvents;
	}

	public FixAction(String actionName, DefinedEvent definedEvent) {
		super();
		this.actionName = actionName;
		this.definedEvents.add(definedEvent);
	}
	
	// setters and getters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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
