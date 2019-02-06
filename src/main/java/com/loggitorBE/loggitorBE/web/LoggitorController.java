package com.loggitorBE.loggitorBE.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

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
import com.loggitorBE.loggitorBE.domain.EventSeverity;
import com.loggitorBE.loggitorBE.domain.EventSeverityRepo;
import com.loggitorBE.loggitorBE.domain.EventsResult;
import com.loggitorBE.loggitorBE.domain.FixAction;
import com.loggitorBE.loggitorBE.domain.FixActionRepo;

@RestController
public class LoggitorController {

	// private final static int PAGESIZE = 10;

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

	@Autowired
	private EventSeverityRepo eventSevRepo;

	@RequestMapping("/events")
	public Iterable<DefinedEvent> getEvents() {
		return eventRepo.findAll();
	}

	/*
	 * /// viewEvents?p=1
	 * 
	 * @RequestMapping(value = "/viewEvents", method = RequestMethod.GET) public
	 * Iterable<DefinedEvent> viewEvents(@RequestParam(name = "p", defaultValue =
	 * "1") int pageNumber) {
	 * 
	 * @SuppressWarnings("deprecation") PageRequest request = new
	 * PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id");
	 * 
	 * return eventPagingRepo.findAll(request).getContent(); }
	 */

	@SuppressWarnings("deprecation")
	@RequestMapping("/viewEvents/{pageNumber}/{pageSize}")
	@ResponseBody
	public ArrayList<EventsResult> getEventsResult(@PathVariable("pageNumber") int pageNumber,
			@PathVariable("pageSize") int pageSize, HttpServletRequest req, HttpServletResponse res)
			throws ServletException {

		return eventRepo.getEventsResult(new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC, "id"));
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	@ResponseBody
	public boolean addEvent(@RequestBody ArrayList<EventsResult> events) {
		try {
			EventsResult event = events.get(0);
			String appName = event.getAppName();
			String appType = event.getAppType();
			String defSeverity = event.getDefSeverity();
			String comperator = event.getComperator();
			Double percent = event.getPercent();
			String eventName = event.getEventName();
			String eventSev = event.getEventSeverity();
			String actionName = event.getActionName();
			String des = event.getDescription();

			ArrayList<BigInteger> appID = appRepo.findByAppnameAndType(appName, appType);
			ArrayList<BigInteger> defID = defRepo.findByDefSeverity(defSeverity);
			ArrayList<BigInteger> actionID = actionRepo.findByActionName(actionName);
			ArrayList<BigInteger> eventSeverityID = eventSevRepo.findByEvSeverity(eventSev);

			App app = new App(appName, appType);
			app.setId(appID.get(0).longValue());
			FixAction action = new FixAction(actionName);
			action.setId(actionID.get(0).longValue());
			DefectSeverity ds = new DefectSeverity(defSeverity);
			ds.setId(defID.get(0).longValue());
			EventSeverity es = new EventSeverity(eventSev);
			es.setId(eventSeverityID.get(0).longValue());

			DefinedEvent eve = new DefinedEvent(percent, comperator, eventName, des, action, ds, es, app);
			eventRepo.save(eve);
			return true;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@RequestMapping(value = "/deleteEvent/{eventID}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteEvent(@PathVariable("eventID") long evenID) {
		try {
			eventRepo.deleteById(evenID);
			return true;
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	@RequestMapping(value = "/updateEvent", method = RequestMethod.PUT)
	@ResponseBody
	public boolean updateEvent(@RequestBody EventsResult event)
	{
		try {
			BigInteger id = event.getId();
			String appName = event.getAppName();
			String appType = event.getAppType();
			String defSeverity = event.getDefSeverity();
			String comperator = event.getComperator();
			Double percent = event.getPercent();
			String eventName = event.getEventName();
			String eventSev = event.getEventSeverity();
			String actionName = event.getActionName();
			String des = event.getDescription();
			
			ArrayList<BigInteger> appID = appRepo.findByAppnameAndType(appName, appType);
			ArrayList<BigInteger> defID = defRepo.findByDefSeverity(defSeverity);
			ArrayList<BigInteger> actionID = actionRepo.findByActionName(actionName);
			ArrayList<BigInteger> eventSeverityID = eventSevRepo.findByEvSeverity(eventSev);

			App app = new App(appName, appType);
			app.setId(appID.get(0).longValue());
			FixAction action = new FixAction(actionName);
			action.setId(actionID.get(0).longValue());
			DefectSeverity ds = new DefectSeverity(defSeverity);
			ds.setId(defID.get(0).longValue());
			EventSeverity es = new EventSeverity(eventSev);
			es.setId(eventSeverityID.get(0).longValue());
	
			Optional<DefinedEvent> eventToUpdate = eventRepo.findById(id.longValue());
			eventToUpdate.get().setPercent(percent);
			eventToUpdate.get().setComperator(comperator);
			eventToUpdate.get().setName(eventName);
			eventToUpdate.get().setDescription(des);
			eventToUpdate.get().setFixAction(action);
			eventToUpdate.get().setDefectSev(ds);
			eventToUpdate.get().setEventSev(es);
			eventToUpdate.get().setApp(app);
			
			eventRepo.save(eventToUpdate.get());
			return true;
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@RequestMapping("/getEventInsTable/{date}")
	public ArrayList<EventInstanceOnDate> getActionLogTable(@PathVariable String date) {

		return eventInsRepo.getEventInsTable(date);
	}

	// calling the method that return the applications names
	@RequestMapping("/apps")
	public ArrayList<AppsNames> getAppsNames() {
		return appRepo.getAppsNames();
	}

	// calling the method that return the defect severities
	@RequestMapping("/defectsSeverities")
	public ArrayList<DefectSevApi> getDefectsSev() {
		return defRepo.getDefectsSev();
	}

	// calling the method that return the actions names
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
