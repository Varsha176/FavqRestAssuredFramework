package com.master.apiutils;



/***
 * To Store path of config files.
 * @author varsha
 *
 */
public class FrameworkConstants {
	
	private static final String RESOURCEPATH = System.getProperty("user.dir") ;
	private static final String CONFIGFILEPATH=RESOURCEPATH+"/config.properties";
	private static String extentReportFilePath = "";
	private static final String EXTENTREPORTFOLDERPATH=System.getProperty("user.dir")+"/extent-test-output";
	
	
	public static String getConfigFilePath()
	{
		return CONFIGFILEPATH;
	}

	public static String getExtentreportpath() throws Exception {
		if(extentReportFilePath.isEmpty()) {
			extentReportFilePath = createReportPath();
		}
		return extentReportFilePath;

		
	}
	/**Create report on the basis of time stamp if user don't want to override reports
	 * overridereports value should be read from config file.
	 **/
	
	private static String createReportPath() throws Exception  {
		
		
			return EXTENTREPORTFOLDERPATH+"/spark.html";
		
	}
}
