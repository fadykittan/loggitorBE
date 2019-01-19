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
		+ "EVENT.NAME, DEFECT_SEVERITY.SEVERITY, EVENT.DESC, ACTION.ACTION_NAME, EVENT_INSTANCE.ID  \r\n" + 
		"FROM EVENT_INSTANCE , EVENT , DEFECT_SEVERITY , ACTION \r\n" + 
		"WHERE EVENT_INSTANCE.DATE = :date\r\n" + 
		"AND EVENT_INSTANCE.EVENT = EVENT.ID \r\n" + 
		"AND EVENT.DEFECT_SEV =DEFECT_SEVERITY.ID \r\n" + 
		"AND EVENT.ACTION = ACTION.ID ", resultSetMapping="ActionLogMapping")
public class Event_Instance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String date;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	Event event;
	
	//empty constructor
	public Event_Instance() {}



	public Event_Instance(String date, Event event) {
		super();
		this.date = date;
		this.event = event;
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



	public Event getEvent() {
		return event;
	}



	public void setEvent(Event event) {
		this.event = event;
	}
	
	
	
	
	
	
}
