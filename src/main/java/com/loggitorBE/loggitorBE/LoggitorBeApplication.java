package com.loggitorBE.loggitorBE;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loggitorBE.loggitorBE.domain.App;
import com.loggitorBE.loggitorBE.domain.AppRepo;
import com.loggitorBE.loggitorBE.domain.DefectSeverity;
import com.loggitorBE.loggitorBE.domain.DefectSeverityRepo;
import com.loggitorBE.loggitorBE.domain.DefinedEvent;
import com.loggitorBE.loggitorBE.domain.DefinedEventPOJO;
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;
import com.loggitorBE.loggitorBE.domain.EventInstance;
import com.loggitorBE.loggitorBE.domain.EventInstanceRepo;
import com.loggitorBE.loggitorBE.domain.EventSeverity;
import com.loggitorBE.loggitorBE.domain.EventSeverityRepo;
import com.loggitorBE.loggitorBE.domain.FixAction;
import com.loggitorBE.loggitorBE.domain.FixActionRepo;
import com.nexmo.client.NexmoClientException;

@SpringBootApplication
public class LoggitorBeApplication {

	private static final Logger logger = LoggerFactory.getLogger(LoggitorBeApplication.class);

	@Autowired
	private AppRepo appRepo;
	@Autowired
	private EventSeverityRepo eventSevRepo;
	@Autowired
	private DefectSeverityRepo defectSevRepo;
	@Autowired
	private FixActionRepo actionRepo;
	@Autowired
	private DefinedEventRepo definedEveRepo;
	@Autowired
	private EventInstanceRepo eventInsRepo;

	
	
	public static void main(String[] args)
			throws AddressException, MessagingException, IOException, NexmoClientException {
		SpringApplication.run(LoggitorBeApplication.class, args);
		logger.info("Hello Sping Boot!");
		// String[] to = {"fady.93.fk@gmail.com"};

		// Email.sendEmailMessage(to, "test", "hi");
		// SMS.smsSend();
		// Email.sendEmailMessage(to, "test", "hi");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			
			/*
			 * init data
			 */
			appRepo.save(new App("BLM", "Core"));
			appRepo.save(new App("BLM", "Custom"));
			
			appRepo.save(new App("CLM", "Core"));
			appRepo.save(new App("CLM", "Custom"));
			
			appRepo.save(new App("CGN", "Core"));
			appRepo.save(new App("CGN", "Custom"));
			
			appRepo.save(new App("CMN", "Core"));
			appRepo.save(new App("CMN", "Custom"));

			eventSevRepo.save(new EventSeverity("Error"));
			eventSevRepo.save(new EventSeverity("Warning"));
			eventSevRepo.save(new EventSeverity("Critical"));
			
			defectSevRepo.save(new DefectSeverity("Error"));
			defectSevRepo.save(new DefectSeverity("Warning"));
			defectSevRepo.save(new DefectSeverity("Critical"));
			
			actionRepo.save(new FixAction("SMS"));
			actionRepo.save(new FixAction("Email"));
			
			
			
			/*
			 * activate on daily manner
			 * sleep for certain time
			 */
			SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			while(true) {
				
				loopOverEvents();
				
				System.out.println("Enter Sleep on: " + timeformat.format(Calendar.getInstance().getTime()));
				Thread.sleep(10*60 * 1000);
				
				
				
			}
			

			
			//insertEventInstance(new BigInteger("970"));
			
		};
	}
	
	
	
	
	
	
	/*
	 * insert event instance with given Defined Event ID
	 */
	private void insertEventInstance(BigInteger id)
	{
		/************ REMINDER - perform the action *****************/
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		DefinedEvent event = definedEveRepo.findById(id);
		EventInstance eventIns = new EventInstance(dateformat.format(Calendar.getInstance().getTime()),event);
		eventInsRepo.save(eventIns);

	}
	
	
	
	/*
	 * loop over the defined events and check if events had occurred
	 */
	private void loopOverEvents() throws JSONException, IOException
	{
		/*
		 * loop over the defined events and check if events had occurred
		 */
		ArrayList<DefinedEventPOJO> allEvents = definedEveRepo.getAllDefinedEvent();
		String app;
		String severity;
		int percent;
		
		for (DefinedEventPOJO event: allEvents) {
			app = event.getApp_name();
			severity = event.getDef_severity();
			ReadEventFromDB.getJSONfromURL(app, severity, "");
			
			while(ReadEventFromDB.hasNext())
			{
				percent = ReadEventFromDB.getNext();
				
				switch(event.getComperator().toLowerCase())
				{
				case "greater than":
					if(percent > event.getPercent())
						insertEventInstance(event.getId());
				case "lower than":
					if(percent < event.getPercent())
						insertEventInstance(event.getId());
				case "equal":
					if(percent == event.getPercent())
						insertEventInstance(event.getId());
					
					
				}
			}// END of while
			
			ReadEventFromDB.close();
			
		}// END of for
	}

}
