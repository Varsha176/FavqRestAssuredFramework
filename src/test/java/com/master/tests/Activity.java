package com.master.tests;


import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.master.Utils.PropertyUtils;
import com.master.Utils.SessionUtil;
import com.master.apiutils.ApiBuilder;
import com.master.apiutils.Route;
import com.master.datastore.DataStore;
import io.restassured.response.Response;


/***
 * This test class is to test get activity,delete activity, follow user and unfollow user.
 * @author varsha
 *
 */
public class Activity extends BaseTest{

	private static final Logger Log = LogManager.getLogger(Activity.class.getName());
	
	
	
	@Test(priority=3, description="Get User Activity")
	public void getActivity() throws Exception
	{


		Log.info("Get Activity of the day");

		
		Response response =restObject.GetRequest(ApiBuilder.buildRequestForGetCalls(PropertyUtils.getValue("User-Token")),Route.Activity);
		response.prettyPrint();
		
		logResponseInReport(response);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		Assert.assertTrue(response.jsonPath().getString("activities[0].owner_type").contains("User"));
		
		Log.info("Activity id:_"+response.jsonPath().getInt("activities[0].activity_id"));
		DataStore.ActivityId = response.jsonPath().getInt("activities[0].activity_id");
		
	
		
	}
	
	@Test(description="Follow User",priority=1)
	public void FollowUser() throws Exception
	{
		
		if(DataStore.User_Token == null)
		{
			DataStore.User_Token = SessionUtil.generateSession();
		
		}
		Log.info("Follow User:_"+DataStore.User_Login);
		Response response = restObject.PutRequest(ApiBuilder.buildRequestForPutCalls(DataStore.User_Login), Route.FollowUser);
		response.prettyPrint();
		logResponseInReport(response);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		
	}
	
	@Test(description="UnFollow User",priority=2)
	public void UnFollowUser() throws Exception
	{
		
		if(DataStore.User_Token == null)
		{
			DataStore.User_Token = SessionUtil.generateSession();
		
		}
		Log.info("UnFollow User:_"+DataStore.User_Login);
		Response response = restObject.PutRequest(ApiBuilder.buildRequestForPutCalls(DataStore.User_Login,DataStore.User_Token), Route.UnFollowUser);
		response.prettyPrint();
		logResponseInReport(response);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		Assert.assertEquals(response.getStatusLine(),"OK");;
		
	}
	@Test(priority=4,description="Delete User Activity")
	public void DeleteActivity() throws Exception
	{
		
		
		Log.info("activity id to be deleted is:"+DataStore.ActivityId);

		
		Response response= restObject.DeleteRequest(ApiBuilder.buildRequestForPostCalls(PropertyUtils.getValue("User-Token")), Route.Activity+"/"+DataStore.ActivityId);
		response.prettyPrint();
		logResponseInReport(response);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		
	}

}
