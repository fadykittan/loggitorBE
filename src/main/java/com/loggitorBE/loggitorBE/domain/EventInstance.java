package com.loggitorBE.loggitorBE.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class EventInstance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String date;

	
	
	//DefinedEvent occurred_event;
	
	//empty constructor
	public EventInstance() {}



	public EventInstance(String date) {
		super();
		this.date = date;
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


	





	
	
	
	
	
	
	
}
