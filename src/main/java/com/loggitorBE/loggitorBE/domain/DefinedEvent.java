package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

/*@SqlResultSetMapping(name = "CostumeEvents", classes = {
		@ConstructorResult(targetClass = EventsResult.class, columns = {
				@ColumnResult(name = "ID", type = BigInteger.class),
				@ColumnResult(name = "NAME", type = String.class),
				@ColumnResult(name = "DEFECT_SEVERITY", type = String.class),
				@ColumnResult(name = "COMPERATOR", type = String.class),
				@ColumnResult(name = "PERCENT", type = int.class),
				@ColumnResult(name = "SEVERITY", type = String.class),
				@ColumnResult(name = "ACTION_NAME", type = String.class),
				@ColumnResult(name = "DESCRIPTION", type = String.class) }) })

@NamedNativeQuery(name = "DefinedEvent.getEventsResult", query = "SELECT DE.ID, A.NAME, DS.DEFECT_SEVERITY, DE.COMPERATOR, DE.PERCENT, ES.SEVERITY, FA.ACTION_NAME, DE.DESCRIPTION "
		+ "FROM DEFINED_EVENT AS DE" 
		+ " INNER JOIN APP AS A ON DE.APP = A.ID"
		+ " INNER JOIN DEFECT_SEVERITY AS DS ON DE.DEFECT_SEV = DS.ID"
		+ " INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV = ES.ID"
		+ " INNER JOIN FIX_ACTION AS FA ON DE.FIX_ACTION = FA.ID", resultSetMapping = "CostumeEvents")*/

@SqlResultSetMapping(name = "CostumeEvents", classes = {
		@ConstructorResult(targetClass = EventsResult.class, columns = {
				@ColumnResult(name = "ID", type = BigInteger.class),
				@ColumnResult(name = "NAME", type = String.class),
				@ColumnResult(name = "DEFECT_SEVERITY", type = String.class),
				@ColumnResult(name = "COMPERATOR", type = String.class),
				@ColumnResult(name = "PERCENT", type = int.class),
				@ColumnResult(name = "SEVERITY", type = String.class),
				@ColumnResult(name = "ACTION_NAME", type = String.class),
				@ColumnResult(name = "DESCRIPTION", type = String.class), 
				@ColumnResult(name = "SOLUTION", type = String.class)}) })

@NamedNativeQuery(name = "DefinedEvent.getEventsResult", query = "INSERT INTO CostumeEvents (ID, NAME, DEFECT_SEVERITY,COMPERATOR,PERCENT,SEVERITY,ACTION_NAME,DESCRIPTION,'solution ..')"
         + "SELECT DE.ID, A.NAME, DS.DEFECT_SEVERITY, DE.COMPERATOR, DE.PERCENT, ES.SEVERITY, FA.ACTION_NAME, DE.DESCRIPTION "
		+ "FROM DEFINED_EVENT AS DE" 
		+ " INNER JOIN APP AS A ON DE.APP = A.ID"
		+ " INNER JOIN DEFECT_SEVERITY AS DS ON DE.DEFECT_SEV = DS.ID"
		+ " INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV = ES.ID"
		+ " INNER JOIN FIX_ACTION AS FA ON DE.FIX_ACTION = FA.ID", resultSetMapping = "CostumeEvents")


public class DefinedEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int percent;

	private String comperator;
	private String name;
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fixAction")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private FixAction fixAction;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "defectSev")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private DefectSeverity defectSev;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventSev")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private EventSeverity eventSev;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "app")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private App app;

	@OneToMany(mappedBy = "occurredEvent")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Set<EventInstance> eventInstances = new HashSet<EventInstance>();

	// empty constructor
	public DefinedEvent() {
	}

	public DefinedEvent(int percent, String comperator, String name, String description) {
		super();
		this.percent = percent;
		this.comperator = comperator;
		this.name = name;
		this.description = description;
	}

	public DefinedEvent(int percent, String comperator, String name, String description, FixAction fixAction,
			DefectSeverity defectSev, EventSeverity eventSev, App app, Set<EventInstance> eventInstances) {
		super();
		this.percent = percent;
		this.comperator = comperator;
		this.name = name;
		this.description = description;
		this.fixAction = fixAction;
		this.defectSev = defectSev;
		this.eventSev = eventSev;
		this.app = app;
		this.eventInstances = eventInstances;
	}

	// getters and setters
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

	public FixAction getFixAction() {
		return fixAction;
	}

	public void setFixAction(FixAction fixAction) {
		this.fixAction = fixAction;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DefectSeverity getDefectSev() {
		return defectSev;
	}

	public void setDefectSev(DefectSeverity defectSev) {
		this.defectSev = defectSev;
	}

	public EventSeverity getEventSev() {
		return eventSev;
	}

	public void setEventSev(EventSeverity eventSev) {
		this.eventSev = eventSev;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public Set<EventInstance> getEventInstances() {
		return eventInstances;
	}

	public void setEventInstances(Set<EventInstance> eventInstances) {
		this.eventInstances = eventInstances;
	}

	public void setEventInstance(EventInstance eventInstance) {
		this.eventInstances.add(eventInstance);
	}

}
