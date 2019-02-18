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
@SqlResultSetMapping(
		name="EventOnDateMapping",
	    classes={
	        @ConstructorResult(
	        		targetClass=EventInstanceOnDate.class,
	            columns={
	            		@ColumnResult(name="ID", type = BigInteger.class),
	                @ColumnResult(name="NAME", type = String.class),
	                @ColumnResult(name="SEVERITY", type = String.class),
	                @ColumnResult(name="DESCRIPTION", type = String.class),
	                @ColumnResult(name="ACTION_NAME", type = String.class)
	            }
	        )
	    }
	)

@NamedNativeQuery(name="EventInstance.getEventInsTable", query="SELECT event_instance.id, defined_event.name, event_severity.severity, defined_event.description, fix_action.action_name " + 
		"FROM event_instance, defined_event, event_severity, fix_action " + 
		"WHERE (event_instance.date = :date) AND (event_instance.occurred_event = defined_event.id) " + 
		"AND (defined_event.event_sev = event_severity.id) " + 
		"AND (defined_event.fix_action = fix_action.id)"
		+ "LIMIT (:limit) OFFSET (:offset)", resultSetMapping="EventOnDateMapping")

/////////////////////////////////////////////////Weekly diagram queries/////////////////////////////////////////////////////////
@SqlResultSetMapping(
name="WeeklyWarning",
classes={
@ConstructorResult(
targetClass=WeeklyDiagram.class,
columns={
@ColumnResult(name="WARNING", type = int.class)
}
)
}
)

@NamedNativeQuery(name="EventInstance.getWeeklyWarning", query="SELECT count(EI.ID) AS WARNING"
+" FROM DEFINED_EVENT AS DE"
+" INNER JOIN EVENT_INSTANCE AS EI ON DE.ID=EI.OCCURRED_EVENT"
+" INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV=ES.ID"
+" WHERE ES.SEVERITY='Warning'"
+" GROUP BY date_trunc('week', date)"
+" ORDER BY date_trunc('week', date) DESC", resultSetMapping="WeeklyWarning")

@SqlResultSetMapping(
name="WeeklyCritical",
classes={
@ConstructorResult(
targetClass=WeeklyDiagram.class,
columns={
@ColumnResult(name="CRITICAL", type = int.class)
}
)
}
)

@NamedNativeQuery(name="EventInstance.getWeeklyCritical", query="SELECT count(EI.ID) AS CRITICAL"
+" FROM DEFINED_EVENT AS DE"
+" INNER JOIN EVENT_INSTANCE AS EI ON DE.ID=EI.OCCURRED_EVENT"
+" INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV=ES.ID"
+" WHERE ES.SEVERITY='Critical'"
+" GROUP BY date_trunc('week', date)"
+" ORDER BY date_trunc('week', date) DESC", resultSetMapping="WeeklyCritical")

@SqlResultSetMapping(
name="WeeklyError",
classes={
@ConstructorResult(
targetClass=WeeklyDiagram.class,
columns={
@ColumnResult(name="ERROR", type = int.class)
}
)
}
)

@NamedNativeQuery(name="EventInstance.getWeeklyError", query="SELECT count(EI.ID) AS ERROR"
+" FROM DEFINED_EVENT AS DE"
+" INNER JOIN EVENT_INSTANCE AS EI ON DE.ID=EI.OCCURRED_EVENT"
+" INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV=ES.ID"
+" WHERE ES.SEVERITY='Error'"
+" GROUP BY date_trunc('week', date)"
+" ORDER BY date_trunc('week', date) DESC", resultSetMapping="WeeklyError")

public class EventInstance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private Date date;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "occurredEvent")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	DefinedEvent occurredEvent;
	
	//empty constructor
	public EventInstance() {}



	public EventInstance(Date date) {
		super();
		this.date = date;
	}

	public EventInstance(Date date, DefinedEvent occurredEvent) {
		super();
		this.date = date;
		this.occurredEvent = occurredEvent;
	}
	
	//getters and setters


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
