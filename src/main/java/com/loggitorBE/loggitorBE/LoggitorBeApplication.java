package com.loggitorBE.loggitorBE;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

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
import com.loggitorBE.loggitorBE.domain.DefinedEventRepo;
import com.loggitorBE.loggitorBE.domain.EventInstance;
import com.loggitorBE.loggitorBE.domain.EventInstanceRepo;
import com.loggitorBE.loggitorBE.domain.EventSeverity;
import com.loggitorBE.loggitorBE.domain.EventSeverityRepo;
import com.loggitorBE.loggitorBE.domain.FixAction;
import com.loggitorBE.loggitorBE.domain.FixActionRepo;
import com.loggitorBE.loggitorBE.web.Email;

@SpringBootApplication
public class LoggitorBeApplication {

	private static final Logger logger = LoggerFactory.getLogger(LoggitorBeApplication.class);

	@Autowired
	private AppRepo app;
	@Autowired
	private EventSeverityRepo evSev;
	@Autowired
	private DefectSeverityRepo defSev;
	@Autowired
	private FixActionRepo act;
	@Autowired
	private DefinedEventRepo eve;
	@Autowired
	private EventInstanceRepo eveIns;

	public static void main(String[] args) throws AddressException, MessagingException {
		SpringApplication.run(LoggitorBeApplication.class, args);
		logger.info("Hello Sping Boot!");
		String[] to = {"fady.93.fk@gmail.com"};
        Email.sendEmailMessage(to, "test", "hi");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			//eveIns.deleteAll();

			App a1 = new App("BLM", "core");
			App a2 = new App("CLM", "core");

			EventSeverity e1 = new EventSeverity("critical");
			EventSeverity e2 = new EventSeverity("error");

			DefectSeverity d1 = new DefectSeverity("d Critical");
			DefectSeverity d2 = new DefectSeverity("d error");

			FixAction ac1 = new FixAction("SMS");
			FixAction ac2 = new FixAction("email");

			DefinedEvent ev1 = new DefinedEvent(50, "bigger", "WTF1", "idk1");
			DefinedEvent ev2 = new DefinedEvent(80, "smaller", "WTF2", "idk2");

			EventInstance ei1 = new EventInstance("1");
			EventInstance ei2 = new EventInstance("2");

			/*
			 * Set<DefinedEvent> l1 = new HashSet<DefinedEvent>(); l1.add(ev1);
			 * Set<DefinedEvent> l2 = new HashSet<DefinedEvent>(); l2.add(ev2);
			 */

			ac1.setDefinedEvent(ev1);
			ac2.setDefinedEvent(ev2);

			d1.setDefinedEvent(ev1);
			d2.setDefinedEvent(ev2);

			ev1.setFixAction(ac1);
			ev2.setFixAction(ac2);

			ev1.setDefectSev(d1);
			ev2.setDefectSev(d2);

			e1.setDefinedEvent(ev1);
			e2.setDefinedEvent(ev2);

			ev1.setEventSev(e1);
			ev2.setEventSev(e2);

			a1.setDefinedEvent(ev1);
			a2.setDefinedEvent(ev2);

			ev1.setApp(a1);
			ev2.setApp(a2);

			ei1.setOccurredEvent(ev1);
			ei2.setOccurredEvent(ev2);

			ev1.setEventInstance(ei1);
			ev2.setEventInstance(ei2);

			defSev.save(d1);
			defSev.save(d2);

			act.save(ac1);
			act.save(ac2);

			evSev.save(e1);
			evSev.save(e2);

			app.save(a1);
			app.save(a2);

			eve.save(ev1);
			eve.save(ev2);

			eveIns.save(ei1);
			eveIns.save(ei2);
		};
	}

}
