package com.sc.model;

import java.util.List;

public class DeleteResponse extends ResponseData
{
	private List<String> invalidRecipients;

	public List<String> getInvalidRecipients() {
		return invalidRecipients;
	}

	public void setInvalidRecipients(List<String> invalidRecipients) {
		this.invalidRecipients = invalidRecipients;
	}
}
