package com.arrowgs.agsadmin.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ImagePropertiesHelper {

	static final String  IMAGE_PROPERTIES = "images.properties";
	private static Logger logger = LoggerFactory.getLogger(ImagePropertiesHelper.class);
	
	
	static public String  resource(){
		String location = null;
		InputStream is = ImagePropertiesHelper.class.getClassLoader().getResourceAsStream(IMAGE_PROPERTIES);
		try {
			
			if(is!=null){
				Properties image = new Properties();
				image.load(is);
				location = image.getProperty("img.location");

			}
		} catch (IOException e) {
			logger.error("public void  resource(): " + e.toString());
		}
		
		return location;
	}
	
	static public String  localHostResource(){
		String location = null;
		InputStream is = ImagePropertiesHelper.class.getClassLoader().getResourceAsStream(IMAGE_PROPERTIES);
		try {
			
			if(is!=null){
				Properties image = new Properties();
				image.load(is);
				location = image.getProperty("img.localHost");

			}
		} catch (IOException e) {
			logger.error("public void  resource(): " + e.toString());
		}
		
		return location;
	}
}
