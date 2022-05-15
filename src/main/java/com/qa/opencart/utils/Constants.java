package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	
	public static final String LOGIN_PAGE_TITLE="Account Login11";
	
	public static final String LOGIN_PAGE_URL_FRACTION="route=account/login";
	public static final String ACCOUNTS_PAGE_TITLE="My Account";

	public static final int DEFAULT_TIME_OUT=5;
	public static final String	ACCOUNTS_PAGE_HEADER="Your Store11";
	
	public static final int  IMAC_IMAGE_COUNT=3;
	public static final int  MACBOOKPRO_IMAGE_COUNT=3;
	public static final int  MACBOOKAIR_IMAGE_COUNT=3;
	public static final String LOGIN_ERROR_MESG="No match for E-Mail";
	public static final String REGISTER_SUCCESS_MESG="Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME="registration";

	public static List<String> getExpectedAccSecList()
	{
		List<String>expseclist=new ArrayList<String>();
		expseclist.add("My Account");
		expseclist.add("My Orders");
		expseclist.add("My Affiliate Account");
		expseclist.add("Newsletter");
		
		return expseclist;
	}

}
