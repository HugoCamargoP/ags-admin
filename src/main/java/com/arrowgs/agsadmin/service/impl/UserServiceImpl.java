package com.arrowgs.agsadmin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.daos.UserDao;
import com.arrowgs.agsadmin.entities.User;
import com.arrowgs.agsadmin.service.PasswordEncoderService;
import com.arrowgs.agsadmin.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordEncoderService passwordService;

	//Attributes
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getUsers() {
		try{
			
			return userDao.getUsers();
		}
		catch(Exception e){
			logger.error("UserService : getUsers : "+e.toString());
			throw e;
		}
	}

	@Override
	public List<User> getUserByFilter(String email, Integer way) {
		List<User> users;
		try{
			users = userDao.getUserByFilter(email, way);
		}catch(Exception e){
			logger.error("UserService : getUsersByFilter : "+e.toString());
			throw e;
		}
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		try{
			
			return userDao.getUserByEmail(email);
		}
		catch(Exception e){
			logger.error("UserService: getUserByEmail",e);
			throw e;
		}	
	}

	@Override
	public String getEncodedPassByEmail(String email) {
		User myUser = userDao.getUserByEmail(email);
		String retorno;
		if(myUser==null)
			retorno=null;
		else
			retorno = myUser.getPassword();
		return retorno;
	}

	@Override
	public void updateUser(User user) {
		try{
			if(user.getPassword()!=null){
				String encode = passwordService.encode(user.getPassword());
				user.setPassword(encode);
			}
			userDao.modifyUser(user);		
		}catch(Exception e){
			logger.error("UserService : updateUser : "+e.toString());
			throw e;
		}
		
	}

	@Override
	public void updateUserRol(User user) {
		try{
			userDao.modifyUserRol(user);		
		}catch(Exception e){
			logger.error("UserService : updateMeasure : "+e.toString());
			throw e;
		}
	}

	@Override
	public User getUserById(Integer id) {
		try{
			
			return userDao.getUserById(id);
		}
		catch(Exception e){
			logger.error("UserService: getUserById :",e);
			throw e;
		}	
	}


}
