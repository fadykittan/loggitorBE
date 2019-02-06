package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FixActionRepo extends CrudRepository<FixAction, Long>{
	
	// declaring a method that return a list of actions names
	@Query(nativeQuery = true)
	ArrayList<ActionsName> getActionsName();
	
	@Query(value = "select f.id from fix_action f where f.action_name = ?1",nativeQuery = true)
	ArrayList<BigInteger>  findByActionName(String actionName);
	

}
