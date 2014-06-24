package com.sc.utility;

import java.util.Comparator;
import java.util.Map;

public class PendingMessageComparator implements Comparator<Map<String,Object>> {

	private String paramKey;
	public PendingMessageComparator(String paramKey)
	{
		this.paramKey = paramKey;
	}
	@Override
	public int compare(Map<String, Object> pending1, Map<String, Object> pending2)
	{
		if(pending1.get(paramKey)!= null && pending2.get(paramKey)!= null)
		{
			String time1 = (String) pending1.get(paramKey);
			String time2 = (String) pending2.get(paramKey);
			
			if(time1.compareTo(time2)>0)
			{
				return 1;
			}
			else if(time1.compareTo(time2)<0)
			{
				return -1;
			}
			else return 0;
		}
		else if(pending1.get(paramKey)!= null && pending2.get(paramKey)== null)
		{
			return 1;
		}
		else if(pending1.get(paramKey)== null && pending2.get(paramKey)!= null)
		{
			return -1;
		}
		else return 0;
	}

}
