package com.loggitorBE.loggitorBE.domain;

public class ActionsByApp {
	// Pojo class that define the data in the actions by application table
	String appName;
	
	int appCount;
	double percentage;
	
	
	// default constructor
	public ActionsByApp() {}
	
	// constructor
	public ActionsByApp(String appName, int appCount,double percentage) {
		
		super();
		this.appName=appName;
		
		this.appCount=appCount;
		this.percentage=percentage;
		
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getAppCount() {
		return appCount;
	}

	public void setAppCount(int appCount) {
		this.appCount = appCount;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	


}
