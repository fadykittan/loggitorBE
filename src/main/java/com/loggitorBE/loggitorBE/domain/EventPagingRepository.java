package com.loggitorBE.loggitorBE.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface EventPagingRepository extends PagingAndSortingRepository<DefinedEvent, Long>{
	
	
	Iterable<DefinedEvent> viewEvents(@RequestParam(name = "p", defaultValue = "1") int pageNumber);
	
}	
