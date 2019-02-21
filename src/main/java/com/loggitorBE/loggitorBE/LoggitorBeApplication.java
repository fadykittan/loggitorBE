package com.loggitorBE.loggitorBE;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nexmo.client.NexmoClientException;

@SpringBootApplication
public class LoggitorBeApplication {

	private static final Logger logger = LoggerFactory.getLogger(LoggitorBeApplication.class);

	/*@Autowired
	private DefinedEventRepo definedEveRepo;
	@Autowired
	private EventInstanceRepo eventInsRepo;*/

	
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
			 *
			SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			while(true) {
				
				
				
				System.out.println("Enter Sleep on: " + timeformat.format(Calendar.getInstance().getTime()));
				//Sleep for an hour
				Thread.sleep(60 * 60 * 1000);
				
				
				
			}*/
			
			
		};
	}
	
	
	
	
	
	
	
	
	
	
	

}
