package com.master.reporter;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.master.apiutils.FrameworkConstants;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/***
 * This class is to manage extent reports configuration.
 * @author varsha
 *
 */
public final class ExtentManager {

    private static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public static void initReport() throws Exception {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();
        
            ExtentSparkReporter sparkReporter;
			
				sparkReporter = new ExtentSparkReporter(FrameworkConstants.getExtentreportpath());
		
            extentReports.attachReporter(sparkReporter);

            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("API Report");
            sparkReporter.config().setReportName("RestAssured Suit Report");
        }
    }

    public static void flushReport() throws Exception {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
            try {
    			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentreportpath()).toURI());
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }
    }

    public static void createExtentTest(String testName, String category, String authorName, String device) {
        extentTest = extentReports.createTest(testName).assignCategory(category).assignAuthor(authorName).assignDevice(device);
    }

    public static void endExtentTest(String testName) {
        extentReports.removeTest(testName);
    }

    private static void formatLogInReport(String content) {
        String prettyPrint = content.replace("\n", "<br>");
        ExtentManager.extentTest.info("<pre>" + prettyPrint + "</pre>");
    }

    public static void addResponseLogToReport(String response) {
        formatLogInReport(response);
    }
   

}
