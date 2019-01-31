package com.loggitorBE.loggitorBE.domain;

import java.util.ArrayList;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface EventPagingRepository extends PagingAndSortingRepository<DefinedEvent, Long>{
	
	@Query(nativeQuery = true)
	ArrayList<EventsResult> getEventsResult(PageRequest pageRequest);
	
}	
