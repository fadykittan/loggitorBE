package com.loggitorBE.loggitorBE.domain;


import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface EventInstanceRepo extends CrudRepository<EventInstance, Long>{

	
	@Query(nativeQuery = true)
	ArrayList<EventInstanceOnDate> getEventInsTable(@Param("date") Date date,
			@Param("limit") int limit,
			@Param("offset") int offset);
	
	
	
	
	@Query(value ="SELECT * FROM event_instance WHERE occurred_event = ?1",nativeQuery = true)
	ArrayList<EventInstance> getEveInsByDefinedEveId(@Param("DefinedEventId") long date);

	
}	
