package com.loggitorBE.loggitorBE.web;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.json.JSONException;

import com.loggitorBE.loggitorBE.ReadEventFromDB;
import com.loggitorBE.loggitorBE.domain.DefinedEvent;
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;
import com.loggitorBE.loggitorBE.domain.EventInstance;
import com.loggitorBE.loggitorBE.domain.EventInstanceRepo;
import com.nexmo.client.NexmoClientException;

public class Worker {

	private DefinedEventRepo definedEveRepo;
	private EventInstanceRepo eventInsRepo;

	private DefinedEventList task;
	private int threadNum;

	public Worker(DefinedEventList task) {
		this.task = task;
	}

	/*
	 * public RunApp(DefinedEventRepo DE, EventInstanceRepo EI) { super();
	 * definedEveRepo = DE; eventInsRepo = EI; }
	 */

	/*
	 * loop over the defined events and check if events had occurred
	 */
	public void loopOverEvents()
			throws JSONException, IOException, AddressException, MessagingException, NexmoClientException {
		/*
		 * loop over the defined events and check if events had occurred
		 */
//		DefinedEventPOJO event;

		System.out.println("task");
		task.getAllEvents().parallelStream().forEach(event -> {
			System.out.println("Thread Num: " + threadNum + "Works on Event ID: " + event.getId());
			String app = event.getApp_name();
			String severity = event.getDef_severity();

			Date sqlDate = new Date(Calendar.getInstance().getTime().getTime());
			System.out.println(sqlDate.toString());
			try {
				ReadEventFromDB.getJSONfromURL(app, severity, sqlDate);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			while (ReadEventFromDB.hasNext()) {
				float percent = ReadEventFromDB.getNext();
				try {
					switch (event.getComperator().toLowerCase()) {
					case "greater than":
						if (percent > event.getPercent()) {
							insertEventInstance(event.getId());
						}
					case "lower than":
						if (percent < event.getPercent())
							insertEventInstance(event.getId());
					case "equal":
						if (percent == event.getPercent())
							insertEventInstance(event.getId());
					}
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NexmoClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * ArrayList<DefinedEventPOJO> allEvents = definedEveRepo.getAllDefinedEvent();
	 * String app; String severity; float percent;
	 * 
	 * for (DefinedEventPOJO event: allEvents) { app = event.getApp_name(); severity
	 * = event.getDef_severity(); Date sqlDate = new
	 * Date(Calendar.getInstance().getTime().getTime());
	 * System.out.println(sqlDate.toString()); ReadEventFromDB.getJSONfromURL(app,
	 * severity, sqlDate);
	 * 
	 * while(ReadEventFromDB.hasNext()) { percent = ReadEventFromDB.getNext();
	 * 
	 * switch(event.getComperator().toLowerCase()) { case "greater than": if(percent
	 * > event.getPercent()) insertEventInstance(event.getId()); case "lower than":
	 * if(percent < event.getPercent()) insertEventInstance(event.getId()); case
	 * "equal": if(percent == event.getPercent())
	 * insertEventInstance(event.getId()); } }// END of while
	 * 
	 * ReadEventFromDB.close();
	 * 
	 * }// END of for }
	 */

	/*
	 * insert event instance with given Defined Event ID
	 */
	private void insertEventInstance(BigInteger id)
			throws AddressException, MessagingException, IOException, NexmoClientException {
		// perform the action
		String action = definedEveRepo.findActionById(id);
		String msg = definedEveRepo.findMsgById(id);
		if (action.equals("Email")) {
			String email = definedEveRepo.findEmailById(id);
			Email.sendEmailMessage(email, "Loggitor Action System", msg);
		} else if (action.equals("SMS")) {
			String phone = definedEveRepo.findPhoneById(id);
			// "972525151592"
			SMS.smsSend(msg, phone);
		}

		/************ action ends *****************/

		DefinedEvent event = definedEveRepo.findById(id);
		if (event == null) {
			System.out.println("ID Does not exist");
			return;
		}

		Date sqlDate = new Date(Calendar.getInstance().getTime().getTime());
		EventInstance eventIns = new EventInstance(sqlDate, event);
		eventInsRepo.save(eventIns);
	}

	public void setTask(DefinedEventList task) {
		this.task = task;
	}
}