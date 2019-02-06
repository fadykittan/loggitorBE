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




@Entity
//a query that create a costume table that shows the severity,how much it occurred , and the percentage of it from the whole table 
@SqlResultSetMapping(
		name="ActionsBySeverityCostume",
	    classes={
	        @ConstructorResult(
	        		targetClass=ActionsBySeverity.class,
	            columns={
	                
	                @ColumnResult(name="SEVERITY", type = String.class),
	                @ColumnResult(name="SEV_COUNTER", type = int.class),
	                @ColumnResult(name="PERCENT", type = String.class)
	                
	            }
	        )
	    }
	)

@NamedNativeQuery(name="EventSeverity.getActionsBySeverity", query="SELECT EVENT_SEVERITY.SEVERITY,COUNT(DEFINED_EVENT.EVENT_SEV) AS SEV_COUNTER,CONCAT((COUNT(DEFINED_EVENT.EVENT_SEV)*100/(SELECT COUNT(*) FROM EVENT_INSTANCE WHERE EVENT_INSTANCE.DATE= (:date) )),'%') AS PERCENT  " + 
		"FROM DEFINED_EVENT " + 
		"INNER JOIN EVENT_SEVERITY ON DEFINED_EVENT.EVENT_SEV=EVENT_SEVERITY.ID " + 
		"INNER JOIN EVENT_INSTANCE ON DEFINED_EVENT.ID=EVENT_INSTANCE.OCCURRED_EVENT " + 
		"WHERE EVENT_INSTANCE.DATE= (:date) " + 
		"GROUP BY EVENT_SEVERITY.SEVERITY " + 
		"LIMIT (:limit) OFFSET (:offset) ",resultSetMapping="ActionsBySeverityCostume")

///////////////////////////////////////SQL for get the events severity list///////////
@SqlResultSetMapping(
		name="EventSeverityList",
	    classes={
	        @ConstructorResult(
	        		targetClass=EventSevList.class,
	            columns={
	                
	                @ColumnResult(name="SEVERITY", type = String.class)
	                
	            }
	        )
	    }
	)

@NamedNativeQuery(name="EventSeverity.getEventsSev", query="SELECT DISTINCT EVENT_SEVERITY.SEVERITY " + 
		"FROM EVENT_SEVERITY " + 
		"ORDER BY EVENT_SEVERITY.SEVERITY DESC",resultSetMapping="EventSeverityList")

public class EventSeverity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String severity;
	
	
	@OneToMany(mappedBy="eventSev")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private Set<DefinedEvent> definedEvents = new HashSet<DefinedEvent>();
	
	//empty constructor
	public EventSeverity() {}


	public EventSeverity(String severity) {
		super();
		this.severity = severity;
	}

	

	public EventSeverity(String severity, Set<DefinedEvent> definedEvents) {
		super();
		this.severity = severity;
		this.definedEvents = definedEvents;
	}


	//getters and setters
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSeverity() {
		return severity;
	}


	public void setSeverity(String severity) {
		this.severity = severity;
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
