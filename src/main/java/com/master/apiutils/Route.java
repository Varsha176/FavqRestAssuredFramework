package com.master.apiutils;

/***
 * To store the endpoints for API's.
 * @author varsha
 *
 */
public class Route {
	
	  public static final String User = "/api/users";
	    public static final String Activity = "/api/activities";
	    public static final String FollowUser=Activity+"/follow"; 
	    public static final String UnFollowUser=Activity+"/unfollow";
	    public static final String GetQuote = "/api/qotd";
	    public static final String Quotes="/api/quotes";
	    public static final String Session="api/session"; 

}
