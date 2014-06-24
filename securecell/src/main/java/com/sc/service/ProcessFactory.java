package com.sc.service;

import com.sc.dao.SecureDAO;
import com.sc.dao.SecureDAOImpl;
import com.sc.model.RequestData;

public class ProcessFactory 
{
	private static Process process;
	public static Process getProcess(RequestData data)
	{
		SecureDAO dao= new SecureDAOImpl();
		process=new ProcessImpl(dao,data);
		
		return process;
	}
}
