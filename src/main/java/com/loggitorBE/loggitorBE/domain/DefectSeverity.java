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
//a query that create a costume table that shows the severity,how much it occurred , and the percentage of it from the whole table 
@SqlResultSetMapping(
		name="ActionsBySeverityCostume",
	    classes={
	        @ConstructorResult(
	        		targetClass=ActionsBySeverity.class,
	            columns={
	                
	                @ColumnResult(name="DEFECT_SEVERITY", type = String.class),
	                @ColumnResult(name="SEVERITY_COUNTER", type = int.class),
	                @ColumnResult(name="PERCENT", type = double.class)
	                
	            }
	        )
	    }
	)

@NamedNativeQuery(name="DefectSeverity.getActionsBySeverity", query="SELECT DEFECT_SEVERITY,COUNT(DEFECT_SEVERITY) AS SEVERITY_COUNTER,(COUNT(DEFECT_SEVERITY)*100/(SELECT COUNT(*) FROM DEFECT_SEVERITY)) AS PERCENT"
		+" FROM DEFECT_SEVERITY"
		+" GROUP BY DEFECT_SEVERITY",resultSetMapping="ActionsBySeverityCostume")
public class DefectSeverity {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	@Column(name="defect_severity")
	String severity;
	
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
