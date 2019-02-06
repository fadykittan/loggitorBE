package com.loggitorBE.loggitorBE.domain;


import java.math.BigInteger;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventSeverityRepo extends CrudRepository<EventSeverity, Long> {

	
	@Query(value = "select e.id from event_severity e where e.severity = ?1",nativeQuery = true)
	ArrayList<BigInteger> findByEvSeverity(String severity);
	

}
