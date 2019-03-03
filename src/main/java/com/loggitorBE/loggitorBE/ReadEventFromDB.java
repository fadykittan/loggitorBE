package com.loggitorBE.loggitorBE;

import java.io.IOException;
import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONException;

public class ReadEventFromDB {

	private JSONArray jsonArr;
	private int i=0;
	private String baseUrl = "https://amdocslogfiles.herokuapp.com/";
	private JsonReader jsonReader;
	
	
	
	public ReadEventFromDB() {
		super();
		jsonReader = new JsonReader();
		// TODO Auto-generated constructor stub
	}


	public void getJSONfromURL(String appName, String appType, String severity, Date date) throws JSONException, IOException
	{
		System.out.println(date.toString());
		String url = baseUrl + appName + "/" + severity + "/" + date;
		//String url = baseUrl + appName + "/" + severity + "/" + "2019-02-28";
		jsonArr = jsonReader.readJsonFromUrl(url);
		System.out.println("Print JSON: " + jsonArr.toString());
		//jsonArr = JsonReader.readJsonFromUrl("https://amdocstask.herokuapp.com/SeverityAppPercent/BLM/Error/2019-02-15");
		i = 0;

	}
	
	
	public float getNext()
	{
		float p;
		String strP;
		strP = jsonArr.getJSONObject(i).getString("percentage");
		strP = strP.substring(0, strP.indexOf("%"));

		p = Float.parseFloat(strP);

		i++;
		return p;
	}
	
	public boolean hasNext()
	{
		if(i < jsonArr.length())
			return true;
		else 
			return false;
	}
	
	
	public void close()
	{
		i=0;
		jsonArr = null;
	}
	
	
}
