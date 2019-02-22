package com.loggitorBE.loggitorBE.web;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.loggitorBE.loggitorBE.JsonReader;

public class AccessUser {

	
	//private JSONArray jsonArr;
	private String baseUrl = "https://adminfinal5.herokuapp.com/getUserIdByEmail/";
	
	
	public AccessUser() {
		super();
		// TODO Auto-generated constructor stub
	}


	private long getJSONfromURL(String email) throws JSONException, IOException
	{
		String url = baseUrl + email;
		JSONObject json = JsonReader.read_JSONObject_FromUrl(url);
		return json.getLong("id");
//		JSONArray jsonArr = JsonReader.readJsonFromUrl(url);
//		return jsonArr.getJSONObject(0).getLong("id");
	}
	
	
	public long getIdByEmail(String email) throws JSONException, IOException//, UserRepository userRepo)
	{
		return this.getJSONfromURL(email);
//		ArrayList<BigInteger> userID = userRepo.findByUserName(email);
//		return userID.get(0).longValue();
	}
	
}
