package com.loggitorBE.loggitorBE.domain;


import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;

//import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DefinedEventRepo extends CrudRepository<DefinedEvent, Long> {
	
	
//	@Query(nativeQuery = true)
//	ArrayList<EventsResult> getEventsResult(PageRequest pageRequest);
	
	@Query(nativeQuery = true)
	ArrayList<EventsResult> getEventsResult(int limit, int offset);
	
	
	@Query(nativeQuery = true)
	ArrayList<DailyChart> getDailyChart(Date date, int limit, int offset);
	
	
	@Query(nativeQuery = true)
	ArrayList<DefinedEventPOJO> getAllDefinedEvent();
	

	
	@Query(value = "SELECT * FROM DEFINED_EVENT WHERE DEFINED_EVENT.ID = ?1", nativeQuery = true)
	DefinedEvent findById(BigInteger id);

	
	@Query(value = "select fa.action_name from fix_action fa, defined_event de where de.id = ?1 and fa.id = de.fix_action", nativeQuery = true)
	String findActionById(BigInteger id);
	
	
//	@Query(value = "select u.email from defined_event de, users u where de.id = ?1 and de.user_id = u.user_id", nativeQuery = true)
//	String findEmailById(BigInteger id);
	
	
	@Query(value = "select de.msg from defined_event de where de.id = ?1", nativeQuery = true)
	String findMsgById(BigInteger id);
	
	
//	@Query(value = "select u.phone from defined_event de, users u where de.id = ?1 and de.user_id = u.user_id", nativeQuery = true)
//	String findPhoneById(BigInteger id);
	
	
	@Query(value = "SELECT COUNT(*) FROM defined_event", nativeQuery = true)
	int countDefinedEve();
	
}
