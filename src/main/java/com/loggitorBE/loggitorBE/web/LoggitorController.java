package com.loggitorBE.loggitorBE.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loggitorBE.loggitorBE.domain.ActionLog;
import com.loggitorBE.loggitorBE.domain.Event;
import com.loggitorBE.loggitorBE.domain.Event_Instance_Repo;
import com.loggitorBE.loggitorBE.domain.Event_Repo;



@RestController
public class LoggitorController {

	
	@Autowired
	private Event_Repo eventRepo;
	
	@Autowired
	private Event_Instance_Repo eventInsRepo;
	
	@RequestMapping("/events")
	public Iterable<Event> getEvents(){
		return eventRepo.findAll();
	}
	
	
	@RequestMapping("/getActionLogTable/{date}")
	public ArrayList<ActionLog> getActionLogTable(@PathVariable String date) {
	    return eventInsRepo.getActionLogTable(date);
	    }
	
	/*
	@RequestMapping("/getActionLogTable")
	public ArrayList<ActionLog> getActionLogTable() {
	    return eventInsRepo.getActionLogTable();
	    }
	    */

	
	
}
