package com.loggitorBE.loggitorBE.domain;

import java.sql.Date;

public class WeeklyDiagram {
	String Week;
	String Critical;
	String Warning;
	String Error;
	
	
	
	public WeeklyDiagram() {}
	
	public WeeklyDiagram(String Week, String Critical, String Warning, String Error) {
		super();
		this.Week = Week;
		this.Critical = Critical;
		this.Warning = Warning;
		this.Error = Error;
	}


	public String getWeek() {
		return Week;
	}


	public void setWeek(String Week) {
		this.Week = Week;
	}


	public String getCritical() {
		return Critical;
	}


	public void setCritical(String Critical) {
		this.Critical = Critical;
	}


	public String getWarning() {
		return Warning;
	}


	public void setWarning(String Warning) {
		this.Warning = Warning;
	}


	public String getError() {
		return Error;
	}


	public void setError(String Error) {
		this.Error = Error;
	}
	
	
	

	

}