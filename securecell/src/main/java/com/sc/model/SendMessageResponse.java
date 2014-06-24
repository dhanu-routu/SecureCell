package com.sc.model;

import java.util.List;

public class SendMessageResponse extends ResponseData 
{
	List<String> invalidRecipients;

	public List<String> getInvalidRecipients() {
		return invalidRecipients;
	}

	public void setInvalidRecipients(List<String> invalidRecipients) {
		this.invalidRecipients = invalidRecipients;
	}
	
	
}
