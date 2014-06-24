package com.sc.model;

public class InviteInitiatorResponse extends ResponseData
{
	private String recipient; 
	private String recipientUID;
	private String recipientPublicKey;
	private String rid;
	private String countryCode;
	
	
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getRecipientUID() {
		return recipientUID;
	}
	public void setRecipientUID(String recipientUID) {
		this.recipientUID = recipientUID;
	}
	public String getRecipientPublicKey() {
		return recipientPublicKey;
	}
	public void setRecipientPublicKey(String recipientPublicKey) {
		this.recipientPublicKey = recipientPublicKey;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
