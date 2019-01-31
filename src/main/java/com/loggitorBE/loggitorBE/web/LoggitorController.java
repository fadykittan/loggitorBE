package com.loggitorBE.loggitorBE.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loggitorBE.loggitorBE.domain.ActionsByApp;
import com.loggitorBE.loggitorBE.domain.ActionsBySeverity;
import com.loggitorBE.loggitorBE.domain.ActionsName;
import com.loggitorBE.loggitorBE.domain.App;
import com.loggitorBE.loggitorBE.domain.AppRepo;
import com.loggitorBE.loggitorBE.domain.AppsNames;
import com.loggitorBE.loggitorBE.domain.DefectSevApi;
import com.loggitorBE.loggitorBE.domain.DefectSeverity;
import com.loggitorBE.loggitorBE.domain.DefectSeverityRepo;
import com.loggitorBE.loggitorBE.domain.DefinedEvent;
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;
import com.loggitorBE.loggitorBE.domain.EventInstanceOnDate;
import com.loggitorBE.loggitorBE.domain.EventInstanceRepo;
import com.loggitorBE.loggitorBE.domain.FixAction;
import com.loggitorBE.loggitorBE.domain.FixActionRepo;

@RestController
public class LoggitorController {

	@Autowired
	private DefinedEventRepo eventRepo;

	@Autowired
	private EventInstanceRepo eventInsRepo;
	
	@Autowired
	private AppRepo appRepo;
	
	@Autowired
	private DefectSeverityRepo defRepo;
	
	@Autowired
	private FixActionRepo actionRepo;
	
	@RequestMapping("/events")
	public Iterable<DefinedEvent> getEvents() {
		return eventRepo.findAll();
	}
	
	
	@RequestMapping("/getEventInsTable/{date}")
	public ArrayList<EventInstanceOnDate> getActionLogTable(@PathVariable String date) {
	    return eventInsRepo.getEventInsTable(date);
	    }
	
	//calling the method that return the applications names 
	@RequestMapping("/apps")
	public ArrayList<AppsNames> getAppsNames() {
		return appRepo.getAppsNames();
	}
	//calling the method that return the defect severities
	@RequestMapping("/defectsSeverities")
	public ArrayList<DefectSevApi> getDefectsSev() {
		return defRepo.getDefectsSev();
	}
	//calling the method that return the actions names
	@RequestMapping("/actionsName")
	public ArrayList<ActionsName> getActionsName() {
		return actionRepo.getActionsName();
	}
	
	// calling the method that create the actions by applications table
	@RequestMapping("/actionsbyapp")
	public ArrayList<ActionsByApp> getActionsByApp() {
		return appRepo.getActionsByApp();
	}
	
	// calling the method that create the actions by severities table
	@RequestMapping("/actionsbyseverity")
	public ArrayList<ActionsBySeverity> getActionsBySeverity() {
		return defRepo.getActionsBySeverity();

	}
	

}
