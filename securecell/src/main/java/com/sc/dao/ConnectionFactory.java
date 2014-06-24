package com.sc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

import com.sc.utility.PropUtility;

public class ConnectionFactory 
{
	private static Connection conn;
	public static Connection createConnection()
	{
		if(conn ==null)
		{
			try 
			{
				conn= getDataSource().getConnection();
			}
			catch (SQLException e) {
				System.out.println(e);
			}
		}
		return conn;
	}
	
	private static BasicDataSource getDataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		Properties prop=PropUtility.getProperties("database");
			 
		dataSource.setDriverClassName(prop.getProperty("sc.db.driverClass"));
		dataSource.setUrl(prop.getProperty("sc.db.connectionURL"));
		dataSource.setUsername(prop.getProperty("sc.db.userName"));
		dataSource.setPassword(prop.getProperty("sc.db.password"));
		return dataSource;
	}
}
