package com.sc.dao;

import java.util.List;
import java.util.Map;

import com.sc.model.LicenceDetails;


public interface SecureDAO
{
	// Pending List Process
	public boolean isRegisteredUser(String uId);
	
	public List<Map<String,Object>> getMessagesFromMessageStore(String uId);
	
	public List<Map<String,Object>> getDeliveredReadMessages(String uId);
	
	public List<Map<String,Object>> getInvitesFromInviteStore(String uId);
	
	public List<Map<String,Object>> getResponseFromInviteStore(String uId);
	
	public List<Map<String,Object>> getEntriesFromRemoteDeleteStore(String uId);
	
	public List<Map<String,Object>> getResponsesFromRemoteDeleteStore(String uId);
	
	public List<Map<String,Object>> getEntriesFromContactDeleteStore(String uId);
	
	public List<Map<String,Object>> getRequestsFromMessageDeleteStore(String uId);
	
	public List<Map<String,Object>> getResponsesFromMessageDeleteStore(String uId);

		//Registration process
	
	public Map<String, Object> getOrganizationDetails(String orgName);

	public boolean insertLicenceDetails(LicenceDetails licenceDet);
	
	public String getVirtualNumberByPhNo(String phNo);

	public String getOldUid(String phoneNo);

	public boolean removeExistingUser(String vNumber);
	public boolean removeExistingUser(String uid,String vNumber);

	public String getVirtualNumberByAreaCode(String areaCode);
}
