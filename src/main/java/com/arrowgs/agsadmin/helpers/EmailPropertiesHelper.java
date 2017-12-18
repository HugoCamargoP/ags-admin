package com.arrowgs.agsadmin.helpers;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailPropertiesHelper {

	static final String FromEmail = "mail.from";
	static final String MAIL_PROPERTIES = "mail.properties";
	static final String ContactEmail = "mail.contact";
	
	private static Logger logger = LoggerFactory.getLogger(EmailPropertiesHelper.class);
	
	static public String getEmailFrom(){
		Properties email = new Properties();
		String from = null;
		InputStream is = EmailPropertiesHelper.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES);
		try{
			if(is!=null){
				email.load(is);
				from = email.getProperty(FromEmail);
			}
		}catch (Exception e) {
			logger.error("EmailProperties getEmailFrom : " + e.toString());
		}
		return from;
	}
	
	static public String getEmailContact(){
		Properties email = new Properties();
		String from = null;
		InputStream is = EmailPropertiesHelper.class.getClassLoader().getResourceAsStream(MAIL_PROPERTIES);
		try{
			if(is!=null){
				email.load(is);
				from = email.getProperty(ContactEmail);
			}
		}catch (Exception e) {
			logger.error("EmailProperties getEmailContact : " + e.toString());
		}
		return from;
	}

}
