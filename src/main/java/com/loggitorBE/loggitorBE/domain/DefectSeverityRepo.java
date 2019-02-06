package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;
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
	
	@Query(value ="select d.id from defect_severity d where d.defect_severity = ?1",nativeQuery = true)
	ArrayList<BigInteger> findByDefSeverity(String defSeverity);
	

}
