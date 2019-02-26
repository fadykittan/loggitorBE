package com.loggitorBE.loggitorBE.web;

import java.io.IOException;
import java.math.BigInteger;

import org.json.JSONException;
import org.json.JSONObject;

import com.loggitorBE.loggitorBE.JsonReader;

public class AccessUser {

	
	//private JSONArray jsonArr;
	private String baseUrlId = "https://adminfinal5.herokuapp.com/getUserIdByEmail/";
	private JsonReader jsonReader;
	private String baseUrlEmail = "https://adminfinal5.herokuapp.com/emailByUser/";
	
	
	
	public AccessUser() {
		super();
		jsonReader = new JsonReader();
		// TODO Auto-generated constructor stub
	}


	private long getJSONfromURL(String email) throws JSONException, IOException
	{
		String url = baseUrlId + email;
		JSONObject json = jsonReader.read_JSONObject_FromUrl(url);
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
		System.out.println("Print URL in getJSONemailFromURL: " + url);
		JSONObject json = jsonReader.read_JSONObject_FromUrl(url);
		System.out.println("Print Json in AccessUser: " + json.toString());
		try {
			return json.getString("email");
		} 
		catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public String getEmailById(BigInteger id) throws JSONException, IOException
	{
		return this.getJSONemailFromURL(id);
	}
	
}
