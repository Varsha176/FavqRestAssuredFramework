package com.master.Utils;
import java.util.HashMap;
import com.master.apiutils.ApiBuilder;
import com.master.apiutils.Route;
import com.master.datastore.DataStore;
import io.restassured.response.Response;


/***
 * This class is used to generate user token and user login.(For independent test execution).
 * @author varsha
 *
 */
public class SessionUtil {


	public static String generateSession() throws Exception
	{
		
		
		HashMap<String,Object> object = new HashMap<String, Object>();
		HashMap<String,String> hm = new HashMap<String, String>();
		hm.put("login",PropertyUtils.getValue("Login"));
		hm.put("password",PropertyUtils.getValue("Password"));
		object.put("user",hm);
		Response response = ApiBuilder.buildRequestForPostCalls()
				.body(object).when().post(Route.Session);
				
		response.prettyPrint();
		
		DataStore.User_Token = response.jsonPath().getString("User-Token");
		DataStore.User_Login = response.jsonPath().getString("login");
		return DataStore.User_Token;
	}
}