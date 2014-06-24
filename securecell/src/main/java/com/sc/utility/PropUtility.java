package com.sc.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropUtility
{
	private static final String DBCONFIG="dbconfig.properties";
	private static final String IPHONECONFIG="iphoneconfig.properties";
	
	public static Properties getProperties(String configName)
	{
	    InputStream inputStream = null;
		Properties prop = new Properties();
		
		try
		{
			inputStream= PropUtility.class.getClassLoader().getResourceAsStream(getConfigFile(configName));
			prop.load(inputStream);
		}
		catch (IOException e) 
		{
			System.out.println(e);
		}
		catch (NullPointerException e)
		{
			System.out.println(e);
		}
		
		return prop;
	}

	private static String getConfigFile(String configName)
	{
		if(configName.equalsIgnoreCase("database"))
		{
			return DBCONFIG;
		}
		else if(configName.equalsIgnoreCase("iphone"))
		{
			return IPHONECONFIG;
		}
		else return null;
	}
}
