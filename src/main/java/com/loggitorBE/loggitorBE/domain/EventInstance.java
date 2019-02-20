package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;
import java.sql.Date;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@SqlResultSetMapping(name = "EventOnDateMapping", classes = {
		@ConstructorResult(targetClass = EventInstanceOnDate.class, columns = {
				@ColumnResult(name = "ID", type = BigInteger.class), @ColumnResult(name = "NAME", type = String.class),
				@ColumnResult(name = "SEVERITY", type = String.class),
				@ColumnResult(name = "DESCRIPTION", type = String.class),
				@ColumnResult(name = "ACTION_NAME", type = String.class)
				}
		)
	}
)

@NamedNativeQuery(name = "EventInstance.getEventInsTable", query = "SELECT event_instance.id, defined_event.name, event_severity.severity, defined_event.description, fix_action.action_name "
		+ "FROM event_instance, defined_event, event_severity, fix_action "
		+ "WHERE (event_instance.date = :date) AND (event_instance.occurred_event = defined_event.id) "
		+ "AND (defined_event.event_sev = event_severity.id) " + "AND (defined_event.fix_action = fix_action.id)"
		+ "LIMIT (:limit) OFFSET (:offset)", resultSetMapping = "EventOnDateMapping")

/////////////////////////////////////////////////The Query OF The Devil/////////////////////////////////////////////////////////

@SqlResultSetMapping(
		name="WeeklyDiagramMapping",
	    classes={
	        @ConstructorResult(
	        		targetClass=WeeklyDiagram.class,
	            columns={
	                
	                @ColumnResult(name="WEEK", type = String.class),
	                @ColumnResult(name="CRITICAL", type = String.class),
	                @ColumnResult(name="WARNING", type = String.class),
	                @ColumnResult(name="ERROR", type = String.class)
	               
	            }
	        )
	    }
	)

@NamedNativeQuery(name = "EventInstance.getWeeklyDiagram", query ="SELECT Wset.WEEK,Firstset.Critical, Secondset.Warning, Thirdset.Error\r\n" + 
		"FROM( SELECT DISTINCT cast(date_trunc('week', cast(DATE as timestamp)) as date) || ' ' || cast((date_trunc('week', cast(DATE as timestamp))+ cast('6 days' as interval)) as date) AS WEEK\r\n" + 
		"FROM EVENT_INSTANCE\r\n" + 
		") AS Wset\r\n" + 
		"FULL JOIN (\r\n" + 
		"SELECT DISTINCT cast(date_trunc('week', cast(DATE as timestamp)) as date) || ' ' || cast((date_trunc('week', cast(DATE as timestamp))+ cast('6 days' as interval)) as date) AS WEEK, count(EI.ID) AS Critical\r\n" + 
		"FROM DEFINED_EVENT AS DE\r\n" + 
		"INNER JOIN EVENT_INSTANCE AS EI ON DE.ID=EI.OCCURRED_EVENT\r\n" + 
		"INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV=ES.ID\r\n" + 
		"GROUP BY cast(date_trunc('week', cast(DATE as timestamp)) as date) || ' ' || cast((date_trunc('week', cast(DATE as timestamp))+ cast('6 days' as interval)) as date),ES.SEVERITY\r\n" + 
		"having ES.SEVERITY='Critical'\r\n" + 
		") AS Firstset\r\n" + 
		"ON Wset.WEEK=Firstset.WEEK\r\n" + 
		"FULL JOIN (\r\n" + 
		"SELECT DISTINCT cast(date_trunc('week', cast(DATE as timestamp)) as date) || ' ' || cast((date_trunc('week', cast(DATE as timestamp))+ cast('6 days' as interval)) as date) AS WEEK, count(EI.ID) AS Warning\r\n" + 
		"FROM DEFINED_EVENT AS DE\r\n" + 
		"INNER JOIN EVENT_INSTANCE AS EI ON DE.ID=EI.OCCURRED_EVENT\r\n" + 
		"INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV=ES.ID\r\n" + 
		"GROUP BY cast(date_trunc('week', cast(DATE as timestamp)) as date) || ' ' || cast((date_trunc('week', cast(DATE as timestamp))+ cast('6 days' as interval)) as date),ES.SEVERITY\r\n" + 
		"having ES.SEVERITY='Warning'\r\n" + 
		") AS Secondset\r\n" + 
		"on Wset.WEEK=Secondset.WEEK\r\n" + 
		"FULL JOIN (\r\n" + 
		"SELECT DISTINCT cast(date_trunc('week', cast(DATE as timestamp)) as date) || ' ' || cast((date_trunc('week', cast(DATE as timestamp))+ cast('6 days' as interval)) as date) AS WEEK, count(EI.ID) AS Error\r\n" + 
		"FROM DEFINED_EVENT AS DE\r\n" + 
		"INNER JOIN EVENT_INSTANCE AS EI ON DE.ID=EI.OCCURRED_EVENT\r\n" + 
		"INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV=ES.ID\r\n" + 
		"GROUP BY cast(date_trunc('week', cast(DATE as timestamp)) as date) || ' ' || cast((date_trunc('week', cast(DATE as timestamp))+ cast('6 days' as interval)) as date),ES.SEVERITY\r\n" + 
		"having ES.SEVERITY='Error'\r\n" + 
		") AS Thirdset\r\n" + 
		"on Wset.WEEK=Thirdset.WEEK", resultSetMapping = "WeeklyDiagramMapping")

public class EventInstance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "occurredEvent")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	DefinedEvent occurredEvent;

	// empty constructor
	public EventInstance() {
	}

	public EventInstance(Date date) {
		super();
		this.date = date;
	}

	public EventInstance(Date date, DefinedEvent occurredEvent) {
		super();
		this.date = date;
		this.occurredEvent = occurredEvent;
	}

	// getters and setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public DefinedEvent getOccurredEvent() {
		return occurredEvent;
	}

	public void setOccurredEvent(DefinedEvent occurredEvent) {
		this.occurredEvent = occurredEvent;
	}

}
