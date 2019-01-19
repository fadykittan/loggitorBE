package com.loggitorBE.loggitorBE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loggitorBE.loggitorBE.domain.Action;
import com.loggitorBE.loggitorBE.domain.Action_Repo;
import com.loggitorBE.loggitorBE.domain.App;
import com.loggitorBE.loggitorBE.domain.App_Repo;
import com.loggitorBE.loggitorBE.domain.Defect_Severity;
import com.loggitorBE.loggitorBE.domain.Defect_Severity_Repo;
import com.loggitorBE.loggitorBE.domain.Event;
import com.loggitorBE.loggitorBE.domain.Event_Instance;
import com.loggitorBE.loggitorBE.domain.Event_Instance_Repo;
import com.loggitorBE.loggitorBE.domain.Event_Repo;
import com.loggitorBE.loggitorBE.domain.Event_Severity;
import com.loggitorBE.loggitorBE.domain.Event_Severity_Repo;



@SpringBootApplication
public class LoggitorBeApplication {

	
private static final Logger logger = LoggerFactory.getLogger(LoggitorBeApplication.class);

	
	@Autowired
	private App_Repo app;
	@Autowired
	private Event_Severity_Repo evSev;
	@Autowired
	private Defect_Severity_Repo defSev;
	@Autowired
	private Action_Repo act;
	@Autowired
	private Event_Repo eve;
	@Autowired
	private Event_Instance_Repo eveIns;
	
	
	public static void main(String[] args) {
		SpringApplication.run(LoggitorBeApplication.class, args);
		logger.info("Hello Sping Boot!");
	}
	
	
	@Bean
    CommandLineRunner runner(){
      return args -> {
    	  
    	  App a1 = new App("BLM","core");
    	  App a2 = new App("CLM","core");

    	  Event_Severity e1 = new Event_Severity("critical");
    	  Event_Severity e2 = new Event_Severity("error");
    	  
    	  Defect_Severity d1 = new Defect_Severity("d Critical");
    	  Defect_Severity d2 = new Defect_Severity("d error");
    	  
    	  Action ac1 = new Action("SMS","SMS");
    	  Action ac2 = new Action("email","email");
    	  
    	  Event ev1 = new Event(a1,d1,ac1,e1,"bigger","WTF", 50, ":/");
    	  Event ev2 = new Event(a2,d2,ac2,e2,"bigger","WTF", 50, ":/");
    	  
    	  Event_Instance ei1 = new Event_Instance("1",ev1);
    	  Event_Instance ei2 = new Event_Instance("2",ev2);
    	  
    	  
    	  a1.setEvents(ev1);
    	  a2.setEvents(ev2);
    	  
    	  e1.setEvents(ev1);
    	  e2.setEvents(ev2);
    	  
    	  d1.setEvents(ev1);
    	  d2.setEvents(ev2);
    	  
    	  ac1.setEvents(ev1);
    	  ac2.setEvents(ev2);
    	  
    	  ev1.setEvent_instances(ei1);
    	  ev2.setEvent_instances(ei2);
    	  
    	  
    	  eve.save(ev1);
    	  eve.save(ev2);
    	  
    	  act.save(ac1);
    	  act.save(ac2);
    	  
    	  app.save(a1);
    	  app.save(a2);
    	  
    	  evSev.save(e1);
    	  evSev.save(e2);
    	  
    	  defSev.save(d1);
    	  defSev.save(d2);
    	  
    	  
    	  
    	  
    	  
    	  
    	  
    	  eveIns.save(ei1);
    	  eveIns.save(ei2);
    	  
    	  
      };
    } 
	
	
	
	

}

