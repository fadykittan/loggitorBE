package com.loggitorBE.loggitorBE.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loggitorBE.loggitorBE.LoggitorBeApplication;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;

public class SMS {
	private static final Logger logger = LoggerFactory.getLogger(LoggitorBeApplication.class);
	
	public static String msgsend() {

		try {
			// Construct data
			String apiKey = "apikey=" + "XJGctSkfMrU-pZXjeHw3GhS8wGgOP3gWCaNdbneBfS";
			String message = "&message=" + "This is your message";
			String sender = "&sender=" + "Jims Autos";
			String numbers = "&numbers=" + "+9725151592";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}

	public static void smsSend() throws IOException, NexmoClientException {
		NexmoClient client = new NexmoClient.Builder().apiKey("c2d4480c").apiSecret("lzgyKIVeeApqo8YG").build();

		String messageText = "Hello from action system\n";
	
		TextMessage message = new TextMessage("ActionSystem", "972525151592", messageText);
		//TextMessage message = new TextMessage("Action System", "972526840315", messageText);
		
		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
		for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) 
			System.out.println(responseMessage);
		
		logger.info("SMS sent successfully.");
	}

}
