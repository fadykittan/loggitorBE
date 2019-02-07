package com.loggitorBE.loggitorBE.domain;

import java.math.BigInteger;

public class DefinedEventPOJO {

	private BigInteger id;
	private String comperator;
	private float percent;
	private BigInteger app_id;
	private String app_name;
	private BigInteger def_id;
	private String def_severity;
	private BigInteger action_id;
	private String action_name;
	
	
	// constructor 
	public DefinedEventPOJO() {}
	
	public DefinedEventPOJO(BigInteger id, String comperator, float percent, BigInteger app_id, String app_name,
			BigInteger def_id, String def_severity, BigInteger action_id, String action_name) {
		super();
		this.id = id;
		this.comperator = comperator;
		this.percent = percent;
		this.app_id = app_id;
		this.app_name = app_name;
		this.def_id = def_id;
		this.def_severity = def_severity;
		this.action_id = action_id;
		this.action_name = action_name;
	}

	
	
	// getters and setters
	
	
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getComperator() {
		return comperator;
	}

	public void setComperator(String comperator) {
		this.comperator = comperator;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public BigInteger getApp_id() {
		return app_id;
	}

	public void setApp_id(BigInteger app_id) {
		this.app_id = app_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public BigInteger getDef_id() {
		return def_id;
	}

	public void setDef_id(BigInteger def_id) {
		this.def_id = def_id;
	}

	public String getDef_severity() {
		return def_severity;
	}

	public void setDef_severity(String def_severity) {
		this.def_severity = def_severity;
	}

	public BigInteger getAction_id() {
		return action_id;
	}

	public void setAction_id(BigInteger action_id) {
		this.action_id = action_id;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	

	
	


}
