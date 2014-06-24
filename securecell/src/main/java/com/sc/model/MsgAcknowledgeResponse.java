package com.sc.model;

import java.util.List;

public class MsgAcknowledgeResponse extends ResponseData 
{
	private List<String> messageIdList;

	public List<String> getMessageIdList() {
		return messageIdList;
	}

	public void setMessageIdList(List<String> messageIdList) {
		this.messageIdList = messageIdList;
	}
}
