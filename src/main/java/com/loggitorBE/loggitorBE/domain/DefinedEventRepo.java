package com.loggitorBE.loggitorBE.domain;


import java.util.ArrayList;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface DefinedEventRepo extends CrudRepository<DefinedEvent, Long> {
	
	@Query(nativeQuery = true)
	ArrayList<EventsResult> getEventsResult(PageRequest pageRequest);
	
	@Query(nativeQuery = true)
	ArrayList<DailyChart> getDailyChart(String date, int limit, int offset);

}
