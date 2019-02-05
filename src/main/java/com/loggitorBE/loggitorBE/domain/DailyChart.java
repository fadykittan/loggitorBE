package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;

/*
 * POJO Class
 */
public class DailyChart {

	private BigInteger id;
	private String name;
	private String type;
	private String severity;
	private float percent;
	

	//empty constructor 
	public DailyChart() {}

	//constructor
	public DailyChart(BigInteger id, String name, String type, String severity, float percent) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.severity = severity;
		this.percent = percent;
	}

	//getters and setters 
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}
	
	
	
	
}
