package com.loggitorBE.loggitorBE.domain;


import org.springframework.data.repository.CrudRepository;


public interface EventInstanceRepo extends CrudRepository<EventInstance, Long>{

	/*
	@Query(nativeQuery = true)
	ArrayList<ActionLog> getActionLogTable(@Param("date") String date);
	*/
	
}	
