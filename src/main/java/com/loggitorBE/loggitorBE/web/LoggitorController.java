package com.loggitorBE.loggitorBE.web;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import com.loggitorBE.loggitorBE.domain.ActionsByApp;
import com.loggitorBE.loggitorBE.domain.ActionsBySeverity;
import com.loggitorBE.loggitorBE.domain.ActionsName;
import com.loggitorBE.loggitorBE.domain.AppRepo;
import com.loggitorBE.loggitorBE.domain.AppsNames;
import com.loggitorBE.loggitorBE.domain.DefectSevApi;
import com.loggitorBE.loggitorBE.domain.DefectSeverityRepo;
import com.loggitorBE.loggitorBE.domain.DefinedEvent;
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;
import com.loggitorBE.loggitorBE.domain.EventInstanceOnDate;
import com.loggitorBE.loggitorBE.domain.EventInstanceRepo;

import com.loggitorBE.loggitorBE.domain.EventsResult;

import com.loggitorBE.loggitorBE.domain.FixActionRepo;


@RestController
public class LoggitorController {

	//private final static int PAGESIZE = 10;

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

/*	/// viewEvents?p=1
	@RequestMapping(value = "/viewEvents", method = RequestMethod.GET)
	public Iterable<DefinedEvent> viewEvents(@RequestParam(name = "p", defaultValue = "1") int pageNumber) {
		@SuppressWarnings("deprecation")
		PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");

		return eventPagingRepo.findAll(request).getContent();
	}*/

	@SuppressWarnings("deprecation")
	@RequestMapping("/viewEvents/{pageNumber}/{pageSize}")
	@ResponseBody
	public ArrayList<EventsResult> getEventsResult(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize, HttpServletRequest req,
			HttpServletResponse res) throws ServletException {

		return eventRepo.getEventsResult(new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC, "id"));
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
