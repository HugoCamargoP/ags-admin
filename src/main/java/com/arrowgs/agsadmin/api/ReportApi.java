package com.arrowgs.agsadmin.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.Report;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.helpers.SqlHelper;
import com.arrowgs.agsadmin.service.AddressService;
import com.arrowgs.agsadmin.service.OrderService;
import com.arrowgs.agsadmin.service.ProductService;
import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;


@RestController
public class ReportApi {


	@Resource
	List<Report> reports;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AddressService addressService;
	
	@RequestMapping(path = ApiMappings.ReportSchema, method = RequestMethod.GET)
	public List<Report> getReports(){
		return reports;
	}
	
	@RequestMapping(path = ApiMappings.SalesReportConstructor2, method = RequestMethod.GET)
	public Map<String,? extends Object> getOrderSalesReport(
			@RequestParam(required = false, name="product") Integer product,
			@RequestParam(required = false, name="talla") Integer sizeProduct,
			@RequestParam(required = false, name="sku") String sku,
			@RequestParam(required = false, name="since") Date since,
			@RequestParam(required = false, name="upTo") Date upTo,
			@RequestParam(required = false, name ="client") Integer client){
		
		Order order = new Order();
		order.setProduct(product);
		order.setSizeProduct(sizeProduct);
		order.setSku(sku);
		order.setClient(client);
		
		ResponseStatus status;
		List<Order> orders;
		try{
			orders = orderService.getSalesByFilter(order);
			
			status = ResponseStatus.OK;
		}catch(Exception e){
			orders = null;
			status = ResponseStatus.ExternalError;
		}
		
		return ControllerHelper.mapResponse(status, orders);
	}
	
	
	@RequestMapping(path = ApiMappings.SalesReportConstructor, method = RequestMethod.GET)
	public Map<String,? extends Object> getProductSalesReport(
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
			@RequestParam(required = false, name = "stocktakingFlag") boolean stocktakingFlag,
			@RequestParam(required = false, name = "sizeFlag") boolean salesBySize){
		
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
		response.put("productos", ordersDetail);
		response.put("ordenes", orders);
		response.put("inventario", products);
		response.put("tallaVentas", salesBySizeTable);
		return ControllerHelper.mapResponse(status, response);
	}
	
	@RequestMapping(path = ApiMappings.TopFive, method = RequestMethod.GET)
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
