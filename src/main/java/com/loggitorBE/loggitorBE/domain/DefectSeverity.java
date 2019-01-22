package com.loggitorBE.loggitorBE.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DefectSeverity {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String severity;
	
    //private List<DefinedEvent> definedEvents = new ArrayList<DefinedEvent>();
	
	// empty constructor
	public DefectSeverity() {}

	public DefectSeverity(String severity) {
		super();
		this.severity = severity;
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
	




	
	
	
}
