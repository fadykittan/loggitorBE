package com.loggitorBE.loggitorBE.domain;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DefectSeverityRepo extends CrudRepository<DefectSeverity, Long> {
	@Query(nativeQuery = true)
	ArrayList<EventsResult> getEventsResult();

}
