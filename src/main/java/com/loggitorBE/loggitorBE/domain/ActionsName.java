package com.loggitorBE.loggitorBE.domain;

public class ActionsName {
	
	// pojo class that define the list of actions names
	String Name;
	
	public ActionsName() {}
	
	public ActionsName(String Name)
	{
		this.Name=Name;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
