package com.master.tests;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.master.Utils.SessionUtil;
import com.master.apiutils.ApiBuilder;
import com.master.apiutils.Route;
import com.master.datastore.DataStore;

import io.restassured.response.Response;

public class QuoteOperations extends BaseTest{
	
	/***
	 * This test class is to test quote of the day.
	 */
	private static final Logger Log = LogManager.getLogger(QuoteOperations.class.getName());
	
	@Test(description="Get Quote Of the day",priority=1)
	public void getQuote() throws Exception
	{
		Log.info("Get Quote of the day");
		Response response = ApiBuilder.buildRequestForGetCalls().get(Route.GetQuote);
		response.prettyPrint();
		//logResponseInReport(response);
		DataStore.QuoteId = response.jsonPath().getInt("quote.id");
		Assert.assertEquals(response.toString().isEmpty(),false);
	Log.info("Quote of the day Author is_:"+response.jsonPath().getString("quote.author")+" and quote id is:"+DataStore.QuoteId );
	
		
	}
	
	@Test(description="Hide Quote ",priority=2)
	public void HideQuote() throws Exception
	{
		if(DataStore.User_Token == null)
		{
			DataStore.User_Token = SessionUtil.generateSession();
		
		}
		Log.info("Hide a Quote");
		Response response = ApiBuilder.buildRequestForPostCalls(DataStore.User_Token).put(Route.Quotes+"/"+DataStore.QuoteId+"/hide");
		response.prettyPrint();
		logResponseInReport(response);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		Assert.assertTrue(response.jsonPath().getString("user_details.hidden").contains("true"));
		
		
	
		
	}
	@Test(description="Mark Favourite Quote ",priority=3)
	public void MarkQuoteFavourite() throws Exception
	{
		if(DataStore.User_Token == null)
		{
			DataStore.User_Token = SessionUtil.generateSession();
		
		}
		Log.info("Mark a Quote as favourite");
		Response response = ApiBuilder.buildRequestForPostCalls(DataStore.User_Token).put(Route.Quotes+"/"+DataStore.QuoteId+"/fav");
		response.prettyPrint();
		logResponseInReport(response);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		Assert.assertTrue(response.jsonPath().getString("user_details.favorite").contains("true"));
		
		
	
		
	}

}
