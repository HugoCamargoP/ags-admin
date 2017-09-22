package com.arrowgs.agsadmin.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.daos.AddressDao;
import com.arrowgs.agsadmin.entities.Address;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.service.AddressService;

@Service
public class AddressServiceImplementation implements AddressService{

	private static Logger logger = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	AddressDao addressDao;
	
	@Override
	public List<Address> getAllAddress() {		
		List<Address> address;
		try{
			address = addressDao.getAllAddress();
		}catch(Exception e){
			address = null;
			logger.error("getAllAddress: "+e.toString());
			throw e;
		}
		return address;
	}

	@Override
	public List<Address> getAddressByUser(Integer idUser) {
		List<Address> address;
		try{
			address = addressDao.getAddressByUser(idUser);
		}catch(Exception e){
			address = null;
			logger.error("getAddressByUser: "+e.toString());
			throw e;
		}
		return address;
	}

	@Override
	public Address getAddressById(Integer idAddress) {
		Address address;
		try{
			address = addressDao.getAddressById(idAddress);
		}catch(Exception e){
			address = null;
			logger.error("getAddressById : "+ e.toString());
			throw e;
		}
		return address;
	}

	@Override
	public void createAddress(Address address) {
		try{
			addressDao.createAddress(address);
		}catch(Exception e){			
			logger.error("createAddress : " + e.toString());
			throw e;
		}
		
	}

	@Override
	public void removeAddress(Integer idAddress) {
		try{
			addressDao.removeAddress(idAddress);
		}catch(Exception e){
			logger.error("removeAddress : " + e.toString());
			throw e;
		}
		
		
	}

	@Override
	public void updateAddress(Address address) {
		try{
			addressDao.updateAddress(address);
		}catch(Exception e){
			logger.error("updateAddress : " + e.toString());
		}
		
	}

	@Override
	public List<IdNameTable> getCountries() {
		List<IdNameTable> countries;
		try{
			countries = addressDao.getCountries();
		}catch(Exception e){
			countries = null;
			logger.error("getCountries : " + e.toString());
			throw e;
		}
		return countries;
	}

	@Override
	public List<IdNumTable> getTopCountries() {
		List<IdNumTable> topCountries;
		try{
			topCountries = addressDao.getTopCountries();
		}catch(Exception e){
			logger.error("AddressService : getTopCountries : " + e.toString());
			throw e;
		}
		return topCountries;
	}


}
