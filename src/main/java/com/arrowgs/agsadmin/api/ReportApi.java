package com.arrowgs.agsadmin.api;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arrowgs.agsadmin.controllers.cons.Constants.ApiMappings;
import com.arrowgs.agsadmin.entities.IdNumTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.Product;
import com.arrowgs.agsadmin.entities.Report;
import com.arrowgs.agsadmin.entities.User;
import com.arrowgs.agsadmin.helpers.ControllerHelper;
import com.arrowgs.agsadmin.helpers.ControllerHelper.ResponseStatus;
import com.arrowgs.agsadmin.helpers.SqlHelper;
import com.arrowgs.agsadmin.service.AddressService;
import com.arrowgs.agsadmin.service.JasperService;
import com.arrowgs.agsadmin.service.OrderService;
import com.arrowgs.agsadmin.service.ProductService;


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
	
	@Autowired
	JasperService jasperService;
	
	private static Logger logger = LoggerFactory.getLogger(ReportApi.class);
	
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
			@RequestParam(required = false, name="since") @DateTimeFormat(pattern = "dd-MM-yyyy") Date since,
			@RequestParam(required = false, name="upTo") @DateTimeFormat(pattern = "dd-MM-yyyy") Date upTo,
			@RequestParam(required = false, name ="client") Integer client,
			@RequestParam(required = false, name = "status")Integer statusOrder,
			@RequestParam(required = false, name = "salesFlag") boolean salesFlag,
			@RequestParam(required = false, name = "clientFlag") boolean clientFlag,
			@RequestParam(required = false, name = "ordersFlag") boolean ordersFlag,
			@RequestParam(required = false, name = "stocktakingFlag") boolean stocktakingFlag,
			@RequestParam(required = false, name = "sizeFlag") boolean salesBySize,
			HttpServletResponse responseServlet){
		
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
		Integer reportType=0;
		Map<String,Object> response = new HashMap<>();
		try{
			
			if(ordersFlag){				
				orders = orderService.getSalesByFilter(order);
				reportType=1;
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
					reportType=2;
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
						reportType=3;
						//reportType=6;
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
					reportType=4;
				}
				if(salesBySize){
					salesBySizeTable = productService.getSalesBySize();
					if(salesBySizeTable!=null && salesBySizeTable.size()>0)
					{
						salesBySizeTable.get(0).setSince(order.getSince());
						salesBySizeTable.get(0).setUpTo(order.getUpTo());
						
					}
					reportType=5;
				}
			}
			ByteArrayOutputStream pdf = new ByteArrayOutputStream();			
			pdf = jasperService.getReportPdf(reportType, orders,products, salesBySizeTable);			
			byte[] os = pdf.toByteArray();
			responseServlet.setContentLength(os.length);
			responseServlet.setContentType("application/pdf");
			responseServlet.addHeader("Content-Disposition","attachment; filename=reporte.pdf");
			
			responseServlet.getOutputStream().write(os);
			responseServlet.getOutputStream().flush();
			
			status = ResponseStatus.OK;
		}catch(Exception e){
			ordersDetail = null;
			status = ResponseStatus.ExternalError;
			response.put("CATCH:", e);
			logger.error("ReportApi : getProductSalesReport :"+e.toString());
		}

		response.put("productos", products);
		response.put("ordenes", orders);
		if(stocktakingFlag)
		{
			response.put("inventario", products);
		}
		response.put("tallaVentas", salesBySizeTable);
		return ControllerHelper.mapResponse(status, response);
	}
	
	@RequestMapping(path = ApiMappings.TopFive, method = RequestMethod.GET)
	public Map<String,? extends Object> getTopFive(@RequestParam(name="top",required=true) Integer choose, HttpServletResponse responseServlet){
		Map<String,Object> result = null;
		ResponseStatus status;
		List<Product> products = null;
		List<Order> orders = null;
		List<IdNumTable> topCountries = null;
		List<User> users = null;
		ByteArrayOutputStream pdf = new ByteArrayOutputStream();	
		try{
			switch(choose){
			case 1:
				products = productService.topProducts();
				status = ResponseStatus.OK;
				result = ControllerHelper.mapResponse(status, products);
				break;
			case 2:
				users = orderService.topFiveCustomer();
				status = ResponseStatus.OK;
				result = ControllerHelper.mapResponse(status, users);
				break;
			case 3:
				orders = orderService.topFiveOrders();
				status = ResponseStatus.OK;
				result = ControllerHelper.mapResponse(status, orders);
				break;
			case 4:
				topCountries = addressService.getTopCountries();
				status = ResponseStatus.OK;
				result = ControllerHelper.mapResponse(status, topCountries);
				break;
			default:
			}
			
			pdf = jasperService.getTopFivePdf(orders, products, topCountries,users);
			
			byte[] os = pdf.toByteArray();
			responseServlet.setContentLength(os.length);
			responseServlet.setContentType("application/pdf");
			responseServlet.addHeader("Content-Disposition","attachment; filename=reporte.pdf");
			
			responseServlet.getOutputStream().write(os);
			responseServlet.getOutputStream().flush();
			
			status = ResponseStatus.OK;
			result = ControllerHelper.mapResponse(status, result);
		}catch(Exception e){
			status = ResponseStatus.ExternalError;
			result = ControllerHelper.mapResponse(status, null);
			System.out.println(e);
		}
		return result;
	}
}
