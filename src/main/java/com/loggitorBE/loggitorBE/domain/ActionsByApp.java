package com.loggitorBE.loggitorBE.domain;

public class ActionsByApp {
	// Pojo class that define the data in the actions by application table
	String appName;
	String appType;
	int appCount;
	float percentage;
	
	
	// default constructor
	public ActionsByApp() {}

	// constructor
	public ActionsByApp(String appName, String appType, int appCount, float percentage) {
		super();
		this.appName = appName;
		this.appType = appType;
		this.appCount = appCount;
		this.percentage = percentage;
	}

	// getters and setters
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public int getAppCount() {
		return appCount;
	}

	public void setAppCount(int appCount) {
		this.appCount = appCount;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	
	
	
	
	
	
	


}
