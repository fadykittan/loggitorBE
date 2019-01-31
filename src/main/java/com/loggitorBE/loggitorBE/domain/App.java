package com.loggitorBE.loggitorBE.domain;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


import java.math.BigInteger;


// creating a query that pull the names of the applications from the app table

@Entity
@SqlResultSetMapping(
		name="AppNamesCostume",
	    classes={
	        @ConstructorResult(
	        		targetClass=AppsNames.class,
	            columns={
	                
	                @ColumnResult(name="NAME", type = String.class)
	               
	            }
	        )
	    }
	)

@NamedNativeQuery(name="App.getAppsNames", query="SELECT DISTINCT APP.NAME"
		+" FROM APP"
		+" ORDER BY APP.NAME DESC;",resultSetMapping="AppNamesCostume")

// creating a costume table that counts and calculate the percentage of the actions by applications

@SqlResultSetMapping(
		name="ActionsByAppCostume",
	    classes={
	        @ConstructorResult(
	        		targetClass=ActionsByApp.class,
	            columns={
	                
	                @ColumnResult(name="NAME", type = String.class),
	                @ColumnResult(name="APP_COUNTER", type = int.class),
	                @ColumnResult(name="PERCENT", type = double.class)
	                
	            }
	        )
	    }
	)

@NamedNativeQuery(name="App.getActionsByApp", query="SELECT NAME,COUNT(NAME) AS APP_COUNTER,(COUNT(NAME)*100/(SELECT COUNT(*) FROM APP)) AS PERCENT"
		+" FROM APP"
		+" GROUP BY NAME",resultSetMapping="ActionsByAppCostume")
		
public class App {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String name;
	String type;
	
	@OneToMany(mappedBy="app")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private Set<DefinedEvent> definedEvents = new HashSet<DefinedEvent>();
	
	//empty constructor
	public App() {}



	public App(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	
	

	public App(String name, String type, Set<DefinedEvent> definedEvents) {
		super();
		this.name = name;
		this.type = type;
		this.definedEvents = definedEvents;
	}



	//getters and setters

	public long getId() {
		return id;
	}



	public void setId(long id) {
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



	public Set<DefinedEvent> getDefinedEvents() {
		return definedEvents;
	}



	public void setDefinedEvents(Set<DefinedEvent> definedEvents) {
		this.definedEvents = definedEvents;
	}


	public void setDefinedEvent(DefinedEvent definedEvent) {
		this.definedEvents.add(definedEvent);
	}



	
}
