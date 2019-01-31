package com.loggitorBE.loggitorBE.domain;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FixActionRepo extends CrudRepository<FixAction, Long>{
	
	// declaring a method that return a list of actions names
	@Query(nativeQuery = true)
	ArrayList<ActionsName> getActionsName();

}
