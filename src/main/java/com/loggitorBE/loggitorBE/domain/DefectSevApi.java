package com.loggitorBE.loggitorBE.domain;

public class DefectSevApi {
	
	// pojo class that define the list of defect severities

	
	String defSev;
	
	public DefectSevApi() {}
	
	public DefectSevApi(String defSev) {
		
		this.defSev=defSev;
	}

	public String getDefSev() {
		return defSev;
	}

	public void setDefSev(String defSev) {
		this.defSev = defSev;
	}


}
