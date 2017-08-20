package com.arrowgs.agsadmin.helpers;

import java.lang.reflect.Method;
import java.util.Map;

public class ClassHelper {

	public static <T> T fromStringMap(Class<T> clazz, Map<String, Object> map) {
		T obj = null;
		
		try {
			obj = clazz.newInstance();

			if (map != null) {		
				Method[] methods = clazz.getDeclaredMethods();
				for (int i = 0; i < methods.length; i++) {
					Method method = methods[i];
					String methodName = method.getName();
					if (methodName.startsWith("set")) {
						String key = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
						
						try {
							
							if (method.getParameterCount() > 0)
							{
								Class<?> parType = method.getParameterTypes()[0];
								Object value = map.get(key); 
								if (value != null) {
									String strValue = value.toString();
									if (parType.isAssignableFrom(String.class)) {
										method.invoke(obj, strValue);
									}
									else if (parType.isAssignableFrom(int.class) || parType.isAssignableFrom(Integer.class)) {
										method.invoke(obj, Integer.parseInt(strValue));
									}
									else if (parType.isAssignableFrom(long.class) || parType.isAssignableFrom(Long.class)) {
										method.invoke(obj, Long.parseLong(strValue));
									}									
									else if (parType.isAssignableFrom(double.class) || parType.isAssignableFrom(Double.class)) {
										method.invoke(obj, Double.parseDouble(strValue));
									}
								
								}
								
							}
						} catch(RuntimeException re) {}
					}
				}
			}
		} catch(Throwable t) {}
		
		return obj;
	}
}
