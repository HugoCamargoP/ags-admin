package com.arrowgs.agsadmin.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arrowgs.agsadmin.controllers.cons.Constants.Mappings;
import com.arrowgs.agsadmin.controllers.cons.Constants.Views;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.service.OrderService;

@Controller
@RequestMapping(Views.Root)
public class RootController {
	
	@Autowired
	OrderService orderService;

	@RequestMapping(path={Mappings.Logout,
	                      Mappings.adminLogin,
	                      Mappings.adminHome,
	                      Mappings.adminMain,
	                      Mappings.adminCreateU,
	                      Mappings.adminUsers,
	                      Mappings.adminItems,
	                      Mappings.adminReports,
	                      Mappings.adminModifyU,
	                      Mappings.orders,
	                      Mappings.config})
	public void show(){
		
	}
	
	@RequestMapping(path = Mappings.order, method = RequestMethod.GET)
	public ModelAndView historyControllerOrder(@RequestParam(name="order",required=true) Integer idOrder, 
				HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		Order order = null;
		try{
			order = orderService.getOrderById(idOrder);

		}catch(Exception e){
			
		}
		mav.addObject("order", order);
		return mav;
	}

}
