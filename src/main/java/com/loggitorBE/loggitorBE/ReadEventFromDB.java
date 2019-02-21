package com.loggitorBE.loggitorBE;

import java.io.IOException;
import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONException;

public class ReadEventFromDB {

	private JSONArray jsonArr;
	private int i=0;
	private String baseUrl = "https://amdocstask.herokuapp.com/SeverityAppPercent/";
	
	
	
	
	public ReadEventFromDB() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void getJSONfromURL(String app, String severity, Date date) throws JSONException, IOException
	{
		System.out.println(date.toString());
		String url = baseUrl + app + "/" + severity + "/" + date;
		jsonArr = JsonReader.readJsonFromUrl(url);
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
