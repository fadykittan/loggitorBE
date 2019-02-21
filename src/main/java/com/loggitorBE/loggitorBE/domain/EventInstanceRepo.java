package com.loggitorBE.loggitorBE.domain;


import java.math.BigInteger;
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
	


	@Query(nativeQuery = true)
	ArrayList<WeeklyDiagram> getWeeklyDiagram();

	
	
	@Query(value ="SELECT * FROM event_instance WHERE occurred_event = ?1",nativeQuery = true)
	ArrayList<EventInstance> getEveInsByDefinedEveId(@Param("DefinedEventId") long id);


	
	
	@Query(value = "SELECT COUNT(*) FROM event_instance", nativeQuery = true)
	int countEventIns();
	
	
	@Query(value = "select count(ei.id) from event_instance as ei where ei.date = ?1 and ei.occurred_event = ?2", nativeQuery = true)
	int checkIfInsExist(@Param("date") Date date,@Param("eID") BigInteger eID);
	
}	
