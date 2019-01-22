package com.loggitorBE.loggitorBE.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loggitorBE.loggitorBE.domain.DefinedEvent;

import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;

@RestController
public class LoggitorController {

	@Autowired
	private DefinedEventRepo eventRepo;

	@RequestMapping("/events")
	public Iterable<DefinedEvent> getEvents() {
		return eventRepo.findAll();
	}

}
