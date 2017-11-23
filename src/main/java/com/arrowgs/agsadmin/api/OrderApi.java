package com.arrowgs.agsadmin.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.service.OrderService;


@RestController
public class OrderApi {

	@Autowired
	OrderService orderService;
	
	
	@RequestMapping(path = ApiMappings.UpdateState, method = RequestMethod.PUT)
	public @ResponseBody Map<String,? extends Object> updateOrderState(@RequestBody Order order){
		ResponseStatus status;
		try{
			orderService.updateOrderStatus(order);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, order);
	}

}
