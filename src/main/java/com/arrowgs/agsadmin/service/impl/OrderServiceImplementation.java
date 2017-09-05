package com.arrowgs.agsadmin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arrowgs.agsadmin.daos.OrderDao;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderAmount;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.OrderRecord;
import com.arrowgs.agsadmin.entities.ProductDetail;
import com.arrowgs.agsadmin.entities.SkuProduct;
import com.arrowgs.agsadmin.service.OrderService;
import com.arrowgs.agsadmin.service.ProductService;

@Service
public class OrderServiceImplementation implements OrderService {

	private static Logger logger = LoggerFactory.getLogger(OrderServiceImplementation.class);
	
	@Autowired
	OrderDao orderDao;	
	
	@Autowired
	ProductService productService;
	
	
	@Override
	public List<Order> getOrders() {
		List<Order> orders;
		try{
			orders = orderDao.getOrders();
		}
		catch(Exception e){			
			logger.error("OrderService : getOrders: "+ e.toString());
			throw e;			
		}
		return orders;
	}

	@Override
	public Order getOrderById(Integer id) {
		try{			
			Order userOrder = orderDao.getOrderById(id);			
			userOrder.setOrderDetail(getOrderDetailByOrder(userOrder.getId()));
			
			return userOrder;
		}
		catch(Exception e){
			logger.error("Order Service : getOrderById : "+ e.toString());			
			throw e;
		}
	}

	@Override
	public List<Order> getOrderByUsuario(Integer user) {
		try{
			List<Order> userOrder = orderDao.getOrderByUsuario(user);
			Iterator<Order> myIterator = userOrder.iterator();
			while(myIterator.hasNext()){
				Order actualOrder = myIterator.next();
				actualOrder.setOrderDetail(getOrderDetailByOrder(actualOrder.getId()));
				Iterator<OrderDetail> orderDetail = actualOrder.getOrderDetail().iterator();
				while(orderDetail.hasNext()){
					OrderDetail myActual = orderDetail.next();
					myActual.setProduct(productService.getSkuProductById(myActual.getIdProductSku()));
				}
			}
			return userOrder;
		}
		catch(Exception e){
			logger.error("OrderService : getOrderByUsuario: "+ e.toString());
			throw e;
		}
	}
	
	@Override
	public boolean checkingShoppingCar(Integer user) {
		boolean result = true;
		try{
			Order order = getUserCar(user, statusShoppingCar);
			Iterator<OrderDetail> detailList = order.getOrderDetail().iterator();
			while(detailList.hasNext()){
				OrderDetail actual = detailList.next();	
				Integer stock = actual.getProduct().getStock(),
						amount = actual.getQuantity();				
				if(amount > stock){
					actual.setQuantity(stock);
					orderDao.modifyOrderProduct(actual);
					result = false;
				}
			}
		}catch(Exception e){
			result = false;
			logger.error("OrderService : checkingShoppingCar : "+ e.toString());
		}
		return result;
	}
	
	

	@Override
	public List<Order> getOrderByState(Integer state) {
		try{
			return orderDao.getOrderByState(state);
		}
		catch(Exception e){
			logger.error("OrderService : getOrderByState : " + e.toString() );
			throw e;
		}
	}
	
	@Override
	public List<Order> getOrderByFilter(Order order, Integer page, Integer numOrder) {
		List<Order> orders;
		try{
			orders = orderDao.getOrdersByFilter(order, page, numOrder);
			Iterator<Order> iterator = orders.iterator();
			while(iterator.hasNext()){
				Order actualOrder = iterator.next();
				actualOrder.setOrderDetail(getOrderDetailByOrder(actualOrder.getId()));
				Iterator<OrderDetail> iteratorDetail = order.getOrderDetail().iterator();
				while(iteratorDetail.hasNext()){
					OrderDetail orderDetail = iteratorDetail.next();
					SkuProduct sku = productService.getSkuProductById(orderDetail.getIdProductSku());
					orderDetail.setProduct(sku);
					ProductDetail product = productService.oneProductDetail(sku.getProduct());
					orderDetail.setUrl(product.getUrl());
				}			
			}
		}catch(Exception e){
			orders = null;
			logger.error("OrderService : getOrderByFilter : " + e.toString());
			throw e;			
		}
		return orders;
	}	
	
	@Override
	public Integer getCountByFilter(Order order) {	
		try{
			return orderDao.getCountByFilter(order);
		}catch(Exception e){
			logger.error("OrderService : getCountByFilter : " + e.toString());
			throw e;
		}
	}	

	@Override
	public boolean addOrder(Order order) {
		boolean result = false;
		try{
			order.setCreation(new Date());
			orderDao.addOrder(order);
			result = true;
		}catch(Exception e){
			logger.error("OrderService : addOrder : " + e.toString());
		}
		return result;
	}

	@Override
	public boolean updateOrder(Order order) {
		boolean result = false;
		try{
			orderDao.updateOrder(order);
			result = true;
		}catch(Exception e){
			logger.error("OrderService : updateOrderr : " + e.toString());
		}
		return result;
	}
	
	@Override
	public void updateStateOrder(Order order) {	
		try{
			OrderRecord orderRecord = new OrderRecord();		
			orderRecord.setOrder(order.getId());
			orderRecord.setState(order.getState());
			orderRecord.setUpdate(new Date());
			orderDao.updateState(order, orderRecord); 
		}catch(Exception e){
			logger.error("OrderService : updateStateOrder : " + e.toString());
			throw e;
		}
	}		

	@Override
	public Order getUserCar(Integer user, Integer status) {
		Order order;
		try{
			order = orderDao.getCarOrder(user, status);
			if(order!=null)
			{
				order.setOrderDetail(getOrderDetailByOrder(order.getId()));
				Iterator<OrderDetail> iterator = order.getOrderDetail().iterator();
				while(iterator.hasNext()){
					OrderDetail orderDetail = iterator.next();
					SkuProduct sku = productService.getSkuProductById(orderDetail.getIdProductSku());
					orderDetail.setProduct(sku);
					ProductDetail product = productService.oneProductDetail(sku.getProduct());
					orderDetail.setUrl(product.getUrl());
				}
			}
		}catch(Exception e){
			logger.error("OrderService : getUserCar : " + e.toString());
			throw e;		
		}
		return order;
	}

	@Override
	public boolean deleteOrder(Integer idOrden) {
		boolean result = false;
		try{			
			orderDao.deleteOrder(idOrden);
			result = true;
		}catch(Exception e){
			logger.error("OrderService : deleteOrder : " + e.toString());
		}
		return result;
	}
	
	@Override
	public List<OrderDetail> getOrderDetailByOrder(Integer id) {
		try{
			return orderDao.getOrderDetailByOrder(id);
		}catch(Exception e){
			logger.error("OrderService : getOrderDetailByOrder : " + e.toString());
			throw e;
		}
	}

	@Override
	public void removeOrderDetail(Integer id) {
		try{
			orderDao.removeOrderDetailByOrder(id);
		}catch(Exception e){
			logger.error("OrderService : removeOrderDetail : " + e.toString());
			throw e;
		}
		
	}

	@Override
	public boolean insertOrderDetail(OrderDetail orderDetail) {
		boolean result = false;
		try{
			OrderDetail searching = orderDao.getOrderDetailByOrderAndProduct(orderDetail.getIdOrder(), orderDetail.getIdProductSku());
			if(searching==null)
			{
				orderDao.addOneOrderDetail(orderDetail);
			}
			else
			{
				increaseProduct(searching.getIdDetail());
			}
			result = true;
		}catch(Exception e){
			logger.error("OrderService : insertOrderDetail : " + e.toString());
		}
		return result;
	}

	@Override
	public boolean modifyOrderProdudct(OrderDetail orderDetail) {
		boolean result = false;
		try{						
			orderDao.modifyOrderProduct(orderDetail);
			result = true;
		}catch(Exception e){
			logger.error("OrderService : modifyOrderProduct : " + e.toString());
		}
		return result;
	}

	@Override
	public boolean increaseProduct(Integer idDetail) {
		boolean result = false;
		try{									
			OrderDetail order = orderDao.getOrderDetailById(idDetail);
			order.setProduct(productService.getSkuProductById(order.getIdProductSku()));
			if(order.getProduct().getStock() >= order.getQuantity() + 1){
				orderDao.incrementProductDetail(idDetail);
				result = true;
			}			
		}catch(Exception e){
			logger.error("OrderService : increaseProduct : " + e.toString());
		}
		return result;
	}

	@Override
	public boolean decreaseProduct(Integer idDetail) {
		boolean result = false;
		try{
			OrderDetail order = orderDao.getOrderDetailById(idDetail);
			if(order.getQuantity()-1 >0)
			{
				orderDao.decrementProductDetail(idDetail);
				result = true;
			}			
		}catch(Exception e){
			logger.error("OrderService : decreaseProduct : " + e.toString());
		}
		return result;
	}

	@Override
	public boolean removeOneOrderDetail(Integer idDetail) {
		boolean result = false;
		try{
			orderDao.removeOneOrderDetail(idDetail);
			result = true;
		}catch(Exception e){
			logger.error("OrderService : removeOneOrderDetail : " + e.toString());
		}
		return result;
	}

	@Override
	public void fromWishListToShoppingCar(OrderDetail orderDetail) {
		try{					
			Order order = orderDao.getOrderById(orderDetail.getIdOrder());
			Order car = orderDao.getCarOrder(order.getUser(), statusShoppingCar);
			orderDetail.setIdOrder(car.getId());
			Integer id = orderDetail.getIdDetail();
			insertOrderDetail(orderDetail);			
			orderDao.removeOneOrderDetail(id);
		}catch(Exception e){
			logger.error("OrderService : fromWishListToShoppingCar : " + e.toString());
			throw e;
		}
		
	}
	
	/*OrderAmount*/

	@Override
	public List<OrderAmount> getOrderAmountByOrder(Integer idOrder) {	
		try{
			return orderDao.getOrderAmountByOrder(idOrder);
		}catch(Exception e){
			logger.error("OrderService : getOrderAmountByOrder : " + e.toString());
			throw e;
		}
	}

	@Override
	public boolean createOrderAmount(OrderAmount orderAmount) {
		boolean result = true;
		List<OrderDetail> orderDetail = orderDao.getOrderDetailByOrder(orderAmount.getOrder());		
		List<SkuProduct> productList = new ArrayList<>();
		Iterator<OrderDetail> iterator = orderDetail.iterator();
		Double total = 0.0;
		while(iterator.hasNext()){
			OrderDetail actual = iterator.next();
			SkuProduct product = productService.getSkuProductById(actual.getIdProductSku());
			Integer stock = product.getStock(),
					amount = actual.getQuantity();	
			if(amount > stock){
				actual.setQuantity(stock);
				result = false;
			}			
			if(result){
				total = total + (product.getPrice() * amount);
				product.setStock(stock - amount);
				productList.add(product);
			}
		}
		if(result)
		{
			orderAmount.setAmount(total);
			orderAmount.setDetail(detailPay);
			OrderAmount finalAmount = orderDao.addOrderAmount(orderAmount);
			try
			{					
				productService.modifySkuProductList(productList);
			}catch(Exception e){
				orderDao.removeOrderAmountById(finalAmount.getId());
				logger.error("OrderService : createOrderAmount : " + e.toString());				
				result = false;
			}
		}		
		return result;
	}

	@Override
	public List<Order> getSalesByFilter(Order order) {
		List<Order> orders;
		try{
			orders = orderDao.getSalesByFilter(order);
			if(orders!=null){
				Iterator<Order> iterator = orders.iterator();
				while(iterator.hasNext()){
					Order actual = iterator.next();
					order.setId(actual.getId());
					actual.setOrderDetail(orderDao.getOrderDetailByFilter(order));
				}
			}
		}catch(Exception e){
			logger.error("OrderService : getSalesByFilter : " + e.toString());
			throw e;
		}
		return orders;
	}

	@Override
	public List<OrderDetail> getSalesProduct(Order order) {
		List<OrderDetail> ordersDetail;
		try{
			List<OrderDetail> detailByDao = orderDao.getSalesProduct(order);			
			ordersDetail = null;
			if(detailByDao!=null){
				Iterator<OrderDetail> iterator = detailByDao.iterator();
				ordersDetail = new ArrayList<>();
				int quantity = 0, skuId=0;

				while(iterator.hasNext()){
					OrderDetail actual = iterator.next();
					if(ordersDetail.isEmpty()){
						ordersDetail.add(actual);
						skuId= actual.getIdProductSku();						
					}
					if(actual.getIdProductSku().intValue()!=skuId){
						OrderDetail last = ordersDetail.get(ordersDetail.size()-1);
						SkuProduct product = productService.getSkuProductById(skuId);
						last.setAmount(quantity * product.getPrice());
						last.setQuantity(quantity);
						last.setProduct(product);
						
						skuId = actual.getIdProductSku();
						quantity = actual.getQuantity();
						if(!iterator.hasNext()){
							actual.setQuantity(quantity);
							actual.setProduct(productService.getSkuProductById(skuId));
							actual.setAmount(quantity * actual.getProduct().getPrice());							
						}
						ordersDetail.add(actual);
						
					}else{
						quantity = quantity + actual.getQuantity();
						if(!iterator.hasNext()){
							OrderDetail last = ordersDetail.get(ordersDetail.size()-1);
							last.setQuantity(quantity);
							last.setProduct(productService.getSkuProductById(skuId));
							last.setAmount(quantity * actual.getProduct().getPrice());							
						}						
					}					
				}
			}
		}catch(Exception e){
			logger.error("OrderService : getSalesProduct : " + e.toString());
			throw e;
		}
		return ordersDetail;
	}
	
}
