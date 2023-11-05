package com.tutorialsninja.qa.utils;

import java.util.Date;

public class Utilities {
	
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "").replace(":", "");
		return "nillu" + timestamp + "@gmail.com";
	}
}




