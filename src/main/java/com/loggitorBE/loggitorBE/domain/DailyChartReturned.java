package com.loggitorBE.loggitorBE.domain;

/*
 * POJO
 */
public class DailyChartReturned {

	
	private String name;
	private float cri;
	private float war;
	private float err;
	
	
	public DailyChartReturned() {}


	public DailyChartReturned(String name, float cri, float war, float err) {
		super();
		this.name = name;
		this.cri = cri;
		this.war = war;
		this.err = err;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public float getCri() {
		return cri;
	}


	public void setCri(float cri) {
		this.cri = cri;
	}


	public float getWar() {
		return war;
	}


	public void setWar(float war) {
		this.war = war;
	}


	public float getErr() {
		return err;
	}


	public void setErr(float err) {
		this.err = err;
	}
	
	
	
	
	
	
	
	
}
