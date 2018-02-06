package com.arrowgs.agsadmin.daos;

import java.util.List;

import com.arrowgs.agsadmin.entities.User;


public interface UserDao {
	
	static final String UsersTable = "usuarios";	
	
	List<User> getUsers();
	List<User> getUserByFilter(String email, Integer way, Integer page, Integer usersInPage);
	Integer	getUserByFilterCount(String email, Integer way);
	User getUserByEmail(String email);
	User getUserById(Integer id);
	void removeUserByEmail(String email);
	void modifyUser(User user);
	void modifyUserRol(User user);
	
}
