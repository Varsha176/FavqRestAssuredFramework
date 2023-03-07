package com.master.apiutils;

import static io.restassured.RestAssured.*;

import com.master.Utils.PropertyUtils;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


/***
 * 
 * @author varsha
 * Builder class for building Request Specification.
 *
 */

public final class ApiBuilder {
	
	private ApiBuilder() {
		
	}
	public static RequestSpecification buildRequestForGetCalls() throws Exception
	{
		return given().baseUri(PropertyUtils.getValue("baseURL")).log().all();
	}
	public static RequestSpecification buildRequestForGetCalls(String token) throws Exception
	{
		return buildRequestForGetCalls().
				header("User-Token",token).header("Authorization","Token token="+PropertyUtils.getValue("ApiKey"));
	}
	public static RequestSpecification buildRequestForPostCalls() throws Exception
	{
		return buildRequestForGetCalls().contentType(ContentType.JSON).
				header("Authorization","Token token="+PropertyUtils.getValue("ApiKey"));
				
	}
	public static RequestSpecification buildRequestForPostCalls(String token) throws Exception
	{
		return buildRequestForPostCalls().header("User-Token",token);
				
	}
	public static RequestSpecification buildRequestForPutCalls(String login) throws Exception
	{
		return  buildRequestForPostCalls().queryParam("type","user").queryParam("filter",login);
				
	}
	public static RequestSpecification buildRequestForPutCalls(String login,String Token) throws Exception
	{
		return  buildRequestForPostCalls(Token).queryParam("type","user").queryParam("filter",login);
				
	}

}
