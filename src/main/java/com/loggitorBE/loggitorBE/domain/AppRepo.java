package com.loggitorBE.loggitorBE.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AppRepo extends CrudRepository<App, Long> {
	
	// declaring a method that pull the applications names from the data base using costume query
	@Query(nativeQuery = true)
	ArrayList<AppsNames> getAppsNames();
	// declaring a method that create a table that calculate the number and the percentage of the actions by applications
	@Query(nativeQuery = true)
	ArrayList<ActionsByApp> getActionsByApp(String date, int limit, int offset);
	
	
	List<App> findByName(String name);
	

}
