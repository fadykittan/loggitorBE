package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;

public class EventsResult {

	private BigInteger id;
	private String appName;
	private String defSeverity;
	private String comperator;
	private Double percent;
	private String eventName;
	private String eventSeverity;
	private String actionName;
	private String description;


	public EventsResult() {
	}


	public EventsResult(BigInteger id, String appName, String defSeverity, String comperator, Double percent,
			String eventName,String eventSeverity, String actionName, String description) {

		super();
		this.id = id;
		this.appName = appName;
		this.defSeverity = defSeverity;
		this.comperator = comperator;
		this.percent = percent;
		this.eventName = eventName;
		this.eventSeverity = eventSeverity;
		this.actionName = actionName;
		this.description = description;
	}


	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
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


	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
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
