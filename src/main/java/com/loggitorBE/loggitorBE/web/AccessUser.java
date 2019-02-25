package com.loggitorBE.loggitorBE.web;

import java.io.IOException;
import java.math.BigInteger;

import org.json.JSONException;
import org.json.JSONObject;

import com.loggitorBE.loggitorBE.JsonReader;

public class AccessUser {

	
	//private JSONArray jsonArr;
	private String baseUrlId = "https://adminfinal5.herokuapp.com/getUserIdByEmail/";
	
	private String baseUrlEmail = "https://adminfinal5.herokuapp.com/emailByUser/";
	
	
	public AccessUser() {
		super();
		// TODO Auto-generated constructor stub
	}


	private long getJSONfromURL(String email) throws JSONException, IOException
	{
		String url = baseUrlId + email;
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
	
	//////////////////////////////////////////////////////////////////////////////////
	
	private String getJSONemailFromURL(BigInteger id) throws JSONException, IOException
	{
		String url = baseUrlEmail + id;
		JSONObject json = JsonReader.read_JSONObject_FromUrl(url);
		return json.getString("email");
	}
	
	public String getEmailById(BigInteger id) throws JSONException, IOException
	{
		return this.getJSONemailFromURL(id);
	}
	
}
