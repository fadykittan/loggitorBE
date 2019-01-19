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
public class App {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String name;
	String type;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="app")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
    private List<Event> events = new ArrayList<Event>();
	
	//empty constructor
	public App() {}

	public App(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	
	
	
	public App(String name, String type, List<Event> events) {
		super();
		this.name = name;
		this.type = type;
		this.events = events;
	}
	
	public App(String name, String type, Event event) {
		super();
		this.name = name;
		this.type = type;
		this.events.add(event);
	}

	//getters and setters
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
