package com.arrowgs.agsadmin.service;

import java.util.List;

import com.arrowgs.agsadmin.entities.Address;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.IdNumTable;

public interface AddressService {
	
	/*Consultas*/
	public List<Address> getAllAddress();
	public List<Address> getAddressByUser(Integer idUser);
	public Address getAddressById(Integer idAddress);
	
	/*Altas*/
	public void createAddress(Address address);
	
	/*Bajas*/
	public void removeAddress(Integer idAddress);
	
	/*Cambios*/
	public void updateAddress(Address address);	
	
	/*Consulta paises*/
	public List<IdNameTable> getCountries();
	
	public List<IdNumTable> getTopCountries();

}
