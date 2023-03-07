package com.master.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;



/***
 * This utility class is to generate unique data every time for the user.
 * @author varsha
 *
 */
public final class FakerUtil {
	private static final Logger Log = LogManager.getLogger(FakerUtil.class.getName());
	
	private FakerUtil()
	{
		
	}

	private static final Faker faker = new Faker();

	public static String generateName(){
  
		String name = faker.name().firstName();
		Log.info("Name provide as:_"+name);
        return name;
    }

    public static String generateEmail(){
    	
    	String Email = faker.internet().emailAddress();
		Log.info("Email provide as:_"+Email);
        return Email;
    }
    
    public static String generatePassword(){
     
        return faker.internet().password(5, 10);
        
    }
    
	
}
