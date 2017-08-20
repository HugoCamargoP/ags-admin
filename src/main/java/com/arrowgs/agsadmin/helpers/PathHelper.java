package com.arrowgs.agsadmin.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PathHelper {

	static public Map<String,Object> fromPathToMap(String path){
		StringTokenizer firstStep = new StringTokenizer(path, "&");
		Map<String,Object> map = new HashMap<>();
		while(firstStep.hasMoreTokens()){
			StringTokenizer secondStep = new StringTokenizer(firstStep.nextToken(), "=");
			String name  = secondStep.nextToken();
			String value;
			if(secondStep.hasMoreTokens())
			{					
				 value = secondStep.nextToken();
				 if(value.equals("undefined")){
					 value = null;
				 }
			}
			else{
				value = null;
			}
			map.put(name, value);
		}
		return map;
	}
	
	static public String sqlLike(String param){
		param = "%" + param + "%";
		return param;
	}
	
}
