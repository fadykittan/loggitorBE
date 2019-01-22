package com.loggitorBE.loggitorBE.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DefinedEvent {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int percent;
	
	private String comperator;
	private String name;
	private String description;

	/*
	App app;
	

	DefectSeverity defect_sev;
	

	FixAction fixAction;
    

	EventSeverity event_sev;
	
	 */
	

    //private List<Event_Instance> event_instances = new ArrayList<Event_Instance>();
	
	
	//empty constructor
	public DefinedEvent() {}

	public DefinedEvent(int percent, String comperator, String name, String description) {
		super();
		this.percent = percent;
		this.comperator = comperator;
		this.name = name;
		this.description = description;
	}

	
	//getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public String getComperator() {
		return comperator;
	}

	public void setComperator(String comperator) {
		this.comperator = comperator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDesc(String description) {
		this.description = description;
	}








	
	
}
