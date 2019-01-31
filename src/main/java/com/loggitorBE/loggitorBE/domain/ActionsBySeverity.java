package com.loggitorBE.loggitorBE.domain;

public class ActionsBySeverity {
	// Pojo class that define the data in the actions by severity table

	
	String Severity;
	int SevCount;
	double SevPercentage;
	
	// default constructor
	public ActionsBySeverity() {}
	
	// constructor
	public ActionsBySeverity(String severity, int sevCount, double sevPercentage) {
		super();
		Severity = severity;
		SevCount = sevCount;
		SevPercentage = sevPercentage;
	}

	public String getSeverity() {
		return Severity;
	}

	public void setSeverity(String severity) {
		Severity = severity;
	}

	public int getSevCount() {
		return SevCount;
	}

	public void setSevCount(int sevCount) {
		SevCount = sevCount;
	}

	public double getSevPercentage() {
		return SevPercentage;
	}

	public void setSevPercentage(double sevPercentage) {
		SevPercentage = sevPercentage;
	}
	
	
	

}
