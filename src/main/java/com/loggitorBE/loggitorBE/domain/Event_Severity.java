package com.loggitorBE.loggitorBE.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Event_Severity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String severity;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="event_sev")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
    private List<Event> events = new ArrayList<Event>();
	
	//empty constructor
	public Event_Severity() {}

	public Event_Severity(String severity) {
		super();
		this.severity = severity;
	}

	
	
	
	public Event_Severity(String severity, List<Event> events) {
		super();
		this.severity = severity;
		this.events = events;
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

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	
	public void setEvents(Event event) {
		this.events.add(event);
	}
	
	
	
}
