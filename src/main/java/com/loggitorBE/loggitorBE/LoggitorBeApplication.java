package com.loggitorBE.loggitorBE;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
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

import com.loggitorBE.loggitorBE.domain.DefinedEvent;
import com.loggitorBE.loggitorBE.domain.DefinedEventPOJO;
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;
import com.loggitorBE.loggitorBE.domain.EventInstance;
import com.loggitorBE.loggitorBE.domain.EventInstanceRepo;
import com.loggitorBE.loggitorBE.web.Email;
import com.loggitorBE.loggitorBE.web.SMS;
import com.nexmo.client.NexmoClientException;

@SpringBootApplication
public class LoggitorBeApplication {

	private static final Logger logger = LoggerFactory.getLogger(LoggitorBeApplication.class);

	@Autowired
	private DefinedEventRepo definedEveRepo;
	@Autowired
	private EventInstanceRepo eventInsRepo;

	
	public static void main(String[] args)
			throws AddressException, MessagingException, IOException, NexmoClientException {
		SpringApplication.run(LoggitorBeApplication.class, args);
		logger.info("Hello Sping Boot!");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			
			/*
			 * activate on daily manner
			 * sleep for certain time
			 */
			SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			while(true) {
				
				
				
				System.out.println("Enter Sleep on: " + timeformat.format(Calendar.getInstance().getTime()));
				//Sleep for an hour
				Thread.sleep(60 * 60 * 1000);
				
				loopOverEvents();
				
			}
			
			
		};
	}
	
	
	
	
	
	
	/*
	 * insert event instance with given Defined Event ID
	 */
	private void insertEventInstance(BigInteger id) throws AddressException, MessagingException, IOException, NexmoClientException
	{
		//perform the action
		String action = definedEveRepo.findActionById(id);
		String msg = definedEveRepo.findMsgById(id);
		if(action.equals("Email"))
		{
			String email = definedEveRepo.findEmailById(id);	
			Email.sendEmailMessage(email,"Loggitor Action System", msg);
		}
		else if(action.equals("SMS"))
		{
			String phone = definedEveRepo.findPhoneById(id);
			SMS.smsSend();
			
		}
		
		/************ action ends *****************/
		
		DefinedEvent event = definedEveRepo.findById(id);
		if(event == null)
		{
			System.out.println("ID Does not exist");
			return;
		}
		Date sqlDate = new Date(Calendar.getInstance().getTime().getTime());
		EventInstance eventIns = new EventInstance(sqlDate,event);
		eventInsRepo.save(eventIns);

	}
	
	
	
	/*
	 * loop over the defined events and check if events had occurred
	 */
	private void loopOverEvents() throws JSONException, IOException, AddressException, MessagingException, NexmoClientException
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
