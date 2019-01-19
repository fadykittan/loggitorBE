package com.loggitorBE.loggitorBE.domain;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface Event_Instance_Repo extends CrudRepository<Event_Instance, Long>{

	
	@Query(nativeQuery = true)
	ArrayList<ActionLog> getActionLogTable(@Param("date") String date);
	/*
	@Query(nativeQuery = true)
	ArrayList<ActionLog> getActionLogTable();
	*/
}	
