package com.loggitorBE.loggitorBE.domain;

import javax.persistence.*;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@SqlResultSetMapping(
		name="CostumeEvents",
	    classes={
	        @ConstructorResult(
	        		targetClass=EventsResult.class,
	            columns={
	                @ColumnResult(name="ID", type = BigInteger.class),
	                @ColumnResult(name="NAME", type = String.class),
	                @ColumnResult(name="DEFECT_SEVERITY", type = String.class),
	                @ColumnResult(name="COMPERATOR", type = String.class),
	                @ColumnResult(name="PERCENT", type = int.class),
	                @ColumnResult(name="SEVERITY", type = String.class),
	                @ColumnResult(name="ACTION_NAME", type = String.class),
	                @ColumnResult(name="DESCRIPTION", type = String.class)
	            }
	        )
	    }
	)

@NamedNativeQuery(name="DefectSeverity.getEventsResult", query="SELECT DE.ID, A.NAME, DS.DEFECT_SEVERITY, DE.COMPERATOR, DE.PERCENT, ES.SEVERITY, FA.ACTION_NAME, DE.DESCRIPTION "
		+"FROM DEFINED_EVENT AS DE"
		+" INNER JOIN APP AS A ON DE.APP=A.ID"
		+" INNER JOIN DEFECT_SEVERITY AS DS ON DE.DEFECT_SEV=DS.ID"
		+" INNER JOIN EVENT_SEVERITY AS ES ON DE.EVENT_SEV=ES.ID"
		+" INNER JOIN FIX_ACTION AS FA ON DE.FIX_ACTION=FA.ID",resultSetMapping="CostumeEvents")

/*@NamedNativeQuery(name="DefectSeverity.getEventsResult", query="select de.id, Dds.severity, de.comperator, de.percent, es.severity, fa.action_name, de.description "
		+"FROM defined_event AS de"
		+"INNER JOIN defect_severity AS ds on de.defect_sev=ds.id"
		+"INNER JOIN event_severity AS es on de.event_sec=es.id"
		+"INNER JOIN fix_action AS fa on de.fix_action=fa.id",resultSetMapping="CostumeEvents")
*/
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
