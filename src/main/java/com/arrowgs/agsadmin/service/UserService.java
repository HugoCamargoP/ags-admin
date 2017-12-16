package com.arrowgs.agsadmin.service;

import java.util.List;

import com.arrowgs.agsadmin.entities.User;


public interface UserService {
	
	public enum LoginError{
		NOUSER(-1), NOERROR(0), NOPASS(1), ERROR(-2);
		
		Integer mistake;
		private LoginError(Integer set){
			mistake = set;
		}
		
		public Integer getError(){
			return mistake;
		}
	};	
	
	List<User> getUsers();
	List<User> getUserByFilter(String email, Integer way);
	User getUserById(Integer id);
	User getUserByEmail(String email);	
	String getEncodedPassByEmail(String email);
	void updateUser(User user);
	void updateUserRol(User user);
	
	
	
}
