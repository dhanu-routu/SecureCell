package com.sc.service;

import com.sc.model.RequestData;
import com.sc.model.ResponseData;

public interface Process 
{
	public ResponseData processPendingMessages();
  
	public ResponseData processRegistration();
   
	public ResponseData processSMSCodeRequest();
  
	public ResponseData processSMSCodeValidate(RequestData data);
  
	public ResponseData processInviteRequest(RequestData data);
   
	public ResponseData processInviteRecipientResponse(RequestData data);
  
	public ResponseData processInviteInitiatorResponse(RequestData data);
   
	public ResponseData processSendMessage(RequestData data);
   
	public ResponseData processFetchMessage(RequestData data);
   
	public ResponseData processDeleteRequest(RequestData data);
  
	public ResponseData processUpdatePublicKey(RequestData data);
   
	public ResponseData processUpdatePushID(RequestData data);
  
	public ResponseData processDeleteContact(RequestData data);
   
	public ResponseData processDeleteContactResponse(RequestData data);
   
	public ResponseData processMessageAcknowledgement(RequestData data);
  
	public ResponseData processDeleteResponse(RequestData data);
  
	public ResponseData processDeleteAcknowledgement(RequestData data);
 
	public ResponseData processLicenseCheck(RequestData data);
  
    public ResponseData processMessageRead(RequestData data);
  
    public ResponseData processMessageDelete(RequestData data);
   
    public ResponseData processSendWebKey(RequestData data);
  
    public ResponseData processGetAffiliateContacts(RequestData data);
  
    public ResponseData processSendAffiliateContactByUID(RequestData data);
 
    public ResponseData processSendAffiliateContactByPN(RequestData data);
 
    public ResponseData processGetUIDByPN(RequestData data);
 
    public ResponseData processDeleteByMessageId(RequestData data);
 
    public ResponseData processMessageDeleteResponse(RequestData data);
 
    public ResponseData processMessageDeleteClose(RequestData data);

    public ResponseData processRetentionPeriod(RequestData data);
  
    public ResponseData processSubscription(RequestData data);
 
    public ResponseData processLoginCreation(RequestData data);
  
    public ResponseData processRestoreSubscription(RequestData data);

    public ResponseData processResetPasswd(RequestData data);
 
    public ResponseData processResetVerify(RequestData data);
   
    public ResponseData processChangePasswd(RequestData data);

	public ResponseData doNothing();

}
