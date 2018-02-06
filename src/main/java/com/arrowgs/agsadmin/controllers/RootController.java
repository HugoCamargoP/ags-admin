package com.arrowgs.agsadmin.controllers;

import java.util.Locale;

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
import com.arrowgs.agsadmin.entities.User;
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
				HttpSession session,
				Locale locale){
		
		ModelAndView mav = new ModelAndView();
		Order order = null;
		try{
			User user = (User) session.getAttribute("userSession");
			if(user!=null){
				order = orderService.getOrderById(idOrder);
				if(order.getUser().intValue() != user.getId().intValue()){
					order = null;
				}
			}

		}catch(Exception e){
			
		}
		mav.addObject("order", order);
		return mav;
	}

}
