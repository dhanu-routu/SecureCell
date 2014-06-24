package com.sc.utility;

import java.util.Calendar;

public class DateTimeUtility
{
	
	public static Long getTimeStamp()
	{
		return Calendar.getInstance().getTimeInMillis();
	}
}
