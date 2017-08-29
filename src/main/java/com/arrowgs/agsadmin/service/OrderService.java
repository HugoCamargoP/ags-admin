package com.arrowgs.agsadmin.service;

import java.util.List;

import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderAmount;
import com.arrowgs.agsadmin.entities.OrderDetail;

public interface OrderService {
	
	final static String	 detailPay = "Costo pedido";
	
	final public static int statusShoppingCar  = 2;
	final public static int statusWishList	   = 1;
	
	//Order
	List<Order> getOrders();
	Order getOrderById(Integer id);
	List<Order> getOrderByFilter(Order order, Integer page, Integer numOrder);
	List<Order> getOrderByUsuario(Integer user);
	List<Order> getOrderByState(Integer state);
	Order getUserCar(Integer user, Integer status);
	boolean addOrder(Order order);
	boolean updateOrder(Order order);
	void updateStateOrder(Order order);
	boolean deleteOrder(Integer idOrden);
	Integer getCountByFilter(Order order);
	boolean checkingShoppingCar(Integer user);
	
	
	/*OrderDetail*/
	List<OrderDetail> getOrderDetailByOrder(Integer id);
	void fromWishListToShoppingCar(OrderDetail orderDetail);
	void removeOrderDetail(Integer id);
	boolean removeOneOrderDetail(Integer idDetail);
	boolean insertOrderDetail(OrderDetail orderDetail);
	boolean modifyOrderProdudct(OrderDetail orderDetail);	
	boolean increaseProduct(Integer idDetail);
	boolean decreaseProduct(Integer idDetail);	
	
	/*OrderAmount*/
	List<OrderAmount> getOrderAmountByOrder(Integer idOrder);
	boolean createOrderAmount(OrderAmount orderAmount);	
	
}
