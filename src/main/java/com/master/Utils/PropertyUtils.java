package com.master.Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import com.master.apiutils.FrameworkConstants;

/***
 * This utility is to read properties file.
 * @author varsha
 *
 */

public final class PropertyUtils {
	
	
	
	private static Properties properties = new Properties();
	public static String getValue(String key) throws Exception
	{
		String value="";
		
		InputStream input = null;
		
			input = new FileInputStream(FrameworkConstants.getConfigFilePath());
			properties.load(input);
		value=properties.getProperty(key);
		if(Objects.isNull(value))
		{
			throw new Exception("property name" +key+"is not found.Please check config.properties");
		}
		return value;
	}

}
