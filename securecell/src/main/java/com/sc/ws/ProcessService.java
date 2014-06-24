package com.sc.ws;



import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sc.model.FetchMessageResponse;
import com.sc.model.RequestData;
import com.sc.model.ResponseData;

@Path("/new")
public class ProcessService {

	

	@POST
	@Path("/process")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseData processTheTask(RequestData requestData) {

		ProcessHelper helper=new ProcessHelper(requestData);
		ResponseData dt =helper.getProcessResponse();
		 dt =new FetchMessageResponse();
		return dt;
		
	}
	
	
}