package com.master.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.master.reporter.ExtentManager;

/***
 * Listener class for reporting purpose.
 * @author varsha
 *
 */

public class Listener implements ITestListener, ISuiteListener {
  
    public void onStart(ISuite suite) {
        try {
			ExtentManager.initReport();
		} catch (Exception e) {

			e.printStackTrace();
		}
    }


    public void onFinish(ISuite suite) {
        try {
			ExtentManager.flushReport();
		} catch (Exception e) {

			e.printStackTrace();
		}
    }


    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getDescription();
        ExtentManager.createExtentTest(testName, "Test Suite", "Varsha", "Chrome");
        ExtentManager.extentTest.pass(testName + " started successfully !!!");
    }

    public void onTestSuccess(ITestResult result) {
        ExtentManager.extentTest.pass(result.getMethod().getDescription() + " TEST is passed !!!");
        ExtentManager.endExtentTest(ExtentManager.extentTest.getExtent().toString());
    }

 
    public void onTestFailure(ITestResult result) {
        String error = result.getMethod().getDescription() + " TEST is failed !!!<br>" + result.getThrowable();
        ExtentManager.extentTest.log(Status.FAIL, error);
        ExtentManager.endExtentTest(ExtentManager.extentTest.getExtent().toString());
    }

  
    public void onTestSkipped(ITestResult result) {
        ExtentManager.extentTest.skip(result.getMethod().getDescription() + " TEST is skipped !!!");
        ExtentManager.endExtentTest(ExtentManager.extentTest.getExtent().toString());
    }

}
