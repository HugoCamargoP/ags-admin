package com.arrowgs.agsadmin.controllers.cons;

public interface Constants {

	static interface Views{
		static final String Root	= "/";
	}
	
	static interface Mappings{
		static final String Login		= "/login";
		static final String Logout		= "/logout";
	}
	
	static interface ApiMappings{
		static final String ServiceContext		= "/api";
		
		//User
		static final String UsersFilter		  = ServiceContext + "/users/filter";
		static final String User		  	  = ServiceContext + "/user";
	}
}
