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

@SqlResultSetMapping(name = "CostumeEvents", classes = {
		@ConstructorResult(targetClass = EventsResult.class, columns = {
				@ColumnResult(name = "ID", type = BigInteger.class),
				@ColumnResult(name = "NAME", type = String.class),
				@ColumnResult(name = "DEFECT_SEVERITY", type = String.class),
				@ColumnResult(name = "COMPERATOR", type = String.class),

				@ColumnResult(name = "PERCENT", type = Double.class),
				@ColumnResult(name = "TITLE", type = String.class),

				@ColumnResult(name = "SEVERITY", type = String.class),
				@ColumnResult(name = "ACTION_NAME", type = String.class),
				@ColumnResult(name = "DESCRIPTION", type = String.class),
				@ColumnResult(name = "USERNAME", type = String.class),
				@ColumnResult(name = "MSG", type = String.class)}) })

@NamedNativeQuery(name = "DefinedEvent.getEventsResult", query = "SELECT DE.ID, CONCAT(A.NAME, '-', A.TYPE) AS NAME , DS.DEFECT_SEVERITY, DE.COMPERATOR, DE.PERCENT,DE.NAME AS TITLE, ES.SEVERITY, FA.ACTION_NAME, DE.DESCRIPTION, U.EMAIL AS USERNAME, DE.MSG " + 
		"FROM DEFINED_EVENT AS DE " + 
		"INNER JOIN APP AS A ON DE.APP = A.ID " + 
		"INNER JOIN DEFECT_SEVERITY AS DS ON DE.DEFECT_SEV = DS.ID " + 
		"INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV = ES.ID " + 
		"INNER JOIN FIX_ACTION AS FA ON DE.FIX_ACTION = FA.ID " + 
		"INNER JOIN USERS AS U ON DE.USER_ID = U.USER_ID ", resultSetMapping = "CostumeEvents")

/*
@NamedNativeQuery(name = "DefinedEvent.getEventsResult", query = "SELECT DE.ID, CONCAT(A.NAME, '-', A.TYPE) AS NAME , DS.DEFECT_SEVERITY, DE.COMPERATOR, DE.PERCENT,DE.NAME AS TITLE, ES.SEVERITY, FA.ACTION_NAME, DE.DESCRIPTION "
		+ "FROM DEFINED_EVENT AS DE" 
		+ " INNER JOIN APP AS A ON DE.APP = A.ID"
		+ " INNER JOIN DEFECT_SEVERITY AS DS ON DE.DEFECT_SEV = DS.ID"
		+ " INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV = ES.ID"
		+ " INNER JOIN FIX_ACTION AS FA ON DE.FIX_ACTION = FA.ID", resultSetMapping = "CostumeEvents")*/
///////////////////////// SQL for the chart //////////////////////////
@SqlResultSetMapping(name = "DailyChart", classes = {
		@ConstructorResult(targetClass = DailyChart.class, columns = {
				@ColumnResult(name = "ID", type = BigInteger.class),
				@ColumnResult(name = "NAME", type = String.class),
				@ColumnResult(name = "TYPE", type = String.class),
				@ColumnResult(name = "SEVERITY", type = String.class),
				@ColumnResult(name = "PERCENT", type = Float.class)
			 }) })

@NamedNativeQuery(name = "DefinedEvent.getDailyChart", query = "SELECT APP.ID, APP.NAME, APP.TYPE, EVENT_SEVERITY.SEVERITY, " + 
		"(COUNT(APP.ID)*100 / (SELECT COUNT(*) FROM EVENT_INSTANCE WHERE EVENT_INSTANCE.DATE= :date)) AS PERCENT  " + 
		"FROM DEFINED_EVENT " + 
		"INNER JOIN EVENT_INSTANCE ON DEFINED_EVENT.ID=EVENT_INSTANCE.OCCURRED_EVENT " + 
		"INNER JOIN APP ON DEFINED_EVENT.APP=APP.ID " + 
		"INNER JOIN EVENT_SEVERITY ON DEFINED_EVENT.EVENT_SEV = EVENT_SEVERITY.ID " + 
		"WHERE EVENT_INSTANCE.DATE = :date " + 
		"GROUP BY APP.ID, EVENT_SEVERITY.ID " + 
		"LIMIT (:limit) OFFSET (:offset) ", resultSetMapping = "DailyChart")
/////////////////////////////////////////// SQL getAllDefinedEvent ////////
@SqlResultSetMapping(name = "AllEventMapping", classes = {
		@ConstructorResult(targetClass = DefinedEventPOJO.class, columns = {
				@ColumnResult(name = "ID", type = BigInteger.class),
				@ColumnResult(name = "COMPERATOR", type = String.class),
				@ColumnResult(name = "PERCENT", type = Float.class),
				@ColumnResult(name = "APPID", type = BigInteger.class),
				@ColumnResult(name = "APPNAME", type = String.class),
				@ColumnResult(name = "DEFID", type = BigInteger.class),
				@ColumnResult(name = "DEFECT_SEVERITY", type = String.class),
				@ColumnResult(name = "ACTIONID", type = BigInteger.class),
				@ColumnResult(name = "ACTION_NAME", type = String.class)
				
			 }) })

@NamedNativeQuery(name = "DefinedEvent.getAllDefinedEvent", query = "SELECT DEFINED_EVENT.ID, DEFINED_EVENT.COMPERATOR, DEFINED_EVENT.PERCENT, "
		+ "APP.ID AS APPID, APP.NAME AS APPNAME, APP.TYPE AS APPTYPE, "
		+ "DEFECT_SEVERITY.ID AS DEFID, DEFECT_SEVERITY.DEFECT_SEVERITY, FIX_ACTION.ID AS ACTIONID, FIX_ACTION.ACTION_NAME " + 
		"FROM DEFINED_EVENT, APP, DEFECT_SEVERITY, FIX_ACTION " + 
		"WHERE DEFINED_EVENT.APP = APP.ID " + 
		"AND DEFINED_EVENT.DEFECT_SEV = DEFECT_SEVERITY.ID " + 
		"AND DEFINED_EVENT.FIX_ACTION = FIX_ACTION.ID ", resultSetMapping = "AllEventMapping")
public class DefinedEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	private Double percent;


	private String comperator;
	private String name;
	private String description;
	private BigInteger userId;
	private String msg;
	

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

	public DefinedEvent(Double percent, String comperator, String name, String description) {
		super();
		this.percent = percent;
		this.comperator = comperator;
		this.name = name;
		this.description = description;
	}

	public DefinedEvent(Double percent, String comperator, String name, String description, FixAction fixAction,
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
	
	
	public DefinedEvent(Double percent, String comperator, String name, String description, FixAction fixAction,
			DefectSeverity defectSev, EventSeverity eventSev, App app) {
		super();
		this.percent = percent;
		this.comperator = comperator;
		this.name = name;
		this.description = description;
		this.fixAction = fixAction;
		this.defectSev = defectSev;
		this.eventSev = eventSev;
		this.app = app;
	}
	
	
	

	public DefinedEvent(Double percent, String comperator, String name, String description, BigInteger userId,
			String msg, FixAction fixAction, DefectSeverity defectSev, EventSeverity eventSev, App app) {
		super();
		this.percent = percent;
		this.comperator = comperator;
		this.name = name;
		this.description = description;
		this.userId = userId;
		this.msg = msg;
		this.fixAction = fixAction;
		this.defectSev = defectSev;
		this.eventSev = eventSev;
		this.app = app;
	}

	// getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
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

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
	

}
