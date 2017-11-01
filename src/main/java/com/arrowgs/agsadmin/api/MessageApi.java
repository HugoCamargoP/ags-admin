package com.arrowgs.agsadmin.api;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;


public class MessageApi {

	@Autowired
	MessageSource messageSource;

	@RequestMapping(path = ApiMappings.MessageProperties, method = RequestMethod.GET)
	public Map<String,? extends Object> getMessageFromProperties(@RequestParam("attribute") String attribute, Locale locale){
		ResponseStatus status;
		String message = "";
		try{
			message = messageSource.getMessage(attribute, null, "", locale);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		
		return ControllerHelper.mapResponse(status, message);
	}
}
