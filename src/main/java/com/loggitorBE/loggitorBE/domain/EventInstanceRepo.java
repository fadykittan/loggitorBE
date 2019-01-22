package com.loggitorBE.loggitorBE.domain;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface EventInstanceRepo extends CrudRepository<EventInstance, Long>{

	
	@Query(nativeQuery = true)
	ArrayList<EventInstanceOnDate> getEventInsTable(@Param("date") String date);

	
}	
