package com.loggitorBE.loggitorBE.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class EventInstance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String date;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "occurredEvent")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	DefinedEvent occurredEvent;
	
	//empty constructor
	public EventInstance() {}



	public EventInstance(String date) {
		super();
		this.date = date;
	}

	public EventInstance(String date, DefinedEvent occurredEvent) {
		super();
		this.date = date;
		this.occurredEvent = occurredEvent;
	}
	
	//getters and setters


	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public DefinedEvent getOccurredEvent() {
		return occurredEvent;
	}



	public void setOccurredEvent(DefinedEvent occurredEvent) {
		this.occurredEvent = occurredEvent;
	}


	





	
	
	
	
	
	
	
}
