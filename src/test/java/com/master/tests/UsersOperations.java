package com.master.tests;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.master.apiutils.ApiBuilder;
import com.master.apiutils.Route;
import com.master.datastore.DataStore;
import com.master.pojo.MaterUserPojo;
import com.master.pojo.User;
import static com.master.Utils.FakerUtil.generateName;
import static com.master.Utils.FakerUtil.generateEmail;
import static com.master.Utils.FakerUtil.generatePassword;
import io.restassured.response.Response;

public class UsersOperations extends BaseTest {
	
	private static final Logger Log = LogManager.getLogger(UsersOperations.class.getName());
	

	/***
	 * This test class is to test user operations like add a new user, get user details,update user details etc.
	 * @throws Exception
	 */
	@Test(priority=1, description="Add a new user")
	public void addNewUser() throws Exception
	{
		Log.info("Create a new User");;
		User createuser = new User();
		MaterUserPojo user = new MaterUserPojo();
		createuser.setEmail(generateEmail());
		createuser.setLogin(generateName());
		createuser.setPassword(generatePassword());
		user.setUser(createuser);
		
		
		Response response=restObject.PostRequest(ApiBuilder.buildRequestForPostCalls().body(user), Route.User);
		response.prettyPrint();
        // Comment out this method if want to execute this tc independently.
		logResponseInReport(response);
	
		  Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);

	Log.info("Login name of created user is:_"+response.jsonPath().getString("login"));
	DataStore.User_Login =response.jsonPath().getString("login");
	 DataStore.User_Token =response.jsonPath().getString("User-Token");
	
		
	}
	
	@Test(priority=2, description="Get user details")
	public void getUser() throws Exception
	{
		Log.info("Get User details for user:_"+DataStore.User_Login);
		
		
		
		Response response=restObject.GetRequest(ApiBuilder.buildRequestForGetCalls(DataStore.User_Token), Route.User+"/"+DataStore.User_Login);
		response.prettyPrint();
		logResponseInReport(response);
		
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
		
		Assert.assertEquals(DataStore.User_Login,response.jsonPath().getString("login"));
	
		
	}
	
	@Test(priority=3, description="Update user details")
	public void UpdateUser() throws Exception
	{
		Log.info("Update User");
		
		
		User Updateuser = new User();
		MaterUserPojo user = new MaterUserPojo();
		Updateuser.setEmail(generateEmail());
		Updateuser.setLogin(generateName());
		Updateuser.setPassword(generatePassword());
		Updateuser.settwitter_username(generateName());
		
		user.setUser(Updateuser);
		
		Response response=restObject.PutRequest(ApiBuilder.buildRequestForPostCalls(DataStore.User_Token).body(user), Route.User+"/"+DataStore.User_Login);
		response.prettyPrint();
		logResponseInReport(response);
		
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
	
		
	}

}
