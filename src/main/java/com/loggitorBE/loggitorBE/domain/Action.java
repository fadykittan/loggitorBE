package com.loggitorBE.loggitorBE.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Action {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String name;
	@Column(name="ACTION_NAME")
	String action;
	
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="action")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
    private List<Event> events = new ArrayList<Event>();
	
	
	// empty constructor
	public Action() {}
	
	public Action(String name, String action) {
		super();
		this.name = name;
		this.action = action;
	}

	
	
	
	public Action(String name, String action, List<Event> events) {
		super();
		this.name = name;
		this.action = action;
		this.events = events;
	}

	//setters and getters
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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
