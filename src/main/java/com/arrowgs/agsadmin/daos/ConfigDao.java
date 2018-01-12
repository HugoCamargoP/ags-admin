package com.arrowgs.agsadmin.daos;

import com.arrowgs.agsadmin.entities.ConfigEntity;

public interface ConfigDao {

	static public final String BasicConfigTable = "basic_config"; 
	
	ConfigEntity getConfigEntity();
	
	ConfigEntity updateConfigEntity(ConfigEntity config);
	
	void	createConfigEntity(ConfigEntity config);
}
