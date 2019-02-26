package com.loggitorBE.loggitorBE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

	
	
	
	
	
	public JsonReader() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray json = new JSONArray(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public JSONObject read_JSONObject_FromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			if(jsonText.equals(""))
			{
				System.out.println("Print JSON in read_JSONObject_FromUrl: " + jsonText);
				jsonText = "{}";
			}
			JSONObject json = new JSONObject(jsonText);
			System.out.println("Print Json in JsonReader: " + jsonText.toString());
			return json;
		} finally {
			is.close();
		}
	}

}
