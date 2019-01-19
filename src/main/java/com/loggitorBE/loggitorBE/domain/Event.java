package com.loggitorBE.loggitorBE.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="defined_events")
public class Event {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "app")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	App app;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "defect_sev")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	Defect_Severity defect_sev;
	
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "action")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
	Action action;
    
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_sev")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	Event_Severity event_sev;
	
	String comperator;
	String name;
	int percent;
	String desc;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="event")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
    private List<Event_Instance> event_instances = new ArrayList<Event_Instance>();
	
	
	//empty constructor
	public Event() {}


	

	public Event(App app, Defect_Severity defect_sev, Action action, Event_Severity event_sev, String comperator,
			String name, int percent, String desc, List<Event_Instance> event_instances) {
		super();
		this.app = app;
		this.defect_sev = defect_sev;
		this.action = action;
		this.event_sev = event_sev;
		this.comperator = comperator;
		this.name = name;
		this.percent = percent;
		this.desc = desc;
		this.event_instances = event_instances;
	}


	


	public Event(App app, Defect_Severity defect_sev, Action action, Event_Severity event_sev, String comperator,
			String name, int percent, String desc) {
		super();
		this.app = app;
		this.defect_sev = defect_sev;
		this.action = action;
		this.event_sev = event_sev;
		this.comperator = comperator;
		this.name = name;
		this.percent = percent;
		this.desc = desc;
	}




	//getters and setters
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public App getApp() {
		return app;
	}


	public void setApp(App app) {
		this.app = app;
	}


	public Defect_Severity getDefect_sev() {
		return defect_sev;
	}


	public void setDefect_sev(Defect_Severity defect_sev) {
		this.defect_sev = defect_sev;
	}


	public Action getAction() {
		return action;
	}


	public void setAction(Action action) {
		this.action = action;
	}


	public Event_Severity getEvent_sev() {
		return event_sev;
	}


	public void setEvent_sev(Event_Severity event_sev) {
		this.event_sev = event_sev;
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


	public int getPercent() {
		return percent;
	}


	public void setPercent(int percent) {
		this.percent = percent;
	}


	public List<Event_Instance> getEvent_instances() {
		return event_instances;
	}


	public void setEvent_instances(List<Event_Instance> event_instances) {
		this.event_instances = event_instances;
	}


	public void setEvent_instances(Event_Instance event_instances) {
		this.event_instances.add(event_instances);
	}


	public String getDesc() {
		return desc;
	}




	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
	
	
}
