package com.loggitorBE.loggitorBE.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.loggitorBE.loggitorBE.JsonReader;
import com.loggitorBE.loggitorBE.admin.domain.UserRepository;

public class AccessUser {

	
	//private JSONArray jsonArr;
	private String baseUrl = "https://adminfinal5.herokuapp.com/emails/";
	
	
	public AccessUser() {
		super();
		// TODO Auto-generated constructor stub
	}


	private long getJSONfromURL(String email) throws JSONException, IOException
	{
		String url = baseUrl + email;
		JSONArray jsonArr = JsonReader.readJsonFromUrl(url);
		return jsonArr.getJSONObject(0).getLong("id");
	}
	
	
	public long getIdByEmail(String email, UserRepository userRepo)
	{
		ArrayList<BigInteger> userID = userRepo.findByUserName(email);
		return userID.get(0).longValue();
	}
	
}
