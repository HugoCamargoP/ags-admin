package com.arrowgs.agsadmin.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;
import com.arrowgs.agsadmin.entities.Address;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.service.AddressService;


@CrossOrigin
@RestController
public class AddressRest {
	
	@Autowired
	AddressService addressService;	
	
	@RequestMapping(path = ApiMappings.Address, method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getAllAddress(){
		ResponseStatus status;
		List<Address> address;
		try{
			address = addressService.getAllAddress();
			status = ResponseStatus.OK;
		}catch(Exception e){
			address = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, address);
	}
	
	@RequestMapping(path = ApiMappings.Address+"/{idAddress}", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getAddressById(@PathVariable Integer idAddress){
		ResponseStatus status;
		Address address;
		try{
			address = addressService.getAddressById(idAddress);
			status = ResponseStatus.OK;
		}catch(Exception e){
			address = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, address);
	}
	
	@RequestMapping(path = ApiMappings.AllAddress+"/{idAddress}", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getAllAddressById(@PathVariable Integer idAddress){
		ResponseStatus status;
		Address address;
		try{
			address = addressService.getAddressById(idAddress);
			status = ResponseStatus.OK;
		}catch(Exception e){
			address = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, address);
	}
	
	@RequestMapping(path = ApiMappings.UserAddress+"/{idUser}", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getAddressByUser(@PathVariable Integer idUser){
		ResponseStatus status;
		List<Address> address;
		try{
			address = addressService.getAddressByUser(idUser);
			status = ResponseStatus.OK;
		}catch(Exception e){
			address = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, address);
	}
	
	@RequestMapping(path = ApiMappings.Address, method = RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> createAddress(@RequestBody Address address){
		ResponseStatus status;
		try{
			addressService.createAddress(address);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, address);
	}
	
	@RequestMapping(path = ApiMappings.Address, method = RequestMethod.PUT)
	public @ResponseBody Map<String,? extends Object> updateAddress(@RequestBody Address address){
		ResponseStatus status;
		try{
			addressService.updateAddress(address);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}	
	
	@RequestMapping(path = ApiMappings.Address + "/{idAddress}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String,? extends Object> createAddress(@PathVariable Integer idAddress){
		ResponseStatus status;
		try{
			addressService.removeAddress(idAddress);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}	
	
	@RequestMapping(path = ApiMappings.Countries, method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getCountries(){
		ResponseStatus status;
		List<IdNameTable> list;
		try{
			list = addressService.getCountries();
			status = ResponseStatus.OK;
		}catch(Exception e){
			list = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, list);
	}

}
