package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SqlResultSetMapping(
		name="ActionLogMapping",
	    classes={
	        @ConstructorResult(
	        		targetClass=ActionLog.class,
	            columns={
		            @ColumnResult(name="ID", type = BigInteger.class),
	                @ColumnResult(name="NAME", type = String.class),
	                @ColumnResult(name="SEVERITY", type = String.class),
	                @ColumnResult(name="DESC", type = String.class),
	                @ColumnResult(name="ACTION_NAME", type = String.class)
	            }
	        )
	    }
	)

@NamedNativeQuery(name="Event_Instance.getActionLogTable", query="SELECT "
		+ "( defined_events.name, defect_severity.severity, defined_events.desc, action.action_name, event_instance.id ) " + 
		"FROM ( event_instance , defined_events , defect_severity , action )" + 
		"WHERE ((( event_instance.date ) = :date ) " + 
		"AND event_instance.occurred_event = defined_events.id " + 
		"AND defined_events.defect_sev = defect_severity.id " + 
		"AND defined_events.action = action.id )", resultSetMapping="ActionLogMapping")
public class Event_Instance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String date;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	Event occurred_event;
	
	//empty constructor
	public Event_Instance() {}



	public Event_Instance(String date, Event event) {
		super();
		this.date = date;
		this.occurred_event = event;
	}



	//getters and setters
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public Event getOccurred_event() {
		return occurred_event;
	}



	public void setOccurred_event(Event occurred_event) {
		this.occurred_event = occurred_event;
	}




	
	
	
	
	
	
	
}
