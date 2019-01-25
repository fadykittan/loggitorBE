package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;

public class EventsResult {
	
	BigInteger  id;
	String appName;
	String defSeverity;
	String comperator;
	int percent;
	String eventSeverity;
	String actionName;
	String description;
	
	public EventsResult() {}
	
	public EventsResult(BigInteger  id, String appName, String defSeverity, String comperator, int percent,
			String eventSeverity, String actionName, String description) {
		super();
		this.id = id;
		this.appName = appName;
		this.defSeverity = defSeverity;
		this.comperator = comperator;
		this.percent = percent;
		this.eventSeverity = eventSeverity;
		this.actionName = actionName;
		this.description = description;
	}
	
	
	public EventsResult(String appName, String defSeverity, String comperator, int percent,
			String eventSeverity, String actionName, String description) {
		super();
		this.appName = appName;
		this.defSeverity = defSeverity;
		this.comperator = comperator;
		this.percent = percent;
		this.eventSeverity = eventSeverity;
		this.actionName = actionName;
		this.description = description;
	}
	
	
	
	public BigInteger  getId() {
		return id;
	}
	public void setId(BigInteger  id) {
		this.id = id;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getDefSeverity() {
		return defSeverity;
	}
	public void setDefSeverity(String defSeverity) {
		this.defSeverity = defSeverity;
	}
	public String getComperator() {
		return comperator;
	}
	public void setComperator(String comperator) {
		this.comperator = comperator;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public String getEventSeverity() {
		return eventSeverity;
	}
	public void setEventSeverity(String eventSeverity) {
		this.eventSeverity = eventSeverity;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
