package com.loggitorBE.loggitorBE.web;

import java.io.IOException;
import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONException;

import com.loggitorBE.loggitorBE.JsonReader;

public class GetUser {

	
	private static JSONArray jsonArr;
	private static String url = "https://adminfinal5.herokuapp.com/emails";
	public static void getJSONfromURL(String app, String severity, Date date) throws JSONException, IOException
	{
		System.out.println(date.toString());
		jsonArr = JsonReader.readJsonFromUrl(url);

	}
	
	
	/*
	public static int getNext()
	{
		int p;
		p = jsonArr.getJSONObject(i).getInt("percentage");
		i++;
		return p;
	}
	*/
	
}
