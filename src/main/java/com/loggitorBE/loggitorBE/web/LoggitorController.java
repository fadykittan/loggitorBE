package com.loggitorBE.loggitorBE.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import com.loggitorBE.loggitorBE.domain.DefinedEvent;
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;
import com.loggitorBE.loggitorBE.domain.EventInstanceOnDate;
import com.loggitorBE.loggitorBE.domain.EventInstanceRepo;
import com.loggitorBE.loggitorBE.domain.EventPagingRepository;

@RestController
public class LoggitorController {

	private final static int PAGESIZE = 10;

	@Autowired
	private DefinedEventRepo eventRepo;

	@Autowired
	private EventPagingRepository eventPagingRepo;

	@Autowired
	private EventInstanceRepo eventInsRepo;

	@RequestMapping("/events")
	public Iterable<DefinedEvent> getEvents() {
		return eventRepo.findAll();
	}

	///customers?p=1
	@RequestMapping(value = "/viewEvents", method = RequestMethod.GET)
	public Iterable<DefinedEvent> viewEvents(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		@SuppressWarnings("deprecation")
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");

		return eventPagingRepo.findAll(request).getContent();
	}

	@RequestMapping("/getEventInsTable/{date}")
	public ArrayList<EventInstanceOnDate> getActionLogTable(@PathVariable String date) {
		return eventInsRepo.getEventInsTable(date);
	}

}
