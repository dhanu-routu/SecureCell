package com.sc.model;

public class RegistrationResponse extends ResponseData
{
	
	private String  webKey;
	private String	sData;
	private String	rPeriod;
	private String	virtualNumber;
	
	
	public String getWebKey() {
		return webKey;
	}
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}
	public String getsData() {
		return sData;
	}
	public void setsData(String sData) {
		this.sData = sData;
	}
	public String getrPeriod() {
		return rPeriod;
	}
	public void setrPeriod(String rPeriod) {
		this.rPeriod = rPeriod;
	}
	public String getVirtualNumber() {
		return virtualNumber;
	}
	public void setVirtualNumber(String virtualNumber) {
		this.virtualNumber = virtualNumber;
	}	
	 
}
