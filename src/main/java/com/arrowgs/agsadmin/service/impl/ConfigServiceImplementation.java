package com.arrowgs.agsadmin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.daos.ConfigDao;
import com.arrowgs.agsadmin.entities.ConfigEntity;
import com.arrowgs.agsadmin.service.ConfigService;

@Service
public class ConfigServiceImplementation implements ConfigService{
	
	private static Logger logger = LoggerFactory.getLogger(ConfigServiceImplementation.class);
	
	@Autowired
	ConfigDao configDao;

	@Override
	public ConfigEntity getConfigEntity() {
		ConfigEntity config = null;
		try{
			config = configDao.getConfigEntity();
		}catch(Exception e){
			logger.error("ConfigService : getConfigEntity : " + e.toString());
			throw e;
		}
		return config;
	}

	@Override
	public ConfigEntity updateConfigEntity(ConfigEntity config) {
		try{
			ConfigEntity aux = configDao.getConfigEntity();
			if(aux!=null)
			{
				config = configDao.updateConfigEntity(config);
			}else{
				createConfigEntity(config);
			}
		}catch(Exception e){
			logger.error("ConfigService : getConfigEntity : " + e.toString());
			throw e;
		}
		return config;
	}

	@Override
	public void createConfigEntity(ConfigEntity config) {
		
		try{
			configDao.createConfigEntity(config);
		}catch(Exception e){
			logger.error("ConfigService : getConfigEntity : " + e.toString());
			throw e;
		}
		
	}


}
