package com.arrowgs.agsadmin.daos;

import java.util.List;

import com.arrowgs.agsadmin.entities.Address;
import com.arrowgs.agsadmin.entities.IdNameTable;

public interface AddressDao {
	
	public final static String addressTable = "domicilios";
	
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
	
	/*Pais Consulta*/
	public List<IdNameTable> getCountries();

}
