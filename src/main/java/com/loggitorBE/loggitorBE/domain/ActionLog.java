package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;


public class ActionLog {

	
	BigInteger id;
	String name;
	String severity;
	String desc;
	String action;
	
	
	
	//empty constructor 
	public ActionLog() {}
	
	
	public ActionLog(BigInteger id, String name, String severity, String desc, String action) {
		super();
		this.id = id;
		this.name = name;
		this.severity = severity;
		this.desc = desc;
		this.action = action;
	}

	
	
	public ActionLog(String name, String severity, String desc, String action) {
		super();
		this.name = name;
		this.severity = severity;
		this.desc = desc;
		this.action = action;
	}
	
	public ActionLog(String name, String severity, String desc) {
		super();
		this.name = name;
		this.severity = severity;
		this.desc = desc;
	}


	// getters and setters

	public BigInteger getId() {
		return id;
	}


	public void setId(BigInteger id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSeverity() {
		return severity;
	}


	public void setSeverity(String severity) {
		this.severity = severity;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}
	
	
	
	
	
	
	
	
	
	
}
