package com.arrowgs.agsadmin.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;
import com.arrowgs.agsadmin.entities.ConfigEntity;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.service.ConfigService;

@RestController
public class ConfigApi {

	@Autowired
	ConfigService configService;
	
	@RequestMapping(path = ApiMappings.Config, method = RequestMethod.GET)
	public Map<String,? extends Object> getConfigEntity(){
		ResponseStatus status;
		ConfigEntity config = null;
		try{
			config = configService.getConfigEntity();
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, config);
	}
	
	@RequestMapping(path = ApiMappings.Config, method = RequestMethod.PUT)
	public Map<String,? extends Object> updateConfigEntity(@RequestBody ConfigEntity config){
		ResponseStatus status;
		try{
			config = configService.updateConfigEntity(config);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, config);
	}
}
