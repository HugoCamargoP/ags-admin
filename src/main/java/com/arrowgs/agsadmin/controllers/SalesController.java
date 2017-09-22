package com.arrowgs.agsadmin.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arrowgs.agsadmin.controllers.cons.Constants.Mappings;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.SqlHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.service.OrderService;
import com.arrowgs.agsadmin.service.ProductService;

@Controller
@RequestMapping(Mappings.adminSales)
public class SalesController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getProductSalesReport(
			@RequestParam(required = false, name="product") Integer product,
			@RequestParam(required = false, name="size") Integer sizeProduct,
			@RequestParam(required = false, name="sku") String sku,
			@RequestParam(required = false, name="since") Date since,
			@RequestParam(required = false, name="upTo") Date upTo,
			@RequestParam(required = false, name ="client") Integer client,
			@RequestParam(required = false, name = "status")Integer statusOrder,
			@RequestParam(required = false, name = "salesFlag") boolean salesFlag,
			@RequestParam(required = false, name = "clientFlag") boolean clientFlag,
			@RequestParam(required = false, name = "ordersFlag") boolean ordersFlag,
			@RequestParam(required = false, name = "stocktakingFlag") boolean stocktakingFlag){
		
		ModelAndView mv = new ModelAndView();
		
		Order order = new Order();
		order.setProduct(product);
		order.setSizeProduct(sizeProduct);
		order.setSku(sku);
		order.setSince(since);
		order.setUpTo(upTo);
		order.setClient(client);
		order.setStatus(statusOrder);
		
		ResponseStatus status;
		List<OrderDetail> ordersDetail=null;
		List<Order> orders=null;
		List<Product> products = null;
		try{
			
			if(ordersFlag){				
				orders = orderService.getSalesByFilter(order);
			}
			else{
				
				if(salesFlag){
					order.setHistoric(OrderService.approvedOrder);
					order.setLastBoundQuery(OrderService.completedOrder);
					ordersDetail = orderService.getSalesProduct(order);
				}
				if(clientFlag){
					if(ordersDetail==null && client != null && client.intValue()>0){						
						order.setHistoric(OrderService.requestedOrder);
						order.setLastBoundQuery(OrderService.warning);
						ordersDetail = orderService.getSalesProduct(order);
						orders = orderService.getSalesByFilter(order);
					}
					
				}
				if(stocktakingFlag){
					Product productEnti = new Product();
					if(sku!=null){
						sku = SqlHelper.likeSpaceHelper(sku);
					}
					productEnti.setSku(sku);
					productEnti.setTalla(sizeProduct);
					products = productService.getProductsByFilter(productEnti, 1, 999999999);
				}
			}

			status = ResponseStatus.OK;
		}catch(Exception e){
			ordersDetail = null;
			status = ResponseStatus.ExternalError;
		}
		Map<String,Object> response = new HashMap<>();
		response.put("productos", ordersDetail);
		response.put("ordenes", orders);
		response.put("inventario", products);
		mv.addObject("response",ControllerHelper.mapResponse(status, response));
		return mv;
	}

}
