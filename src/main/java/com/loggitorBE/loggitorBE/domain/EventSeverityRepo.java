package com.loggitorBE.loggitorBE.domain;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventSeverityRepo extends CrudRepository<EventSeverity, Long> {

	
	//declaring a method that create an actions by severity table
	@Query(nativeQuery = true)
	ArrayList<ActionsBySeverity> getActionsBySeverity(String date, int limit, int offset);

	
	// get the event severity list
	@Query(nativeQuery = true)
	ArrayList<EventSevList> getEventsSev();

}
