package com.arrowgs.agsadmin.service;

import com.arrowgs.agsadmin.entities.ConfigEntity;

public interface ConfigService {

	ConfigEntity getConfigEntity();
	
	ConfigEntity updateConfigEntity(ConfigEntity config);
	
	void	createConfigEntity(ConfigEntity config);
}
