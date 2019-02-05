package com.loggitorBE.loggitorBE.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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
	                @ColumnResult(name="TYPE", type = String.class),
	                @ColumnResult(name="APP_COUNTER", type = Integer.class),
	                @ColumnResult(name="PERCENT", type = Float.class)
	                
	            }
	        )
	    }
	)

@NamedNativeQuery(name="App.getActionsByApp", query="SELECT APP.NAME,APP.TYPE,COUNT(DEFINED_EVENT.APP) AS APP_COUNTER,(COUNT(DEFINED_EVENT.APP)*100/(SELECT COUNT(*) FROM EVENT_INSTANCE WHERE EVENT_INSTANCE.DATE= :date)) AS PERCENT " + 
		"FROM DEFINED_EVENT " + 
		"INNER JOIN APP ON DEFINED_EVENT.APP=APP.ID " + 
		"INNER JOIN EVENT_INSTANCE ON DEFINED_EVENT.ID= EVENT_INSTANCE.OCCURRED_EVENT " + 
		"WHERE EVENT_INSTANCE.DATE= (:date) " + 
		"GROUP BY DEFINED_EVENT.APP,APP.NAME,APP.TYPE " + 
		"LIMIT (:limit) OFFSET (:offset) ",resultSetMapping="ActionsByAppCostume")
		
public class App {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String type;
	
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
