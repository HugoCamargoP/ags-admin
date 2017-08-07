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
		Properties image = new Properties();
		String location = null;
		InputStream is = ImagePropertiesHelper.class.getResourceAsStream(IMAGE_PROPERTIES);
		try {
			image.load(is);
			if(is!=null){
				location = image.getProperty("img.locationAux");
			}
		} catch (IOException e) {
			logger.error("public void  resource(): " + e.toString());
		}
		
		return location;
	}
}
