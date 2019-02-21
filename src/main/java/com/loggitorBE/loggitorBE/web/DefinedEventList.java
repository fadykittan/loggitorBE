package com.loggitorBE.loggitorBE.web;

import java.util.List;

import com.loggitorBE.loggitorBE.domain.DefinedEventPOJO;
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;

public class DefinedEventList {

	private DefinedEventRepo definedEveRepo;
	
	private List<DefinedEventPOJO> allEvents;
	private int size;
	
	public DefinedEventList(DefinedEventRepo repo) {
		super();
		definedEveRepo = repo;
		allEvents = definedEveRepo.getAllDefinedEvent();
	}

	public int getSize() {
		return size;
	}

	public List<DefinedEventPOJO> getAllEvents() {
		return allEvents;
	}
}
