package com.sc.model;

public class FetchMessageResponse extends ResponseData
{
	private String from;
	private String messageId;
	private String content;
	private String rid;
	private String mSize;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getmSize() {
		return mSize;
	}
	public void setmSize(String mSize) {
		this.mSize = mSize;
	}

}
