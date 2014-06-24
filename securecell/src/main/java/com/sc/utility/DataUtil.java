package com.sc.utility;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class DataUtil 
{

	public static List<Map<String, Object>> sortList(List<Map<String, Object>> pendingMessages, String keyParam) 
	{
		 Collections.sort(pendingMessages,new PendingMessageComparator(keyParam));
		 return pendingMessages;
	}

	public static String getPhoneNo(String phoneNoAndCode) 
	{
		String phoneNo = phoneNoAndCode.substring(phoneNoAndCode.length()-10);
		return phoneNo;
	}

	public static String getCountryCode(String phoneNoAndCode)
	{
		String countryCode = phoneNoAndCode.substring(0,phoneNoAndCode.length()-10);
		return countryCode;
	}

	public static String generateRandomCode(int n)
	{
		Random random = new Random();
		String randomCode="";
		String arr[] = {"0","1","2","3","4","5","6","7","8","9"};
		while(randomCode.length()<n)
		{
			String randomNo = arr[random.nextInt(10)];
			if(randomCode.indexOf(randomNo)==-1)
			{
				randomCode+=randomNo;
			}
		}
		return randomCode;
	}

}
