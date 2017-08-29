package com.arrowgs.agsadmin.daos;

import java.util.List;

import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderAmount;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.OrderRecord;

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
	void updateState(Order order, OrderRecord orderRecord);
	Integer getCountByFilter(Order order);
	List<Order> getOrdersByFilter(Order order, Integer page, Integer numOrder);
	
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
	
	//OrderRecord
	List<OrderRecord> getOrderRecordByOrder(Integer idOrder);
	void addOrderRecord(OrderRecord orderRecord);
	
	//OrderRecord
	List<OrderAmount> getOrderAmountByOrder(Integer idOrder);
	void removeOrderAmountById(Integer idOrderAmount);
	OrderAmount addOrderAmount(OrderAmount orderAmount);
}
