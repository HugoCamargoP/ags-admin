package com.arrowgs.agsadmin.daos;

import java.util.Date;
import java.util.List;

import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderAmount;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.OrderRecord;
import com.arrowgs.agsadmin.entities.User;

public interface OrderDao {

	static final String OrdersTable         = "ordenes";
	static final String OrderDetailTables   = "orden_detalles";
	static final String OrderRecordTable	= "orden_historico";
	static final String OrderAmountTable	= "orden_costos";
	
	
	List<Order> getOrders();
	Order getOrderById(Integer id);
	List<Order> getOrderByUsuario(Integer user);
	List<Order> getOrderByState(Integer state);	
	Order getCarOrder(Integer user, Integer status);
	void deleteOrder(Integer idOrden);	
	void addOrder(Order order);
	void updateOrder(Order order);
	void updateStatus(Order order, OrderRecord orderRecord);
	Integer getCountByFilter(Order order);
	List<Order> getOrdersByFilter(Order order, Integer page, Integer numOrder);
	List<Order> getTopOrdersSales();
	
	//OrderDetail
	List<OrderDetail> getOrderDetailByOrder(Integer id);
	OrderDetail getOrderDetailByOrderAndProduct(Integer idOrder, Integer idProduct);
	OrderDetail getOrderDetailById(Integer id);
	void incrementProductDetail(Integer idOrderDetail);
	void incrementProductDetail(Integer idOrderDetail, Integer extra);
	void decrementProductDetail(Integer idOrderDetail);
	void removeOrderDetailByOrder(Integer idOrder);
	void addOneOrderDetail(OrderDetail orderDetail);
	void removeOneOrderDetail(Integer idDetail);
	void modifyOrderProduct(OrderDetail orderDetail);
	void modifyOrderProduct(List<OrderDetail> orderDetails);
	
	//OrderRecord
	List<OrderRecord> getOrderRecordByOrder(Integer idOrder);
	void addOrderRecord(OrderRecord orderRecord);
	Date getDateByOrderAndStatus(Integer order, Integer status);
	
	//OrderAmount
	List<OrderAmount> getOrderAmountByOrder(Integer idOrder);
	List<OrderAmount> getTopFiveOrderAmount();
	void removeOrderAmountById(Integer idOrderAmount);
	OrderAmount addOrderAmount(OrderAmount orderAmount);
	
	//Sales report
	List<Order> getSalesByFilter(Order order);
	List<OrderDetail> getSalesProduct(Order order);
	List<OrderDetail> getOrderDetailByFilter(Order order);
	
	//Tipos Pago
	List<IdNameTable> getTenderTypes();
	
	//Top User
	List<User> topFiveCustomer();
	
	
}
