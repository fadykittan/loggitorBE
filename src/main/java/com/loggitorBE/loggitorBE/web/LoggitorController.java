package com.loggitorBE.loggitorBE.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loggitorBE.loggitorBE.domain.DefectSeverityRepo;
import com.loggitorBE.loggitorBE.domain.DefinedEvent;
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;
import com.loggitorBE.loggitorBE.domain.EventInstanceOnDate;
import com.loggitorBE.loggitorBE.domain.EventInstanceRepo;
import com.loggitorBE.loggitorBE.domain.EventsResult;

@RestController
public class LoggitorController {

	@Autowired
	private DefinedEventRepo eventRepo;

	@Autowired
	private EventInstanceRepo eventInsRepo;
	
	@Autowired
	private DefectSeverityRepo defSevRebo;
	
	@RequestMapping("/events")
	public Iterable<DefinedEvent> getEvents() {
		return eventRepo.findAll();
	}
	
	
	@RequestMapping("/getEventInsTable/{date}")
	public ArrayList<EventInstanceOnDate> getActionLogTable(@PathVariable String date) {
	    return eventInsRepo.getEventInsTable(date);
	    }
	
	@RequestMapping("/eventsresult")
	  public ArrayList<EventsResult> geEventsResults() {
	    return defSevRebo.getEventsResult();
	  }


}
