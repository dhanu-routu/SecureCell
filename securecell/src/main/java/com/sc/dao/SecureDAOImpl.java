package com.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sc.model.LicenceDetails;
import com.sc.utility.DateTimeUtility;

public class SecureDAOImpl implements SecureDAO 
{
	private Connection con=ConnectionFactory.createConnection();
	@Override
	public boolean isRegisteredUser(String uId)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean isUser=false;
		String qry="select phoneNumber from securecell_users where uid=? and verificationStatus=?";
		try
		{
			
			pstmt = con.prepareStatement(qry);
			
			pstmt.setString(1, uId);
			pstmt.setInt(2, 2);
			
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				isUser= true;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		return isUser;
	}

	private void closePreparedStatement(PreparedStatement pstmt)
	{
		try
		{
			pstmt.close();
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
	}

	private void closeResultSet(ResultSet rs)
	{
		try
		{
			rs.close();
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
	}

	private void closeConnection(Connection con) 
	{
		try
		{
			con.close();
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
	}

	@Override
	public List<Map<String,Object>> getMessagesFromMessageStore(String uId) 
	{
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 
		//List<PendingInfo> messages = new ArrayList<PendingInfo>(); 
		 
		List<Map<String,Object>> messages = new ArrayList<Map<String,Object>>(); 
		 
		//String query ="select message, messageId, messageTime, fromUID, dType, messageType, messageSize from securecell_messages where toUID=? and messageStatus=? order by messageTime asc";
		//String query1 ="select phoneNumber,subscriberName,activationCode from securecell_users where uid=? and verificationStatus=?";

		String qry="select message, messageId, messageTime, fromUID, dType, messageType, messageSize,phoneNumber,subscriberName,activationCode from securecell_messages  sm  left outer join securecell_users su on su.uid=sm.fromUID and  su.verificationStatus=? where sm.toUID=? and sm.messageStatus=? order by messageTime asc";
      	try
		{
			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, 2);
			pstmt.setString(2, uId);
			pstmt.setInt(3, 1);
			
			rs = pstmt.executeQuery();
			
			 while (rs.next())
		     {
				 //PendingMessage msgInfo=new PendingMessage();
				  Map<String,Object> msgInfo = new HashMap<String, Object>();
				  				
				  msgInfo.put("Type",8);
				  msgInfo.put("from",rs.getString("fromUID"));
				  msgInfo.put("messageId",rs.getString("messageId"));
				  msgInfo.put("content",rs.getString("message"));
				  msgInfo.put("time",rs.getString("messageTime"));
				  msgInfo.put("dType",rs.getString("dType"));
				  msgInfo.put("mType",rs.getString("messageType"));
				  msgInfo.put("mSize",rs.getString("messageSize"));
				  msgInfo.put("rid",rs.getString("activationCode"));
					  
					  if (rs.getInt("dType") == 5)
			           {
						  msgInfo.put("pNumber",rs.getString("phoneNumber"));
						  msgInfo.put("pName",rs.getString("subscriberName"));
			           }
				 messages.add(msgInfo);
		      }
			  
		}
		catch(Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			
		}
		return messages;
		
	}

	@Override
	public List<Map<String,Object>> getDeliveredReadMessages(String uId) 
	{
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 
		 //List<PendingInfo> messages = new ArrayList<PendingInfo>(); 
		 List<Map<String,Object>> messages = new ArrayList<Map<String,Object>>(); 

		//List<MessageStaus> messageStatusList = new ArrayList<MessageStaus>(); 
		 
		List<Map<String,Object>> messageStatusList = new ArrayList<Map<String,Object>>(); 
		 
		 String qry="select a.messageID, a.messageStatus, b.phoneNumber from securecell_messages a join securecell_users b on a.toUID=b.uid  where fromUID=? order by messageTime asc";
		 
		 try
		 {
				pstmt = con.prepareStatement(qry);
				pstmt.setString(1, uId);
				
				rs = pstmt.executeQuery();
				
				 while(rs.next())
			     {
         			 	//MessageStaus msgStatus = new MessageStaus();
					 Map<String,Object> msgStatus = new HashMap<String,Object>();
					 
					 	msgStatus.put("phoneNumber",rs.getString("phoneNumber"));
					 	msgStatus.put("messageID",rs.getString("messageID"));
					 	msgStatus.put("messageStatus",rs.getString("messageStatus"));
					 	messageStatusList.add(msgStatus);
			     }
		  }
		 catch (Exception e) 
		 {
				System.out.println(e);
				closeResultSet(rs);
				closePreparedStatement(pstmt);
		 }

		// PendingDeliveredMessages info = new PendingDeliveredMessages();
		 Map<String,Object> info = new HashMap<String,Object>();
				 //logError("PendingList - Adding ".count($msgStatusList)." message status items to pending list");
		         if (messageStatusList.size() > 0)
		         {
		        	 info.put("Type",20);
		        	 info.put("msgStatusList",messageStatusList);
		        	 info.put("time",DateTimeUtility.getTimeStamp());
		         }
		         messages.add(info);
		return messages;
	}

	@Override
	public List<Map<String,Object>> getInvitesFromInviteStore(String uId)
	{
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 //List<PendingInfo> pendingInvites = new ArrayList<PendingInfo>();  
		 List<Map<String,Object>> pendingInvites= new ArrayList<Map<String,Object>>();  
		 
		String qry = "select requestTime,fromPhoneNumber from securecell_invites where toUID=? and requestStatus=? order by requestTime asc";
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, uId);
			pstmt.setInt(2,1);
			rs=pstmt.executeQuery();

	        while (rs.next())
	        {
	          //Invites invites= new Invites();
	        	Map<String,Object> 	invites= new HashMap<String,Object>();
	        	
	          invites.put("Type",41);
	          invites.put("inviteList",rs.getString("fromPhoneNumber"));
	          invites.put("time",rs.getString("requestTime"));
	          pendingInvites.add(invites);
	        }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		} 
		return pendingInvites;
	}

	@Override
	public List<Map<String,Object>> getResponseFromInviteStore(String uId)
	{
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		// List<PendingInfo> pendingInvites = new ArrayList<PendingInfo>();  
		 List<Map<String,Object>> pendingInvites= new ArrayList<Map<String,Object>>();  

		String qry ="select requestStatus, requestTime, toPhoneNumber, toUID from securecell_invites where fromUID=? and requestStatus != ? order by requestTime asc";

		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, uId);
			pstmt.setInt(2,1);
			rs=pstmt.executeQuery();
	         while (rs.next())
	         {
	        	//ResponseInviteStore response=new ResponseInviteStore();
	        	 Map<String,Object> response= new HashMap<String,Object>();
	            response.put("Type",51);
	            response.put("responseList",rs.getString("toPhoneNumber"));
	            response.put("recipientUID",rs.getString("toUID"));
	            response.put("status",rs.getString("requestStatus"));
	            response.put("time",rs.getString("requestTime"));
	            
	            pendingInvites.add(response);
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
       
		return pendingInvites;
	}

	@Override
	public List<Map<String,Object>> getEntriesFromRemoteDeleteStore(String uId)
	{
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 //List<PendingInfo> pendingDeleteEntries = new ArrayList<PendingInfo>();  
		 List<Map<String,Object>> pendingDeleteEntries= new ArrayList<Map<String,Object>>();  
		String qry ="select mode, deleteId, requestTime, fromPhoneNumber from securecell_deletes where toUID=? and requestStatus=? order by requestTime asc";
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, uId);
			pstmt.setInt(2,1);
			rs=pstmt.executeQuery();
	         while (rs.next())
	         {
	        	//DeletedEntry entry=new DeletedEntry();
	        	 
	        	 Map<String,Object> entry=new HashMap<String,Object>();
	        	 
	        	entry.put("Type",91);
	        	entry.put("sender",rs.getString("fromPhoneNumber"));
	        	entry.put("mode",rs.getString("mode"));
	        	entry.put("deleteId",rs.getString("deleteId"));
	        	entry.put("time",rs.getString("requestTime"));
	        	pendingDeleteEntries.add(entry);
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		return pendingDeleteEntries;
	}

	@Override
	public List<Map<String,Object>> getResponsesFromRemoteDeleteStore(String uId)
	{
	     PreparedStatement pstmt=null;
		 ResultSet rs=null;
		// List<PendingInfo> pendingDeleteResponse = new ArrayList<PendingInfo>();  
		 List<Map<String,Object>> pendingDeleteResponse= new ArrayList<Map<String,Object>>();  
		String qry ="select mode, deleteId, requestTime, toUID from securecell_deletes where fromUID=? and requestStatus=? order by requestTime asc";
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, uId);
			pstmt.setInt(2,2);
			rs=pstmt.executeQuery();
	         while (rs.next())
	         {
	        	//ResponseDeleteStore deleteResponse=new ResponseDeleteStore();
	        	 Map<String,Object>  deleteResponse= new HashMap<String,Object>();
	        	 
	        	deleteResponse.put("Type",92);
	        	deleteResponse.put("deleteList",rs.getString("toUID"));
	        	deleteResponse.put("mode",rs.getString("mode"));
	        	deleteResponse.put("deleteId",rs.getString("deleteId"));
	        	deleteResponse.put("time",rs.getString("requestTime"));
	        	
	        	pendingDeleteResponse.add(deleteResponse);
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		return pendingDeleteResponse;
	}

	@Override
	public List<Map<String,Object>> getEntriesFromContactDeleteStore(String uId)
	{
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 //List<PendingInfo> deletedContacts = new ArrayList<PendingInfo>();  
		 List<Map<String,Object>> deletedContacts= new ArrayList<Map<String,Object>>();  
		String qry ="select requestTime, fromUID from securecell_delete_contacts where toUID=? order by requestTime asc";
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, uId);
			rs=pstmt.executeQuery();
	         while (rs.next())
	         {
	        	//DeletedContact contact=new DeletedContact();
	        	 Map<String,Object> contact= new HashMap<String,Object>();
	        	contact.put("Type",131);
	        	contact.put("sender",rs.getString("fromUID"));
	        	contact.put("time",rs.getString("requestTime"));
	        	deletedContacts.add(contact);
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		return deletedContacts;

	}

	@Override
	public List<Map<String,Object>> getRequestsFromMessageDeleteStore(String uId) 
	{
         PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 //List<PendingInfo> pendingDeleteRequest = new ArrayList<PendingInfo>();  
		 List<Map<String,Object>> pendingDeleteRequest= new ArrayList<Map<String,Object>>();  
		String qry ="select fromUID, group_concat(messageId) as messageIdList from securecell_message_deletes where toUID=? and deleteStatus=? group by fromUID";
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, uId);
			pstmt.setInt(2,1);
			rs=pstmt.executeQuery();
	         while (rs.next())
	         {
	        	//RequestDeleteStore deleteRequest=new RequestDeleteStore();
	        	 Map<String,Object> deleteRequest = new HashMap<String,Object>();
	        	deleteRequest.put("Type",93);
	        	deleteRequest.put("sender",rs.getString("toUID"));
	        	deleteRequest.put("messageIdList",Arrays.asList(rs.getString("messageIdList").split(",")));
	        	
	        	pendingDeleteRequest.add(deleteRequest);
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		return pendingDeleteRequest;
	}

	@Override
	public List<Map<String,Object>> getResponsesFromMessageDeleteStore(String uId) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//List<PendingInfo> pendingDeleteResponse = new ArrayList<PendingInfo>();  
		List<Map<String,Object>> pendingDeleteResponse = new ArrayList<Map<String,Object>>();  
		 
		String qry ="select toUID,group_concat(messageId) as messageIdList from securecell_message_deletes where fromUID=? and deleteStatus=? group by toUID";
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, uId);
			pstmt.setInt(2,2);
			rs=pstmt.executeQuery();
	         while (rs.next())
	         {
	        	//ResponseMessageDeleteStore deleteResponse=new ResponseMessageDeleteStore();
	        	 Map<String,Object> deleteResponse = new HashMap<String,Object>();
	        	deleteResponse.put("Type",94);
	        	deleteResponse.put("recipient",rs.getString("toUID"));
	        	deleteResponse.put("messageIdList",Arrays.asList(rs.getString("messageIdList").split(",")));
	        	
	        	pendingDeleteResponse.add(deleteResponse);
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		return pendingDeleteResponse;
	}

	@Override
	public Map<String, Object> getOrganizationDetails(String orgName) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String qry ="select orgID,retentionPeriod from securecell_organizations where orgName=?";
		Map<String,Object> org = null;
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, orgName);
			
			rs=pstmt.executeQuery();
	         if (rs.next())
	         {
	        	 org=new HashMap<String,Object>();
	        	 org.put("orgID",rs.getInt("orgID"));
	        	 org.put("rPeriod",rs.getInt("retentionPeriod"));
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		return org;
	}

	@Override
	public boolean insertLicenceDetails(LicenceDetails licenceDet)
	{
		PreparedStatement pstmt=null;
		boolean result=false;
		String qry ="replace into securecell_license set uid=?,licenseStatus=?, purchaseDate=?,expiryDate=?, affiliateName=?";
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, licenceDet.getUid());
			pstmt.setString(2,licenceDet.getLicenseStatus()+"");
			pstmt.setString(3,licenceDet.getPurchaseDate()+"");
			pstmt.setString(4,licenceDet.getExpiryDate()+"");
			pstmt.setString(5,licenceDet.getAffiliateName());
			int res=pstmt.executeUpdate();
	        if(res>0)
	        {
	        	result=true;
	        }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closePreparedStatement(pstmt);
		}
		return result;
	}

	@Override
	public String getVirtualNumberByPhNo(String phNo) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String qry ="select virtualNumber from securecell_virtual where phoneNumber=?";
		String virtualNo = null;
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, phNo);
			
			rs=pstmt.executeQuery();
	         if (rs.next())
	         {
	        	 virtualNo=rs.getString("virtualNumber");
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		  return virtualNo;
	}

	@Override
	public String getOldUid(String phoneNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String qry ="select uid from securecell_users where phoneNumber=?";
		String oldUid = null;
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, phoneNo);
			
			rs=pstmt.executeQuery();
	         if (rs.next())
	         {
	        	 oldUid=rs.getString("uid");
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		  return oldUid;
	}

	@Override
	public boolean removeExistingUser(String vNumber)
	{
		PreparedStatement pstmt=null;
		String qry ="delete from securecell_users where phoneNumber=?";
		boolean isDeleted = false;
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, vNumber);
			pstmt.executeUpdate();
			isDeleted=true;
	    }
		catch (Exception e)
		{
			System.out.println(e);
			closePreparedStatement(pstmt);
		}
		  return isDeleted;
	}

	@Override
	public boolean removeExistingUser(String uid, String vNumber)
	{
		PreparedStatement pstmt=null;
		String qry ="delete from securecell_users where uid=? and phoneNumber=?";
		boolean isDeleted = false;
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, uid);
			pstmt.setString(2, vNumber);
			pstmt.executeUpdate();
			isDeleted=true;
	    }
		catch (Exception e)
		{
			System.out.println(e);
			closePreparedStatement(pstmt);
		}
		  return isDeleted;
	}

	@Override
	public String getVirtualNumberByAreaCode(String areaCode)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String qry ="select virtualNumber from securecell_virtual where areaCode=? order by virtualNumber desc limit 1";
		String virtualNo = null;
		try
		{
			pstmt= con.prepareStatement(qry);
			pstmt.setString(1, areaCode);
			
			rs=pstmt.executeQuery();
	         if (rs.next())
	         {
	        	 virtualNo=rs.getString("virtualNumber");
	         }
		}
		catch (Exception e)
		{
			System.out.println(e);
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		  return virtualNo;
	}
	

}
