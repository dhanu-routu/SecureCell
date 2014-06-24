package com.sc.service;

import java.util.HashMap;
import java.util.Map;

import com.sc.dao.SecureDAO;
import com.sc.model.RegistrationResponse;
import com.sc.model.RequestData;
import com.sc.utility.DataUtil;

public class RegistrationService 
{
	private RequestData data;
	private SecureDAO dao;
	public RegistrationService(RequestData data,SecureDAO dao)
	{
		this.data = data;
		this.dao = dao;
	}
	
	public RegistrationResponse registerTablet()
	{
		String phoneNoAndCode=data.getPhoneNumber();
		String phoneNo= DataUtil.getPhoneNo(phoneNoAndCode);
		String countryCode = DataUtil.getCountryCode(phoneNoAndCode);
		String uid = data.getUid();
		String publicKey = data.getPublicKey();
		String phoneType = data.getPhoneType();
		String OSVersion = data.getOSVersion();
		String pushID = data.getPushID();
		String retry = data.getRetry();
		String appVersion = data.getAppVersion();

	     // String logMessage = "Registration - PhoneNumber: " + phoneNoAndCode + " UID: " + uid + " Device: " + phoneType + OSVersion + " PushID: "+pushID+" Retry: " +retry;
	     // logError(logMessage);
		Map<String,Object> responseMsg = new HashMap<String,Object>();
		 // Validate the device type
	      if (!phoneType.equalsIgnoreCase("iPadPro"))
	      {
	    	  responseMsg.put("errCode",1);
	          //logError("Registration - Invalid device type for "+$phoneType);
	      }

	      // Process the license
	      LicenseService licenseService = new LicenseService(dao);
	      Map<String,Object> plResult =licenseService.processLicense(uid, phoneNo, countryCode);
	      if (plResult==null)
	      {
	    	  responseMsg.put("errCode",2);
	       // logError("Registration - License check failed for "+phoneNo);
	      }

	     String activationCode = null;
	     String oldUid = null;

	      // Verify if a Virtual Number already exists for this Phone Number
	      String vNumber = dao.getVirtualNumberByPhNo(phoneNo);
	      if (vNumber != null)
	      {
	         if(data.getType()== 3)
	         {
	            // Get the old UID, to remove all obsolete entries based on the old UID
	        	 oldUid = dao.getOldUid(phoneNo);
	        	
	            if (data.getSmsCode()==null)
	            {
	               responseMsg.put("errCode",5);
	              // logError("Registration - Activation code not received for "+vNumber+" ("+phoneNo+")");
	            }
	              activationCode = data.getSmsCode();

	            // Remove existing entries, if re-registration of the same phone number with different device
	              dao.removeExistingUser(vNumber);
	         }
	         else
	         {
	            // Remove existing entries, if re-registration of the same phone number with same device
	            //logError("Registration - Virtual Number("+vNumber+") already exists for "+phoneNo);
	            dao.removeExistingUser(uid,vNumber);

	            // Generate 8 digit Activation Code
	             activationCode = DataUtil.generateRandomCode(6);
	         }
	      }
	      else
	      {
	         // No Virtual Number exists. This is a new registration, so allocate a Virtual Number
	         $retValue = allocateVirtualNumber($phoneNumber,$countryCode,$sql_con);
	         if (//$retValue['errCode'] != '0')
	         {
	            $responseMsg['errCode'] = $retValue['errCode'];
	            die(json_encode($responseMsg));
	         }
	         $vNumber = $retValue['virtualNumber'];
	         logError("Registration - Virtual Number(".$vNumber.") generated for ".$phoneNumber);
	      }
	                
	      // Set default appVersion, if no appVersion is received
	      if (!$appVersion)
	         setDefaultAppversion($appVersion, $phoneType);

	      // Register the device_token with UrbanAirship
	      registerPushIDWithUA($pushID, $phoneType);

	      // Generate 8 digit Activation Code
	      $activationCode = generateRandomCode(6);

	      // Modify the name for Tablets
	      $plResult['subscriberName'] = $plResult['subscriberName']." (iPad)";

	      // Set title mode
	      $titleMode = '1';
	      if (stripos($plResult['Title'], "Client") || stripos($plResult['Title'], "Patient"))
	         $titleMode = '0';

	      // Store the current data to the database
	      $iQuery = "INSERT INTO securecell_users (phoneNumber, countryCode, uid, publicKey, phoneType, OSVersion, verificationStatus, activationCode, pushID, orgID, subscriberName, Title, appVersion, titleMode) VALUES ('".$vNumber."','".$countryCode."', '".$uid."', '".$publicKey."', '".$phoneType."', '".$OSVersion."', 2, '".$activationCode."', '".$pushID."','".$plResult['orgID']."','".$plResult['subscriberName']."','".$plResult['Title']."','".$appVersion."','".$titleMode."')";

	      if (!mysql_query($iQuery,$sql_con))
	      {
	         $error = mysql_error($sql_con);
	         if (verifyError($error))
	         {
	            $responseMsg['errCode'] = "3";
	         }
	         else
	         {
	            $responseMsg['errCode'] = "4";
	            mysql_query("delete from securecell_virtual where virtualNumber='".$vNumber."'");
	         }
	         logError("Registration - Failed to insert data. ".$error);
	         die(json_encode($responseMsg));
	      }

	      // Respond back to the registration request
	      $responseMsg['virtualNumber'] = $vNumber;
	      $responseMsg['sData'] = getLicenseData($uid, $sql_con);
	      $responseMsg['status'] = "1";
	      $responseMsg['webKey'] = getPublicKey();
	      $responseMsg['rPeriod'] = $plResult['rPeriod'];
	      $responseMsg['Title'] = $plResult['Title'];

	      if ($responseMsg['Type'] == "3")
	         $uid = $uidResult['uid'];

	      // Remove any existing entries from other tables, to take care of re-registration of pre-existing users
	      mysql_query("delete from securecell_messages where toUID='".$uid."'", $sql_con); // Pending messages were encrypted with obsolete public key, so not useful anymore
	      mysql_query("delete from securecell_invites where fromUID='".$uid."' and (requestStatus='2' or requestStatus='3')", $sql_con);
	      mysql_query("delete from securecell_deletes where fromUID='".$uid."' and verificationStatus='2'", $sql_con); 
	      mysql_query("delete from securecell_delete_contacts where toUID='".$uid."'", $sql_con); 

	      logError("Registration - Data inserted for ".$vNumber);
	   }        
	      
	      
	      
	      
		return null;
	}

// Function to allocate a Virtual Number
private Map<String,Object> allocateVirtualNumber(String phoneNo, String countryCode)
{
	Map<String,Object> retValue = new HashMap<String,Object>();
	retValue.put("errCode",0);
	
	String areaCode = phoneNo.substring(0,3);
    String nextNumber = null;
    String vNum = dao.getVirtualNumberByAreaCode(areaCode);
   if (vNum != null)
   {
      nextNumber = (Long.parseLong(vNum)+ 1)+"";
   }
   else
   {
      nextNumber = areaCode+"5550001";
   }

   // Add the Virtual Number to the database
   $insertQuery = "insert into securecell_virtual (phoneNumber,countryCode,areaCode,virtualNumber) values ('".$phoneNumber."','".$countryCode."','".$areaCode."','".$nextNumber."')";
   if (!mysql_query($insertQuery,$sql_con))
   {
      logError("Registration - Failed to insert the generated Virtual Number(".$nextNumber.") for ".$phoneNumber);
      $retValue['errCode'] = "3";
      return $retValue;
   }

   // Return the allocated Virtual Number
   $retValue['virtualNumber'] = $nextNumber;
   return $retValue;
}

}
