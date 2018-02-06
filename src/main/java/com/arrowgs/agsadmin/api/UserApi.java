package com.arrowgs.agsadmin.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arrowgs.agsadmin.entities.User;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.service.UserService;
import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;;


@RestController
public class UserApi {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path = ApiMappings.UsersFilter, method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getUserByFilter(@RequestParam(value="email") String email, 
			@RequestParam(value="filter") Integer way,
			@RequestParam("page") Integer page, 
			@RequestParam("usersInPage") Integer usersInPage)	{
		ResponseStatus status;
		List<User> users;
		try{
			users = userService.getUserByFilter(email, way,page,usersInPage);
			status = ResponseStatus.OK;
		}catch(Exception e){
			users = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, users);
	}
	
	@RequestMapping(path = ApiMappings.UsersFilterCount, method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getUserByFilterCount(@RequestParam(value="email") String email, @RequestParam(value="filter") Integer way)	{
		ResponseStatus status;
		Integer users;
		try{
			users = userService.getUserByFilterCount(email, way);
			status = ResponseStatus.OK;
		}catch(Exception e){
			users = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, users);
	}
	
	@RequestMapping(path = ApiMappings.UsersRol, method = RequestMethod.PUT)
	public @ResponseBody Map<String,? extends Object> updateUserRol(@RequestBody List<User> users){
		ResponseStatus status;
		try{
			Iterator<User> iterator =  users.iterator();
			while(iterator.hasNext()){
				User user = iterator.next();
				userService.updateUserRol(user);
			}
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		
		return ControllerHelper.mapResponse(status, users);
	}

}
