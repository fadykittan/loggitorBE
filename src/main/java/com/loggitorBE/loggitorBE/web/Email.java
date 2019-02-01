package com.loggitorBE.loggitorBE.web;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loggitorBE.loggitorBE.LoggitorBeApplication;

public class Email 
{
	private static final Logger logger = LoggerFactory.getLogger(LoggitorBeApplication.class);
	private static String emailHost = "smtp.gmail.com";
	private static String fromUser = "loggitor.action.system";// just the id alone without @gmail.com
	private static String fromUserEmailPassword = "0123action";
	
	public static void sendEmailMessage(String[] toEmails,String emailSubject,String emailBody) throws AddressException, MessagingException {
		Session mailSession;
		MimeMessage emailMessage;
		String emailPort = "587";// gmail's smtp port
		Properties emailProperties;
		
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) 
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		
		emailMessage.setSubject(emailSubject);
		// emailMessage.setContent(emailBody, "text/html");// for a html email
		emailMessage.setText(emailBody);// for a text email

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		
		logger.info("Email sent successfully.");
	}
	
	/*
	public static void sendEmailMessage() throws AddressException, MessagingException {
		Session mailSession;
		MimeMessage emailMessage;
		String emailPort = "587";// gmail's smtp port
		Properties emailProperties;
		
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

		String[] toEmails = { "ananghadban@gmail.com" };
		String emailSubject = "Java Email";
		String emailBody = "This is an email sent by JavaMail api.";

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		// emailMessage.setContent(emailBody, "text/html");// for a html email
		emailMessage.setText(emailBody);// for a text email

		String emailHost = "smtp.gmail.com";
		String fromUser = "ananghadban";// just the id alone without @gmail.com
		String fromUserEmailPassword = "0000";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		
		System.out.println("Email sent successfully.");

	}*/
}
