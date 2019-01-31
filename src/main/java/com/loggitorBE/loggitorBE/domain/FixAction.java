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

// a query that pull the names of actions from the database
@SqlResultSetMapping(
		name="ActionsCostume",
	    classes={
	        @ConstructorResult(
	        		targetClass=ActionsName.class,
	            columns={
	                
	                @ColumnResult(name="ACTION_NAME", type = String.class)
	               
	            }
	        )
	    }
	)

@NamedNativeQuery(name="FixAction.getActionsName", query="SELECT DISTINCT FIX_ACTION.ACTION_NAME"
		+" FROM FIX_ACTION"
		+" ORDER BY FIX_ACTION.ACTION_NAME DESC;",resultSetMapping="ActionsCostume")


public class FixAction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String actionName;

	//@OneToMany(cascade = CascadeType.ALL, mappedBy="fixAction")
	@OneToMany(mappedBy="fixAction")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private Set<DefinedEvent> definedEvents = new HashSet<DefinedEvent>();

	// empty constructor
	public FixAction() {
	}

	public FixAction(String actionName) {
		super();
		this.actionName = actionName;
	}


	public FixAction(String actionName, Set<DefinedEvent> definedEvents) {
		super();
		this.actionName = actionName;
		this.definedEvents = definedEvents;
	}

	public FixAction(String actionName, DefinedEvent definedEvent) {
		super();
		this.actionName = actionName;
		this.definedEvents.add(definedEvent);
	}
	
	// setters and getters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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
