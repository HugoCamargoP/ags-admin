package com.arrowgs.agsadmin.helpers;

import java.util.StringTokenizer;

public class SqlHelper {

	static public String likeSpaceHelper(String string){
		StringTokenizer tokens;
		StringBuilder finalString;
		if(string==null||string.equals("")){
			finalString= new StringBuilder("%%");
		}else{
			tokens = new StringTokenizer(string," \t");
			finalString = new StringBuilder("");
			while(tokens.hasMoreTokens()){
				 String token = tokens.nextToken();
				 finalString.append("%");
				 finalString.append(token);
				 finalString.append("%");
			}
		}
		return finalString.toString();
	}
	

}
