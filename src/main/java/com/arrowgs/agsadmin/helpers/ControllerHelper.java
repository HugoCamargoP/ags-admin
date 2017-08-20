package com.arrowgs.agsadmin.helpers;

import java.util.HashMap;
import java.util.Map;

public class ControllerHelper {

	private final static String Data   = "data";
	private final static String Status = "status";
	private final static String Error  = "error"; 
	
	public static enum ResponseStatus{
		OK, ExternalError
	}
	
	public static Map<String, Object> mapResponse(Object status, Object data){
		Map<String,Object> newMap = new HashMap<>();
		newMap.put(Status, status);
		newMap.put(Data, data);
		return newMap;
	}
	
	public static Map<String, Object> mapResponse(Object status, Object data, String error){
		Map<String,Object> newMap = new HashMap<>();
		newMap.put(Status, status);
		newMap.put(Data, data);
		newMap.put(Error, error);
		return newMap;
	}
	
	public static Map<String, Object> mapResponseError(Object status,String error){
		Map<String,Object> newMap = new HashMap<>();
		newMap.put(Status, status);		
		newMap.put(Error, error);
		return newMap;
	}	
}
