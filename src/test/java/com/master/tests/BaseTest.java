package com.master.tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.master.apiutils.RestHelper;
import com.master.reporter.ExtentManager;

import io.restassured.response.Response;

/***
 * This base class can be utilized to share common methods.
 * @author varsha
 *
 */
public class BaseTest {
	RestHelper restObject = new RestHelper();
	
	private static final Logger Log = LogManager.getLogger(BaseTest.class.getName());
	 public static void logResponseInReport(Response response) {
	        ExtentManager.addResponseLogToReport(response.asPrettyString());
	    }
	 
	 @BeforeSuite(alwaysRun = true)
		public void globalSetup() {
			Log.info("************************** Test Execution Started ************************************");
			
			
			
			
		}
	 
		@AfterSuite
		public void afterSuite() throws IOException  {
			
			
			Log.debug("************************** Execution ended************************************");
			
			

		}
		 @BeforeMethod
		    public void beforeMethod(Method m){
		        System.out.println("STARTING TEST: " + m.getName());
		        System.out.println("THREAD ID: " + Thread.currentThread().getId());
		    }
}
