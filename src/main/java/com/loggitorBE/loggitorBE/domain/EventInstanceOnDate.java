package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;


public class EventInstanceOnDate {

	
	BigInteger id;
	String eventName;
	String severity;
	String description;
	String fixAction;
	
	
	
	//empty constructor 
	public EventInstanceOnDate() {}



	public EventInstanceOnDate(BigInteger id, String eventName, String severity, String description, String fixAction) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.severity = severity;
		this.description = description;
		this.fixAction = fixAction;
	}



	public EventInstanceOnDate(String eventName, String severity, String description, String fixAction) {
		super();
		this.eventName = eventName;
		this.severity = severity;
		this.description = description;
		this.fixAction = fixAction;
	}


	// getters and setters

	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public String getEventName() {
		return eventName;
	}



	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public String getSeverity() {
		return severity;
	}



	public void setSeverity(String severity) {
		this.severity = severity;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getFixAction() {
		return fixAction;
	}



	public void setFixAction(String fixAction) {
		this.fixAction = fixAction;
	}
	

	


	
	
}
