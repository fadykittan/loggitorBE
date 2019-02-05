package com.loggitorBE.loggitorBE.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
//a query that pull the defect severities from the database
@SqlResultSetMapping(
		name="DefSevCostume",
	    classes={
	        @ConstructorResult(
	        		targetClass=DefectSevApi.class,
	            columns={
	                
	                @ColumnResult(name="DEFECT_SEVERITY", type = String.class)
	               
	            }
	        )
	    }
	)

@NamedNativeQuery(name="DefectSeverity.getDefectsSev", query="SELECT DISTINCT DEFECT_SEVERITY.DEFECT_SEVERITY"
		+" FROM DEFECT_SEVERITY"
		+" ORDER BY DEFECT_SEVERITY.DEFECT_SEVERITY DESC;",resultSetMapping="DefSevCostume")

public class DefectSeverity {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="defect_severity")
	private String severity;

	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy="defectSev")
	@OneToMany(mappedBy="defectSev")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private Set<DefinedEvent> definedEvents = new HashSet<DefinedEvent>();
	
	// empty constructor
	public DefectSeverity() {}

	public DefectSeverity(String severity) {
		super();
		this.severity = severity;
	}


	public DefectSeverity(String severity, Set<DefinedEvent> definedEvents) {
		super();
		this.severity = severity;
		this.definedEvents = definedEvents;
	}

	public DefectSeverity(String severity,DefinedEvent definedEvent) {
		super();
		this.severity = severity;
		this.definedEvents.add(definedEvent);
	}
	
	//setters and getters
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
