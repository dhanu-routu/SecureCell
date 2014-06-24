package com.sc.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.sc.dao.SecureDAO;
import com.sc.model.PendingMsgResponse;
import com.sc.model.RegistrationResponse;
import com.sc.model.RequestData;
import com.sc.model.ResponseData;
import com.sc.utility.DataUtil;
import com.sc.utility.DateTimeUtility;

public class ProcessImpl implements Process {

	private SecureDAO dao;
	private RequestData data;
	public ProcessImpl(SecureDAO dao, RequestData data)
	{
		this.dao=dao;
		this.data=data;
	}

	@Override
	public ResponseData processPendingMessages()
	{
		String uId=data.getUid();
		List<Map<String,Object>> pendingList= new ArrayList<Map<String,Object>>();
				
		if(dao.isRegisteredUser(uId))
		{
			 System.out.println("This is Registered Phone number.");
			 pendingList.addAll(dao.getMessagesFromMessageStore(uId));
			 pendingList.addAll(dao.getDeliveredReadMessages(uId));
			 pendingList.addAll(dao.getInvitesFromInviteStore(uId));
			 pendingList.addAll(dao.getResponseFromInviteStore(uId));
			 pendingList.addAll(dao.getEntriesFromRemoteDeleteStore(uId));
			 pendingList.addAll(dao.getResponsesFromRemoteDeleteStore(uId));
			 pendingList.addAll(dao.getEntriesFromContactDeleteStore(uId));
			 pendingList.addAll(dao.getRequestsFromMessageDeleteStore(uId));
			 pendingList.addAll(dao.getResponsesFromMessageDeleteStore(uId));
			 
		}
		
		List<Map<String,Object>> sortedPendingList = DataUtil.sortList(pendingList , "time");
		
		PendingMsgResponse data= new PendingMsgResponse();
		data.setType(0);
		data.setStatus(1);
		data.setcTime(DateTimeUtility.getTimeStamp());
		data.setPendingList(sortedPendingList);
		return data;
	}

	@Override
	public ResponseData processRegistration() 
	{
		RegistrationResponse regResponse;
		String phoneType= data.getPhoneType();
		RegistrationService regService = new RegistrationService(data, dao);
		
		if(phoneType.equalsIgnoreCase("aTab") || phoneType.equalsIgnoreCase("iPadPro"))
		{
			regResponse = regService.registerTablet();
		}
		else if(phoneType.equalsIgnoreCase("iPhone") )
		{
			
		}
		else
		{
			
		}
		return null;
	}

	@Override
	public ResponseData processSMSCodeRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processSMSCodeValidate(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processInviteRequest(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processInviteRecipientResponse(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processInviteInitiatorResponse(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processSendMessage(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processFetchMessage(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processDeleteRequest(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processUpdatePublicKey(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processUpdatePushID(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processDeleteContact(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processDeleteContactResponse(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processMessageAcknowledgement(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processDeleteResponse(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processDeleteAcknowledgement(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processLicenseCheck(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processMessageRead(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processMessageDelete(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processSendWebKey(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processGetAffiliateContacts(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processSendAffiliateContactByUID(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processSendAffiliateContactByPN(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processGetUIDByPN(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processDeleteByMessageId(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processMessageDeleteResponse(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processMessageDeleteClose(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processRetentionPeriod(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processSubscription(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processLoginCreation(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processRestoreSubscription(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processResetPasswd(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processResetVerify(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData processChangePasswd(RequestData data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseData doNothing() {
		// TODO Auto-generated method stub
		return null;
	}

}
