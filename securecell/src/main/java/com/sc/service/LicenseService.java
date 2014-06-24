package com.sc.service;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

import com.sc.dao.SecureDAO;
import com.sc.model.LicenceDetails;
import com.sc.model.LicenseResponse;
import com.sc.model.RequestData;
import com.sc.utility.DateTimeUtility;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LicenseService 
{
	private SecureDAO dao;
	public LicenseService(SecureDAO dao)
	{
		this.dao=dao;
	}
	public Map<String,Object> processLicense(String uid, String phoneNo,String countryCode)
	{
		String pNumber="";
	      if (!countryCode.equals("1"))
	      {
	         pNumber = countryCode + phoneNo;
	      }
	      else
	      {
	         pNumber = phoneNo;
	      }

	      Map<String,Object> org= new HashMap<String,Object>();
	      // Send the request to License Server
	      //logError("LicenseCheck - Sending request to License server for "+ pNumber);
	      String url = "https://www.gomycell.com/licensecheck.aspx?PhoneNum="+pNumber;
	      //$url = "http://beta.gomycell.com/licensecheck.aspx?PhoneNum=".$pNumber;
	    
	      LicenseResponse licenseRes = getLicenseCheckResponse(url);
	   // logError("LicenseCheck - Received response from License server for "+pNumber+": "+licenseRes);
	      if (licenseRes.getStatus()!=null && licenseRes.getStatus().equalsIgnoreCase("Valid"))
	      {
	    	  org= dao.getOrganizationDetails(licenseRes.getAffiliateName());
	       //  $result = mysql_fetch_assoc(mysql_query("select * from securecell_organizations where orgName=?"));
	         if (org != null)
	         {
	            // Insert license details into SecureCell database
	        	 Long purchaseDate = DateTimeUtility.getTimeStamp();
		         Long expiryDate = purchaseDate + 2592000;
		         LicenceDetails licenceDet = new LicenceDetails();
		         licenceDet.setUid(uid);
		         licenceDet.setPurchaseDate(purchaseDate);
		         licenceDet.setExpiryDate(expiryDate);
		         licenceDet.setAffiliateName(licenseRes.getAffiliateName());
		         licenceDet.setLicenseStatus(1);
		         if (dao.insertLicenceDetails(licenceDet))
		         {
		           
		        	 org.put("subscriberName", licenseRes.getName());
			         org.put("Title", licenseRes.getTitle());
			         return org;
		         }
		         else
		         {
		        	// logError("LicenseCheck - Failed to insert details for ".$pNumber.". Error is ".mysql_error($sql_con));
		        	 org= new HashMap<String,Object>();
		        	 return org;
		         }
	             
	         }
	         else
	         {
	        	 //logError("LicenseCheck - Could not find organization (".$licenseResponse['Affiliate Name'].") in database");
	        	 return org;
	         }
	        
	         
	         
	       //  logError("LicenseCheck - ".$pNumber." license Start date is ".date('D M j G:i:s T Y',$purchaseDate)." and Expiry date is ".date('D M j G:i:s T Y',$expiryDate));
	        //!mysql_query("replace into securecell_license set uid='".$uid."',licenseStatus='1', purchaseDate='".$purchaseDate."',expiryDate='".$expiryDate."', affiliateName='".$licenseResponse['Affiliate Name']."'", $sql_con
	         
	         
	        // logError("LicenseCheck - Inserted license details for ".$pNumber);
	        // $plResult = Array("orgID" => $result['orgID'], "rPeriod" => $result['retentionPeriod'], "subscriberName" => $licenseResponse['Name'], "Title" => $licenseResponse['Title']);
	         
	      }
	      else
	      {
	         //logError("LicenseCheck - License check with license server failed for ".$pNumber);
	         return org;
	      }
	}
	
	private LicenseResponse getLicenseCheckResponse(String url)
	{
		LicenseResponse lResponse = new LicenseResponse();
		try
		{
   		 
			Client client = Client.create();
	 
			WebResource webResource = client.resource(url);
	 
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	 
			if (response.getStatus() != 200) 
			{
			   throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
			}
	 
			String output = response.getEntity(String.class);
	 
			JSONObject jsonObj = new JSONObject(output);
			
			lResponse.setStatus(jsonObj.getString("Status"));
			lResponse.setAffiliateName(jsonObj.getString("Affiliate Name"));
			lResponse.setName(jsonObj.getString("Name"));
			lResponse.setTitle(jsonObj.getString("Title"));
			
		} 
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return lResponse;
	}
}
