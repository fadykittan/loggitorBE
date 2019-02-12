package com.loggitorBE.loggitorBE.domain;



import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventSeverityRepo extends CrudRepository<EventSeverity, Long> {

	

	@Query(value = "select e.id from event_severity e where e.severity = ?1",nativeQuery = true)
	ArrayList<BigInteger> findByEvSeverity(String severity);

	//declaring a method that create an actions by severity table
	@Query(nativeQuery = true)
	ArrayList<ActionsBySeverity> getActionsBySeverity(Date date, int limit, int offset);

	
	// get the event severity list
	@Query(nativeQuery = true)
	ArrayList<EventSevList> getEventsSev();

}
