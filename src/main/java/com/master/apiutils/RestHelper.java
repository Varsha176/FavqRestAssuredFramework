/***
 * @author Varsha
 */

package com.master.apiutils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestHelper{
	
	public Response PostRequest(RequestSpecification repoSpecObj, String PathUri) {
		 return repoSpecObj.log().body().when().post(PathUri);
	}
	
	public Response PutRequest(RequestSpecification repoSpecObj, String PathUri) {
		 return repoSpecObj.log().body().when().put(PathUri);
	}
	
	public Response GetRequest(RequestSpecification repoSpecObj, String PathUri) {
		 return repoSpecObj.when().get(PathUri);
	}
	
	
	
	public Response DeleteRequest(RequestSpecification repoSpecObj, String PathUri) {
		 return repoSpecObj.when().delete(PathUri);
	}
	


}
