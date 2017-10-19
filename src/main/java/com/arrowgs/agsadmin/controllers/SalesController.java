package com.arrowgs.agsadmin.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arrowgs.agsadmin.controllers.cons.Constants.Mappings;
import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.SqlHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.service.AddressService;
import com.arrowgs.agsadmin.service.OrderService;
import com.arrowgs.agsadmin.service.ProductService;

@Controller
@RequestMapping(Mappings.adminSales)
public class SalesController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AddressService addressService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getProductSalesReport(
			@RequestParam(required = false, name="product") Integer product,
			@RequestParam(required = false, name="size") Integer sizeProduct,
			@RequestParam(required = false, name="sku") String sku,
			@RequestParam(required = false, name="since") @DateTimeFormat(pattern = "dd-MM-yyyy") Date since,
			@RequestParam(required = false, name="upTo") @DateTimeFormat(pattern = "dd-MM-yyyy") Date upTo,
			@RequestParam(required = false, name ="client") Integer client,
			@RequestParam(required = false, name = "status")Integer statusOrder,
			@RequestParam(required = false, name = "salesFlag") boolean salesFlag,
			@RequestParam(required = false, name = "clientFlag") boolean clientFlag,
			@RequestParam(required = false, name = "ordersFlag") boolean ordersFlag,
			@RequestParam(required = false, name = "stocktakingFlag") boolean stocktakingFlag,
			@RequestParam(required = false, name = "sizeFlag") boolean salesBySize){
		
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
		List<IdNumTable> salesBySizeTable = null;
		try{
			
			if(ordersFlag){				
				orders = orderService.getSalesByFilter(order);
			}
			else{
				
				if(salesFlag){
					order.setHistoric(OrderService.approvedOrder);
					order.setLastBoundQuery(OrderService.completedOrder);
					ordersDetail = orderService.getSalesProduct(order);
					products = productService.makeProductListByOrderedOrderDetail(ordersDetail);
					if(products!=null && products.size()>0){
						Iterator<Product> iterator = products.iterator();
						while(iterator.hasNext()){
							Product actual = iterator.next();
							actual.setSince(order.getSince());
							actual.setUpTo(order.getUpTo());
						}
					}
					ordersDetail=null;
				}
				if(clientFlag){
					if(ordersDetail==null && client != null && client.intValue()>0){						
						order.setHistoric(OrderService.requestedOrder);
						order.setLastBoundQuery(OrderService.warning);
						ordersDetail = orderService.getSalesProduct(order);
						orders = orderService.getSalesByFilter(order);
						products = productService.makeProductListByOrderedOrderDetail(ordersDetail);
						ordersDetail = null;
						
						if(products!=null && products.size()>0)
						{
							Iterator<Product> iterator = products.iterator();
							Order using = orders.get(0);
							while(iterator.hasNext()){
								Product actual = iterator.next();
								actual.setUserText(using.getUserText());
								actual.setSince(order.getSince());
								actual.setUpTo(order.getUpTo());
							}
						}
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
				if(salesBySize){
					salesBySizeTable = productService.getSalesBySize();
				}
			}

			status = ResponseStatus.OK;
		}catch(Exception e){
			ordersDetail = null;
			status = ResponseStatus.ExternalError;
		}
		Map<String,Object> response = new HashMap<>();
		response.put("productos", products);
		response.put("ordenes", orders);
		if(stocktakingFlag)
		{
			response.put("inventario", products);
		}
		response.put("tallaVentas", salesBySizeTable);
		mv.addObject("response",ControllerHelper.mapResponse(status, response));
		return mv;
	}
	
	@RequestMapping(path = Mappings.adminTopFive, method = RequestMethod.GET)
	public Map<String,? extends Object> getTopFive(@RequestParam(name="top",required=true) Integer choose){
		Map<String,Object> result = null;
		ResponseStatus status;
		switch(choose){
		case 1:
			List<Product> products = productService.topProducts();
			status = ResponseStatus.OK;
			result = ControllerHelper.mapResponse(status, products);
			break;
		case 2:
			break;
		case 3:
			List<Order> orders = orderService.topFiveOrders();
			status = ResponseStatus.OK;
			result = ControllerHelper.mapResponse(status, orders);
			break;
		case 4:
			List<IdNumTable> topCountries = addressService.getTopCountries();
			status = ResponseStatus.OK;
			result = ControllerHelper.mapResponse(status, topCountries);
			break;
		default:
		}
		return result;
	}

}
