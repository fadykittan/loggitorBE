package com.loggitorBE.loggitorBE.domain;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DefectSeverityRepo extends CrudRepository<DefectSeverity, Long> {
	
	//declaring a method that return a list of defect severity list by using a query
	@Query(nativeQuery = true)
	ArrayList<DefectSevApi> getDefectsSev();
	
	//declaring a method that create an actions by severity table
	@Query(nativeQuery = true)
	ArrayList<ActionsBySeverity> getActionsBySeverity();

}
