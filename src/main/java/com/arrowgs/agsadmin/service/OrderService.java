package com.arrowgs.agsadmin.service;

import java.util.List;

import com.arrowgs.agsadmin.entities.GuideNumber;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderAmount;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.User;

public interface OrderService {
	
	final static String	 detailPay = "Costo pedido";
	
	final public static int warning			   = 8;
	final public static int completedOrder	   = 7;
	final public static int sendedOrder		   = 6;
	final public static int approvedOrder      = 5;
	final public static int requestedOrder 	   = 4;
	final public static int statusShoppingCar  = 3;
	final public static int statusPreOrder     = 2;
	final public static int statusWishList	   = 1;
	
	//Order
	List<Order> getOrders();
	Order getOrderById(Integer id);
	List<Order> getOrderByFilter(Order order, Integer page, Integer numOrder);
	List<Order> getOrderByUsuario(Integer user);
	List<Order> getOrderByState(Integer state);
	List<Order> topFiveOrders();
	Order getUserCar(Integer user, Integer status);
	boolean addOrder(Order order);
	boolean updateOrder(Order order);
	void updateOrderStatus(Order order);
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
	void modifyOrderProduct(List<OrderDetail> orderDetails);
	boolean increaseProduct(Integer idDetail);
	boolean decreaseProduct(Integer idDetail);	
	
	/*OrderAmount*/
	List<OrderAmount> getOrderAmountByOrder(Integer idOrder);
	List<OrderAmount> getTopFiveOrderAmount();
	boolean createOrderAmount(OrderAmount orderAmount);	
	
	/*Sales*/
	List<Order> getSalesByFilter(Order order);
	List<OrderDetail> getSalesProduct(Order order);
	
	
	/*Tender Type*/
	List<IdNameTable> getTenderTypes();
	
	/*TOP FIVE CUSTOMER*/
	List<User> topFiveCustomer();
	
	/*Status*/
	List<IdNameTable> getStatus();
	
	/*Contact*/
	void contact(String orderId, String msg, String subject);
	
	/* GuideNumber*/
	List<GuideNumber> getGuideNumbersByOrder(Integer orderId);
	GuideNumber	getGuideNumberById(Integer id);
	GuideNumber getGuideNumberByGuideNumber(String guideNumber);
	void createGuideNumber(GuideNumber guide);
	void updateGuideNumber(GuideNumber guide);
	void deleteGuideNumber(Integer guideId);
}
