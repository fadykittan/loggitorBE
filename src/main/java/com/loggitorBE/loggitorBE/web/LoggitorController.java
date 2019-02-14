package com.loggitorBE.loggitorBE.web;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loggitorBE.loggitorBE.admin.domain.UserRepository;
import com.loggitorBE.loggitorBE.domain.ActionsByApp;
import com.loggitorBE.loggitorBE.domain.ActionsBySeverity;
import com.loggitorBE.loggitorBE.domain.ActionsName;
import com.loggitorBE.loggitorBE.domain.App;
import com.loggitorBE.loggitorBE.domain.AppRepo;
import com.loggitorBE.loggitorBE.domain.AppsNames;
import com.loggitorBE.loggitorBE.domain.DailyChart;
import com.loggitorBE.loggitorBE.domain.DailyChartReturned;
import com.loggitorBE.loggitorBE.domain.DefectSevApi;
import com.loggitorBE.loggitorBE.domain.DefectSeverity;
import com.loggitorBE.loggitorBE.domain.DefectSeverityRepo;
import com.loggitorBE.loggitorBE.domain.DefinedEvent;
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;
import com.loggitorBE.loggitorBE.domain.EventInstance;
import com.loggitorBE.loggitorBE.domain.EventInstanceOnDate;
import com.loggitorBE.loggitorBE.domain.EventInstanceRepo;
import com.loggitorBE.loggitorBE.domain.EventSevList;
import com.loggitorBE.loggitorBE.domain.EventSeverity;
import com.loggitorBE.loggitorBE.domain.EventSeverityRepo;
import com.loggitorBE.loggitorBE.domain.EventsResult;
import com.loggitorBE.loggitorBE.domain.FixAction;
import com.loggitorBE.loggitorBE.domain.FixActionRepo;
import com.nexmo.client.NexmoClientException;

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
	private EventSeverityRepo eventSevRepo;

	@Autowired
	private DefectSeverityRepo defSevRepo;

	@Autowired
	private FixActionRepo actionRepo;
	
	@Autowired
	private UserRepository userRepo;




	@RequestMapping("/events")
	public Iterable<DefinedEvent> getEvents() {
		return eventRepo.findAll();
	}

	
	@SuppressWarnings("deprecation")
	@RequestMapping("/viewEvents/{pageNumber}/{pageSize}")
	@ResponseBody
	public ArrayList<EventsResult> getEventsResult(@PathVariable("pageNumber") int pageNumber,
			@PathVariable("pageSize") int pageSize, HttpServletRequest req, HttpServletResponse res)
			throws ServletException {

		if(pageNumber < 1 || pageSize < 1)
		{
			return eventRepo.getEventsResult(new PageRequest(0, 999, Sort.Direction.ASC, "id"));
		}
		else
		{
			return eventRepo.getEventsResult(new PageRequest(pageNumber - 1, pageSize, Sort.Direction.ASC, "id"));
		}
	}

	@RequestMapping("/getAllEventInsTable/{date}")
	public ArrayList<EventInstanceOnDate> getActionLogTable(@PathVariable Date date) {

		// limit => 999, offset => 0
		return eventInsRepo.getEventInsTable(date, 999, 0);
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	@ResponseBody
	public boolean addEvent(@RequestBody EventsResult event) {
		try {
		
			String appAndType = event.getAppName();
			int indexStrik = appAndType.indexOf("-");
			String appName = appAndType.substring(0, indexStrik);
			String appType = appAndType.substring(indexStrik  + 1 ,appAndType.length()) ;
			String defSeverity = event.getDefSeverity();
			String comperator = event.getComperator();
			Double percent = event.getPercent();
			String eventName = event.getEventName();
			String eventSev = event.getEventSeverity();
			String actionName = event.getActionName();
			String des = event.getDescription();
			String userName = event.getuserName();
			String msg = event.getMsg();

			ArrayList<BigInteger> appID = appRepo.findByAppnameAndType(appName, appType);
			ArrayList<BigInteger> defID = defRepo.findByDefSeverity(defSeverity);
			ArrayList<BigInteger> actionID = actionRepo.findByActionName(actionName);
			ArrayList<BigInteger> eventSeverityID = eventSevRepo.findByEvSeverity(eventSev);
			ArrayList<BigInteger> userID = userRepo.findByUserName(userName);
			
			App app = new App(appName, appType);
			app.setId(appID.get(0).longValue());
			FixAction action = new FixAction(actionName);
			action.setId(actionID.get(0).longValue());
			DefectSeverity ds = new DefectSeverity(defSeverity);
			ds.setId(defID.get(0).longValue());
			EventSeverity es = new EventSeverity(eventSev);
			es.setId(eventSeverityID.get(0).longValue());

			DefinedEvent eve = new DefinedEvent(percent, comperator, eventName, des, userID.get(0).longValue(), msg, action, ds, es, app);
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
			
			ArrayList<EventInstance> eventIns = eventInsRepo.getEveInsByDefinedEveId(evenID);
			
			for(EventInstance i: eventIns)
			{
				eventInsRepo.deleteById(i.getId());
			}
			
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
			String appAndType = event.getAppName();
			int indexStrik = appAndType.indexOf("-");
			String appName = appAndType.substring(0, indexStrik);
			String appType = appAndType.substring(indexStrik  + 1 ,appAndType.length()) ;
			String defSeverity = event.getDefSeverity();
			String comperator = event.getComperator();
			Double percent = event.getPercent();
			String eventName = event.getEventName();
			String eventSev = event.getEventSeverity();
			String actionName = event.getActionName();
			String des = event.getDescription();
			String userName = event.getuserName();
			String msg = event.getMsg();
			
			ArrayList<BigInteger> appID = appRepo.findByAppnameAndType(appName, appType);
			ArrayList<BigInteger> defID = defRepo.findByDefSeverity(defSeverity);
			ArrayList<BigInteger> actionID = actionRepo.findByActionName(actionName);
			ArrayList<BigInteger> eventSeverityID = eventSevRepo.findByEvSeverity(eventSev);
			ArrayList<BigInteger> userID = userRepo.findByUserName(userName);
			
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
			eventToUpdate.get().setUserId(userID.get(0).longValue());
			eventToUpdate.get().setMsg(msg);
			
			eventRepo.save(eventToUpdate.get());
			return true;
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@RequestMapping("/getEventInsTable/{date}/{pageSize}/{PageNumber}")
	public ArrayList<EventInstanceOnDate> getActionLogTable(@PathVariable("date") Date date,
			@PathVariable("pageSize") int pageSize, @PathVariable("PageNumber") int pageNumber) {

		if (pageSize < 1 || pageNumber < 1) {
			return eventInsRepo.getEventInsTable(date, 999, 0);
		} else {
			int limit = pageSize;
			int offset = pageNumber - 1;
			offset = offset * limit;
			return eventInsRepo.getEventInsTable(date, limit, offset);
		}
	}

	// calling the method that return the applications names
	@RequestMapping("/apps")
	public ArrayList<AppsNames> getAppsNames() {
		return appRepo.getAppsNames();
	}

	// calling the method that return the defect severities
	@RequestMapping("/defectsSeverities")
	public ArrayList<DefectSevApi> getDefectsSev() {
		return defSevRepo.getDefectsSev();
	}

	// calling the method that return the actions names
	@RequestMapping("/actionsName")
	public ArrayList<ActionsName> getActionsName() {
		return actionRepo.getActionsName();
	}

	// calling the method that create the actions by applications table
	@RequestMapping("/actionsbyapp/{date}/{pageSize}/{PageNumber}")
	public ArrayList<ActionsByApp> getActionsByApp(@PathVariable("date") Date date, 
			@PathVariable("pageSize") int pageSize, @PathVariable("PageNumber") int pageNumber) {
		
		if (pageSize < 1 || pageNumber < 1) {
			return appRepo.getActionsByApp(date,999,0);
		} else {
			int limit = pageSize;
			int offset = pageNumber - 1;
			offset = offset * limit;
			return appRepo.getActionsByApp(date,limit,offset);
		}
	}

	// calling the method that create the actions by severities table
	@RequestMapping("/actionsbyseverity/{date}/{pageSize}/{PageNumber}")
	public ArrayList<ActionsBySeverity> getActionsBySeverity(@PathVariable("date") Date date, 
			@PathVariable("pageSize") int pageSize, @PathVariable("PageNumber") int pageNumber) {
		
		if (pageSize < 1 || pageNumber < 1) {
			return eventSevRepo.getActionsBySeverity(date,999,0);
		} else {
			int limit = pageSize;
			int offset = pageNumber - 1;
			offset = offset * limit;
		return eventSevRepo.getActionsBySeverity(date,limit,offset);
		}

	}

	
	
	
	
	/*
	 * calling the method to get JSON for Daily chart
	 */
	@RequestMapping("/getDailyChart/{date}/{pageSize}/{PageNumber}")
	private ArrayList<DailyChartReturned> getDailyChartReturned(@PathVariable("date") Date date, 
			@PathVariable("pageSize") int pageSize, @PathVariable("PageNumber") int pageNumber) {
	
		String fullName;
		float[] floatArr;
		ArrayList<DailyChart> arr = getDailyChart(date,pageSize,pageNumber);
		
		
		/*
		 * index 0 -> cri
		 * index 1 -> war
		 * index 2 -> err
		 * 
		 * map to sort the values
		 */
		HashMap<String, float[]> tempMap= new HashMap<>();
		
		// iterate over Daily chart arrayList
		for(DailyChart d: arr)
		{
			fullName = d.getName() + "-" + d.getType();
			if(tempMap.get(fullName) != null)
			{
				// if the key exists in the map
				switch (d.getSeverity())
				{
				case "Critical":
					tempMap.get(fullName)[0] = d.getPercent();
					break;
					
				case "Warning":
					tempMap.get(fullName)[1] = d.getPercent();
					break;
					
				case "Error":
					tempMap.get(fullName)[2] = d.getPercent();
					break;
				}
				
			}
			else
			{
				floatArr = new float[3];
				floatArr[0] = 0;
				floatArr[1] = 0;
				floatArr[2] = 0;
				
				switch (d.getSeverity())
				{
				case "Critical":
					floatArr[0] = d.getPercent();
					break;
					
				case "Warning":
					floatArr[1] = d.getPercent();
					break;
					
				case "Error":
					floatArr[2] = d.getPercent();
					break;
				}
				
				tempMap.put(fullName, floatArr);
			}
			
			floatArr = null;
			fullName = null;

		}
		
		
		// returned result
		ArrayList<DailyChartReturned> result = new ArrayList<>();
		
		// get keys entry as set
		Set<HashMap.Entry<String, float[]>> setRes = tempMap.entrySet();
		
		// iterate over the entry set
		for(HashMap.Entry<String, float[]> entry: setRes)
		{
			result.add(new DailyChartReturned(entry.getKey(), 
					entry.getValue()[0], entry.getValue()[1], entry.getValue()[2]));
		}
		
		
		return result;
	
	}
	
	
	
	
	
	
	
	
	/*
	 *  DailyChart method for getDailyChart API
	 */
	private ArrayList<DailyChart> getDailyChart(Date date, 
			int pageSize, int pageNumber) {
		
		
		if (pageSize < 1 || pageNumber < 1) {
			return eventRepo.getDailyChart(date,999,0);
		} else {
			int limit = pageSize;
			int offset = pageNumber - 1;
			offset = offset * limit;
			return eventRepo.getDailyChart(date,limit,offset);
			
		}
		

	}
	
	
	
	// calling the method that return the event severities
	@RequestMapping("/getEventSevList")
	public ArrayList<EventSevList> getEventsSev() {
		return eventSevRepo.getEventsSev();
	}


	
	/*
	 * check the event in db and adding event instances
	 * using SysTools class
	 */
	@RequestMapping("/runActionSys")
	public void runActionSys() throws AddressException, JSONException, IOException, MessagingException, NexmoClientException {
		System.out.println("Checking Events Starts");
		SysTools mytools = new SysTools(eventRepo,eventInsRepo);
		mytools.loopOverEvents();
		System.out.println("Checking Events Ended");
	}
	
	
	
}
