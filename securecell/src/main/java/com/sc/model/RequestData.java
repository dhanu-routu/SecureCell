package com.sc.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class RequestData {

	@JsonProperty("Type")
	private int type;
	private String uid;
	private String phoneNumber;
	private String countryCode;
	private String publicKey;
	private String phoneType;
	private String OSVersion;
	private String pushID;
	private String retry;
	private String appVersion;
	private String status;
	private String sender;
	private String smsCode;
	private String recipient; 
	private List<Message> messageList;
	private String messageId;
	private List<String> recipientList;
	private int mode;
	private List<DeleteId> deleteIdList;
	private List<String> messageIdList;
	private String contact;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getOSVersion() {
		return OSVersion;
	}
	public void setOSVersion(String oSVersion) {
		OSVersion = oSVersion;
	}
	public String getPushID() {
		return pushID;
	}
	public void setPushID(String pushID) {
		this.pushID = pushID;
	}
	public String getRetry() {
		return retry;
	}
	public void setRetry(String retry) {
		this.retry = retry;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public List<Message> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public List<String> getRecipientList() {
		return recipientList;
	}
	public void setRecipientList(List<String> recipientList) {
		this.recipientList = recipientList;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public List<DeleteId> getDeleteIdList() {
		return deleteIdList;
	}
	public void setDeleteIdList(List<DeleteId> deleteIdList) {
		this.deleteIdList = deleteIdList;
	}
	public List<String> getMessageIdList() {
		return messageIdList;
	}
	public void setMessageIdList(List<String> messageIdList) {
		this.messageIdList = messageIdList;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
