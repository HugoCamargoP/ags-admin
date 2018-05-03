package com.arrowgs.agsadmin.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;
import com.arrowgs.agsadmin.entities.GuideNumber;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.helpers.ClassHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.helpers.PathHelper;
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
	
	@RequestMapping(path = ApiMappings.OrdersByFilter+"/{path}/{page}/{numOrder}", method = RequestMethod.GET)
	public @ResponseBody Map<String,?extends Object> getOrdersByFilter(@PathVariable String path, @PathVariable Integer page,@PathVariable Integer numOrder){
		ResponseStatus status;
		List<Order> orders = null;
		Map<String,Object> response;
		try{
			Map<String,Object> orderMap = PathHelper.fromPathToMap(path);
			Order order = ClassHelper.fromStringMap(Order.class, orderMap);
			orders = orderService.getOrderByFilter(order, page, numOrder);
			Integer pages = orderService.getCountByFilter(order);
			pages = (int) Math.ceil(pages/numOrder);
			status = ResponseStatus.OK;
			response = ControllerHelper.mapResponse(status, orders);
			response.put("totalPages", pages);
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
			response = ControllerHelper.mapResponse(status, orders);
		}
		return response;
	}
	
	@RequestMapping(path = ApiMappings.GetOrderCountByFilter +"/{path} {inPage}", method = RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getCountOrderByFilter(@PathVariable String path,@PathVariable Integer inPage){
		ResponseStatus status;
		Map<String,Object> params = PathHelper.fromPathToMap(path);
		Order order = ClassHelper.fromStringMap(Order.class, params);
		order.setUserText(PathHelper.sqlLike(order.getUserText()));
		Integer result; 
		params.clear();
		try{
			result = orderService.getCountByFilter(order);
			params.put("total", result);
			if(result%inPage!=0){
				result = result/inPage +1;
			}
			else{
				result /= inPage;
			}
			params.put("pages", result);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
			result = null;
		}
		return ControllerHelper.mapResponse(status, params);
	}	
	
	@RequestMapping(path=ApiMappings.StatusOrder, method=RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getStatus(){
		ResponseStatus status;
		List<IdNameTable> response= null;
		try{
			response = orderService.getStatus();
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		
		return ControllerHelper.mapResponse(status, response);
	}
	
	@RequestMapping(path = ApiMappings.OrderDetail, method = RequestMethod.PUT)
	public @ResponseBody Map<String,? extends Object> updateOrderDetail(@RequestBody OrderDetail orderDetail){
		ResponseStatus status;
		try{
			if(orderService.modifyOrderProdudct(orderDetail)){
				status = ResponseStatus.OK;
			}else{
				status = ResponseStatus.ExternalError;
			}
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, orderDetail);
	}

	@RequestMapping(path=ApiMappings.OrderContact, method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> orderContact(@RequestBody Map<String,String> params){
		ResponseStatus status;
		try{
			String orderId, msg, subject;
			orderId = params.get("orderId");
			msg = params.get("message");
			subject = params.get("subject");
			orderService.contact(orderId, msg, subject);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}
	
	
	@RequestMapping(path=ApiMappings.GuidesNumber, method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> createGuideNumber(@RequestBody GuideNumber guideNumber){
		ResponseStatus status;
		try{
			orderService.createGuideNumber(guideNumber);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, guideNumber);
	}
	
	@RequestMapping(path=ApiMappings.GuidesNumber, method=RequestMethod.PUT)
	public @ResponseBody Map<String,? extends Object> updateGuideNumber(@RequestBody GuideNumber guideNumber){
		ResponseStatus status;
		try{
			orderService.updateGuideNumber(guideNumber);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, guideNumber);
	}
	
	@RequestMapping(path=ApiMappings.OrderGuidesNumber, method=RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getGuidesNumberByOrder(@RequestParam(name="order", required=true) Integer orderId){
		List<GuideNumber> guides;
		ResponseStatus status;
		try{
			guides = orderService.getGuideNumbersByOrder(orderId);
			status = ResponseStatus.OK;
		}catch(Exception e){
			guides = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, guides);
	}
	
	@RequestMapping(path=ApiMappings.GuidesNumber, method=RequestMethod.GET)
	public @ResponseBody Map<String,? extends Object> getGuidesNumberByIdOrGuide(
			@RequestParam(name="id", required=false) Integer id,
			@RequestParam(name="guide", required=false) String guideNumber){
		GuideNumber guide;
		ResponseStatus status;
		try{
			if(id!=null){
				guide = orderService.getGuideNumberById(id);
			}else{
				guide = orderService.getGuideNumberByGuideNumber(guideNumber);
			}
			status = ResponseStatus.OK;
		}catch(Exception e){
			guide = null;
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, guide);
	}
	
	
	@RequestMapping(path=ApiMappings.GuidesNumber, method=RequestMethod.DELETE)
	public @ResponseBody Map<String,? extends Object> deleteGuideNumber(@RequestParam(name="id", required=true) Integer guideId){
		ResponseStatus status;
		try{
			orderService.deleteGuideNumber(guideId);
			status = ResponseStatus.OK;
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
		}
		return ControllerHelper.mapResponse(status, null);
	}
}
