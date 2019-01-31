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
public class EventSeverity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String severity;
	
	
	@OneToMany(mappedBy="eventSev")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private Set<DefinedEvent> definedEvents = new HashSet<DefinedEvent>();
	
	//empty constructor
	public EventSeverity() {}


	public EventSeverity(String severity) {
		super();
		this.severity = severity;
	}

	

	public EventSeverity(String severity, Set<DefinedEvent> definedEvents) {
		super();
		this.severity = severity;
		this.definedEvents = definedEvents;
	}


	//getters and setters
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSeverity() {
		return severity;
	}


	public void setSeverity(String severity) {
		this.severity = severity;
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
