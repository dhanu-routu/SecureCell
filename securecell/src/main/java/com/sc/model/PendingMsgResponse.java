package com.sc.model;

import java.util.List;
import java.util.Map;

public class PendingMsgResponse extends ResponseData
{
	private List<Map<String,Object>> pendingList;
	private Long cTime;
	

	public List<Map<String,Object>> getPendingList() {
		return pendingList;
	}

	public void setPendingList(List<Map<String,Object>> pendingList) {
		this.pendingList = pendingList;
	}

	public Long getcTime() {
		return cTime;
	}

	public void setcTime(Long cTime) {
		this.cTime = cTime;
	}
	
	
}
