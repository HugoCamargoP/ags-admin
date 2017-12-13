package com.arrowgs.agsadmin.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.arrowgs.agsadmin.daos.OrderDao;
import com.arrowgs.agsadmin.entities.IdNameTable;
import com.arrowgs.agsadmin.entities.Order;
import com.arrowgs.agsadmin.entities.OrderAmount;
import com.arrowgs.agsadmin.entities.OrderDetail;
import com.arrowgs.agsadmin.entities.OrderRecord;
import com.arrowgs.agsadmin.entities.User;
import com.arrowgs.agsadmin.service.OrderService;


@Repository
public class OrderDaoImplementation implements OrderDao{

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private PlatformTransactionManager transactionManager;
	
	private SimpleJdbcInsert orderInsertActor;
	private SimpleJdbcInsert orderDetailInsertActor;
	private SimpleJdbcInsert orderRecordInsertActor;
	private SimpleJdbcInsert orderAmountInsertActor;
	
	
	
	/*Order*/
	class OrderRowMapper implements RowMapper<Order>{
		
		private boolean expandible;
		
		public OrderRowMapper(boolean expandible) {		
			this.expandible = expandible;
		}

		@Override
		public Order mapRow(ResultSet rs, int row) throws SQLException {
			Order orden = new Order();
			orden.setId(rs.getInt(1));
			orden.setUser(rs.getInt(2));
			orden.setAddress(rs.getInt(3));
			orden.setState(rs.getInt(4));
			orden.setCreation(rs.getDate(5));	
			orden.setComment(rs.getString(6));
			orden.setEnable(rs.getInt(7));
			
			if(expandible){
				orden.setUserText(rs.getString(8));
				orden.setStatusText(rs.getString(9));
			}
			
			return orden;
		}
		
	}
	
	class OrderRowExtractor implements ResultSetExtractor<Order>{
		
		private boolean expandible;
		
		public OrderRowExtractor(boolean expandible) {
			this.expandible = expandible;
		}
		
		@Override
		public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
						
			return rs.next() ? (new OrderRowMapper(expandible)).mapRow(rs, 0) : null;
		}
		
	}
	
	/*OrderDetail*/
	
	class OrderDetailRowMapper implements RowMapper<OrderDetail>{

		@Override
		public OrderDetail mapRow(ResultSet rs, int row) throws SQLException {
			OrderDetail detalle = new OrderDetail();
			detalle.setIdDetail(rs.getInt(1));
			detalle.setIdOrder(rs.getInt(2));
			detalle.setIdProductSku(rs.getInt(3));
			detalle.setQuantity(rs.getInt(4));
			detalle.setIndividualPrice(rs.getDouble(5));
			detalle.setGuideNumber(rs.getString(6));
			detalle.setShippingCompany(rs.getString(7));
			return detalle;
		}
		
	}
	
	class OrderDetailRowExtractor implements ResultSetExtractor<OrderDetail>{

		@Override
		public OrderDetail extractData(ResultSet rs) throws SQLException, DataAccessException {		
			return rs.next() ? (new OrderDetailRowMapper()).mapRow(rs, 0) : null;
		}
		
	}	
	
	/*OrderRecord*/
	
	class OrderRecordRowMapper implements RowMapper<OrderRecord>{
		
		private boolean expandible;
		
		public OrderRecordRowMapper(boolean expandible) {
			this.expandible = expandible;
		}

		@Override
		public OrderRecord mapRow(ResultSet rs, int col) throws SQLException {
			OrderRecord orderRecord = new OrderRecord();
			orderRecord.setId(rs.getInt(1));
			orderRecord.setOrder(rs.getInt(2));
			orderRecord.setState(rs.getInt(3));
			orderRecord.setUpdate(rs.getDate(4));
			if(expandible)
			{
				orderRecord.setStateText(rs.getString(5));
			}
			return orderRecord;
		}
		
	}
	
	class OrderRecordRowExtractor implements ResultSetExtractor<OrderRecord>{
		
		private boolean expandible;
		
		public OrderRecordRowExtractor(boolean expandible) {
			this.expandible = expandible;
		}

		@Override
		public OrderRecord extractData(ResultSet rs) throws SQLException, DataAccessException {
			return rs.next() ? (new OrderRecordRowMapper(expandible)).mapRow(rs, 0) : null;
		}		
	}
	
	
	/*Order Amount*/
	class OrderAmountRowMapper implements RowMapper<OrderAmount>{
		private boolean expandible;
		
		public OrderAmountRowMapper(boolean expandible) {
			this.expandible = expandible;
		}

		@Override
		public OrderAmount mapRow(ResultSet rs, int row) throws SQLException {
			OrderAmount orden = new OrderAmount();
			orden.setId(rs.getInt(1));
			orden.setOrder(rs.getInt(2));
			orden.setAmount(rs.getDouble(3));
			orden.setDetail(rs.getString(4));
			orden.setTenderType(rs.getInt(5));
			orden.setVariety(rs.getString(6));
			if(expandible){
				orden.setTenderTypeText(rs.getString(7));
			}
			return orden;
		}
		
	}
	
	class OrderAmountRowExtractor implements ResultSetExtractor<OrderAmount>{
		
		private boolean expandible;
		
		public OrderAmountRowExtractor(boolean expandible) {
			this.expandible = expandible;
		}

		@Override
		public OrderAmount extractData(ResultSet rs) throws SQLException, DataAccessException {
						
			return rs.next() ? (new OrderAmountRowMapper(expandible)).mapRow(rs, 0) : null;
		}
		
	}
	
	
	//Tipos de Pago
	
	class IdNameTableRowMapper implements RowMapper<IdNameTable>{

		@Override
		public IdNameTable mapRow(ResultSet rs, int rowNum) throws SQLException {
			IdNameTable tender = new IdNameTable();
			tender.setId(rs.getInt(1));
			tender.setName(rs.getString(2));
			
			return tender;
		}
		
	}
	
	class IdNameTableRowExtractor implements ResultSetExtractor<IdNameTable>{

		@Override
		public IdNameTable extractData(ResultSet rs) throws SQLException, DataAccessException {
			return rs.next() ? (new IdNameTableRowMapper()).mapRow(rs, 0) : null;
		}
		
	}
	
	class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int row) throws SQLException {
			User myUser = new User();
			myUser.setEmail(rs.getString(1));
			myUser.setType(rs.getString(2));			
			myUser.setName(rs.getString(3));
			myUser.setPassword(rs.getString(4));
			myUser.setId(rs.getInt(5));
			myUser.setAmount(rs.getDouble(6));
			myUser.setQuantity(rs.getInt(7));
			return myUser;
		}
		
	}
	
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		orderInsertActor = new SimpleJdbcInsert(dataSource)
								.withTableName(OrdersTable)
								.usingGeneratedKeyColumns("idOrden");
		
		orderDetailInsertActor = new SimpleJdbcInsert(dataSource)
				.withTableName(OrderDetailTables)
				.usingGeneratedKeyColumns("id");	
		
		orderRecordInsertActor = new SimpleJdbcInsert(dataSource)
				.withTableName(OrderRecordTable)
				.usingGeneratedKeyColumns("id");
		
		orderAmountInsertActor = new SimpleJdbcInsert(dataSource)
				.withTableName(OrderAmountTable)
				.usingGeneratedKeyColumns("id");
	}
	
	@Autowired
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	
	
	
	
	@Override
	public List<Order> getOrders() {
		
		String query = "select * from ordenes";
		
		return jdbcTemplate.query(query, new OrderRowMapper(false));
	}

	@Override
	public Order getOrderById(Integer id) {
		String query = "select * from ordenes where id = :id";
		SqlParameterSource mapOrder = new MapSqlParameterSource("id",id);
		return  jdbcTemplate.query(query, mapOrder, new OrderRowExtractor(false));
	}

	@Override
	public List<Order> getOrderByUsuario(Integer user) {
		String query = "select * from ordenes where usuario = :user";
		SqlParameterSource mapOrder = new MapSqlParameterSource("user",user);
		return jdbcTemplate.query(query, mapOrder, new OrderRowMapper(false));		
	}

	@Override
	public List<Order> getOrderByState(Integer state) {
		String sql = "select * from ordenes where estado = :state";
		SqlParameterSource mapOrder = new MapSqlParameterSource("state",state);		
		return jdbcTemplate.query(sql, mapOrder, new OrderRowMapper(false));
	}

	
	//Insert order method
	@Override
	public void addOrder(Order order) {
		Map<String,Object> orden = new HashMap<String,Object>();		
		orden.put("usuario", order.getUser());
		orden.put("domicilio", order.getAddress());
		orden.put("estado", order.getState());		
		orden.put("creacion",order.getCreation());
		orden.put("comentario", order.getComment());
		
		Number idOrder = orderInsertActor.executeAndReturnKey(orden);
		order.setId(idOrder.intValue());
	}

	@Override
	public void updateOrder(Order order) {
		String sql = "update ordenes set usuario = :usuario, domicilio = :domicilio, creacion = :creacion, comentario = :comentario where id = :idOrden";
		Map<String,Object> namedParameters = new HashMap<>();
		namedParameters.put("usuario", order.getUser());
		namedParameters.put("domicilio", order.getAddress());
		namedParameters.put("creacion", order.getCreation());
		namedParameters.put("comentario", order.getComment());
		namedParameters.put("idOrden", order.getId());
		jdbcTemplate.update(sql, namedParameters);
	}
	
	@Override
	public void updateStatus(Order order, OrderRecord orderRecord) {
		TransactionStatus transactionStatus =
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
			String sql = "update ordenes set estado = :estado where id = :idOrden";
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("estado", order.getState());
			paramMap.put("idOrden", order.getId());
			jdbcTemplate.update(sql, paramMap);
						
			addOrderRecord(orderRecord);
			
			transactionManager.commit(transactionStatus);
		}catch(Exception e){
			transactionManager.rollback(transactionStatus);
		}
	}
	

	@Override
	public Order getCarOrder(Integer user, Integer status) {
		String sql = "select * from ordenes where usuario = :user and estado = :status";
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("user", user);
		namedParameters.put("status", status);
		return jdbcTemplate.query(sql, namedParameters, new OrderRowExtractor(false));
	}

	@Override
	public void deleteOrder(Integer idOrden) {	
		TransactionStatus transactionStatus =
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
			removeOrderDetailByOrder(idOrden);
			String sql = "delete from ordenes where id = :idOrden";
			SqlParameterSource paramMap = new MapSqlParameterSource("idOrden",idOrden);
			jdbcTemplate.update(sql, paramMap);
			
			transactionManager.commit(transactionStatus);
		}catch(Exception e){
			transactionManager.rollback(transactionStatus);			
			throw e;
		}
	}
	
	
	@Override
	public List<Order> getOrdersByFilter(Order order, Integer page, Integer numOrder) {		
		page = ((page-1) * numOrder);
		StringBuilder sql = new StringBuilder("SELECT o.*, u.email, e.descripcion FROM ordenes o LEFT JOIN usuarios u ON o.usuario = u.id LEFT JOIN estados e ON e.id = o.estado");
		StringBuilder aux = new StringBuilder("");
		boolean where = false;
		Map<String,Object> paramMap = new HashMap<>();
		if(order.getState()!=null && order.getState()!=0){
			aux.append(" o.estado = :estado");
			paramMap.put("estado", order.getState());
			where= true;
		}
		if(order.getCreation()!=null){
			if(where){
				aux.append(" AND");
			}
			aux.append(" o.creacion >= :fecha");
			paramMap.put("fecha", order.getCreation());
			where= true;
		}
		if(order.getUser()!=null){
			if(where){
				aux.append(" AND");
			}
			aux.append(" u.email like :usuario");
			paramMap.put("usuario", order.getUserText());
			where= true;
		}
		if(where){
			sql.append(" WHERE");
			sql.append(aux);
		}
		sql.append(" LIMIT :begin , :end");
		paramMap.put("begin", page);
		paramMap.put("end",numOrder);
		return jdbcTemplate.query(sql.toString(), paramMap, new OrderRowMapper(true));
	}

	
	@Override
	public Integer getCountByFilter(Order order) {
		StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ordenes o LEFT JOIN usuarios u ON o.usuario = u.id");
		StringBuilder aux = new StringBuilder("");
		boolean where = false;
		Map<String,Object> paramMap = new HashMap<>();
		if(order.getState()!=null && order.getState()!=0){
			aux.append(" o.estado = :estado");
			paramMap.put("estado", order.getState());
			where= true;
		}
		if(order.getCreation()!=null){
			if(where){
				aux.append(" AND");
			}
			aux.append(" o.creacion >= :fecha");
			paramMap.put("fecha", order.getCreation());
			where= true;
		}
		if(order.getUser()!=null){
			if(where){
				aux.append(" AND");
			}
			aux.append(" u.email like :usuario");
			paramMap.put("usuario", order.getUserText());
			where= true;
		}
		if(where){
			sql.append(" WHERE");
			sql.append(aux);
		}
		
		List<Integer> counting = jdbcTemplate.query(sql.toString(), paramMap, new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int col) throws SQLException {
				return new Integer(rs.getInt(1));
			}
			
		});
		return counting.get(0);
	}

	
	/*Order Detail*/
	@Override
	public List<OrderDetail> getOrderDetailByOrder(Integer id) {
		String sql = "select * from orden_detalles where orden = :id ORDER BY id DESC";
		SqlParameterSource mapOrder = new MapSqlParameterSource("id",id);		
		return jdbcTemplate.query(sql,mapOrder, new OrderDetailRowMapper());
	}
	
	@Override
	public OrderDetail getOrderDetailByOrderAndProduct(Integer idOrder, Integer idProduct) {
		String sql = "SELECT * FROM orden_detalles WHERE id_producto_sku = :sku and orden = :orden";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("sku",idProduct);
		paramMap.put("orden", idOrder);
		return jdbcTemplate.query(sql,paramMap , new OrderDetailRowExtractor());
	}
	
	@Override
	public OrderDetail getOrderDetailById(Integer id) {
		String sql = "SELECT * FROM orden_detalles WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",id);
		return jdbcTemplate.query(sql, paramMap, new OrderDetailRowExtractor());
	}
	
	

	@Override
	public void removeOrderDetailByOrder(Integer idOrder) {
		String sql = "delete from orden_detalles where orden = :id";
		SqlParameterSource namedParameter = new MapSqlParameterSource("id",idOrder);
		jdbcTemplate.update(sql, namedParameter);
		
	}

	@Override
	public void addOneOrderDetail(OrderDetail orderDetail) {		
		Map<String,Object> mapOrderDetail = new HashMap<String,Object>();		
		mapOrderDetail.put("orden", orderDetail.getIdOrder());
		mapOrderDetail.put("id_producto_sku", orderDetail.getIdProductSku());
		mapOrderDetail.put("cantidad", orderDetail.getQuantity());
		mapOrderDetail.put("precio_individual", orderDetail.getIndividualPrice());
		mapOrderDetail.put("numero_guia", orderDetail.getGuideNumber());
		mapOrderDetail.put("compania_envio", orderDetail.getShippingCompany());
		Number idDetail = orderDetailInsertActor.executeAndReturnKey(mapOrderDetail);
		orderDetail.setIdDetail(idDetail.intValue());
	}

	@Override
	public void modifyOrderProduct(OrderDetail orderDetail) {		
		String sql = "update orden_detalles set orden = :orden, id_producto_sku = :producto, cantidad = :cantidad, numero_guia = :guia, compania_envio = :envio where id = :id";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("orden", orderDetail.getIdOrder());
		paramMap.put("id_producto_sku", orderDetail.getIdProductSku());
		paramMap.put("cantidad", orderDetail.getQuantity());
		paramMap.put("id", orderDetail.getIdDetail());
		paramMap.put("guia", orderDetail.getGuideNumber());
		paramMap.put("envio", orderDetail.getShippingCompany());
		jdbcTemplate.update(sql, paramMap);
	}
	
	@Override
	public void modifyOrderProduct(List<OrderDetail> orderDetails) {
		TransactionStatus transactionStatus =
				transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
			Iterator<OrderDetail> iterator = orderDetails.iterator();
			while(iterator.hasNext()){
				modifyOrderProduct(iterator.next());
			}
			transactionManager.commit(transactionStatus);
		}catch(Exception e){
			transactionManager.rollback(transactionStatus);
			throw e;
		}
		
	}

	@Override
	public void incrementProductDetail(Integer idOrderDetail) {
		String sql = "update orden_detalles set cantidad = cantidad +1 where id = :idOrderDetail";
		SqlParameterSource paramMap = new MapSqlParameterSource("idOrderDetail",idOrderDetail);
		jdbcTemplate.update(sql, paramMap);		
	}
	
	@Override
	public void incrementProductDetail(Integer idOrderDetail, Integer extra) {
		String sql = "update orden_detalles set cantidad = cantidad + :extra where id = :idOrderDetail";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("idOrderDetail",idOrderDetail);
		paramMap.put("extra", extra);
		jdbcTemplate.update(sql, paramMap);	
	}	

	@Override
	public void decrementProductDetail(Integer idOrderDetail) {
		String sql = "update orden_detalles set cantidad = cantidad -1 where id = :idOrderDetail";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("idOrderDetail",idOrderDetail);
		jdbcTemplate.update(sql, paramMap);			
	}

	@Override
	public void removeOneOrderDetail(Integer idDetail) {
		String sql = "DELETE FROM orden_detalles WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idDetail);
		jdbcTemplate.update(sql, paramMap);
	}

	
	/*OrderRecord*/
	@Override
	public List<OrderRecord> getOrderRecordByOrder(Integer idOrder) {
		String sql = "SELECT oh.*, e.descripcion FROM orden_historico oh LEFT JOIN estados e ON oh.estado = e.id WHERE orden = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idOrder);
		return jdbcTemplate.query(sql, paramMap, new OrderRecordRowMapper(true));
	}

	@Override
	public void addOrderRecord(OrderRecord orderRecord) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("orden", orderRecord.getOrder());
		paramMap.put("estado",orderRecord.getState());
		paramMap.put("actualizacion", orderRecord.getUpdate());
		orderRecordInsertActor.execute(paramMap);
	}

	
	/*Order Amount*/
	@Override
	public List<OrderAmount> getOrderAmountByOrder(Integer idOrder) {
		String sql = "SELECT oc.*, tp.descripcion FROM orden_costos oc LEFT JOIN tipos_pago tp ON oc.medio_pago = tp.id WHERE orden = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idOrder);
		return jdbcTemplate.query(sql, paramMap, new OrderAmountRowMapper(true));
	}

	@Override
	public OrderAmount addOrderAmount(OrderAmount orderAmount) {
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("orden", orderAmount.getOrder());
		paramMap.put("costo", orderAmount.getAmount());
		paramMap.put("detalle", orderAmount.getDetail());
		paramMap.put("medio_pago", orderAmount.getTenderType());
		paramMap.put("variedad", orderAmount.getVariety());
					
		Number id = orderAmountInsertActor.executeAndReturnKey(paramMap);
		orderAmount.setId(id.intValue());
		return orderAmount;
				
	}

	@Override
	public void removeOrderAmountById(Integer idOrderAmount) {
		String sql = "DELETE FROM orden_costos WHERE id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource("id",idOrderAmount);
		jdbcTemplate.update(sql, paramMap);
	}
	
	@Override
	public List<OrderAmount> getTopFiveOrderAmount() {
		String sql = "SELECT oc.id,oc.orden,SUM(oc.costo) AS costo, oc.detalle, oc.medio_pago, oc.variedad, tp.descripcion FROM orden_costos oc LEFT JOIN tipos_pago tp ON oc.medio_pago = tp.id GROUP BY orden ORDER BY costo DESC LIMIT 5";
		return jdbcTemplate.query(sql, new OrderAmountRowMapper(true));
	}

	@Override
	public List<Order> getSalesByFilter(Order order) {
		StringBuilder sql = new StringBuilder("SELECT DISTINCT(o.id),o.usuario,o.domicilio,o.estado,o.creacion,o.comentario,o.status, u.email,e.descripcion FROM ordenes o LEFT JOIN orden_detalles od ON o.id = od.orden LEFT JOIN productos_sku ps ON od.id_producto_sku = ps.id LEFT JOIN productos p on ps.producto = p.id LEFT JOIN usuarios u ON o.usuario = u.id LEFT JOIN orden_historico oh ON oh.orden = o.id LEFT JOIN estados e ON e.id = o.estado");
		StringBuilder aux = new StringBuilder("");
		Map<String,Object> paramMap = new HashMap<>();
		boolean where = false;
		
		if(order.getHistoric()!=null && order.getHistoric().intValue()>0){
			aux.append(" oh.estado >= :historic");
			paramMap.put("historic", order.getHistoric());
			where = true;
		}
		if(order.getStatus()!=null && order.getStatus().intValue()>0){
			if(where){
				aux.append(" AND");
			}
			aux.append(" o.estado = :estado");
			paramMap.put("estado", order.getStatus());
			where = true;
		}
		
		if(order.getSince()!=null && order.getSince().toString().length()>0){
			if(where){
				aux.append(" AND");
			}			
			aux.append(" (oh.actualizacion >= :desde OR o.creacion >= :desde)");
			paramMap.put("desde", order.getSince());
			where=true;
		}
		
		if(order.getUpTo()!=null && order.getUpTo().toString().length()>0){
			if(where){
				aux.append(" AND");
			}			
			aux.append(" (oh.actualizacion <= :hasta OR o.creacion <= :hasta)");
			paramMap.put("hasta", order.getUpTo());
			where=true;
		}
		if(order.getProduct()!=null && order.getProduct().intValue()>0){
			if(where){
				aux.append(" AND");
			}
			aux.append(" ps.producto = :producto");
			paramMap.put("producto", order.getProduct());
			where = true;
		}
		if(order.getSizeProduct()!=null && order.getSizeProduct().intValue()>0 ){
			if(where){
				aux.append(" AND");
			}
			aux.append(" ps.talla = :talla");
			paramMap.put("talla", order.getSizeProduct());
			where = true;
		}
		if(order.getClient()!=null && order.getClient().intValue()>0){
			if(where){
				aux.append(" AND");
			}
			aux.append(" o.usuario = :usuario");
			paramMap.put("usuario", order.getClient());
			where = true;
		}
		if(where){
			sql.append(" WHERE");
		}
		sql.append(aux);
		sql.append(" ORDER BY o.usuario, o.estado, o.creacion");
		return jdbcTemplate.query(sql.toString(), paramMap, new OrderRowMapper(true));
	}

	@Override
	public List<OrderDetail> getOrderDetailByFilter(Order order) {
		StringBuilder sql = new StringBuilder("SELECT od.* FROM orden_detalles od LEFT JOIN productos_sku ps ON od.id_producto_sku = ps.id WHERE od.orden = :orden");
		StringBuilder aux = new StringBuilder("");
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("orden", order.getId());
		if(order.getSizeProduct()!=null && order.getSizeProduct().intValue()>0){
			aux.append(" AND ps.talla = :talla");
			paramMap.put("talla", order.getSizeProduct());
		}
		if(order.getSku()!=null && order.getSku().length() > 0){
			aux.append(" AND ps.sku = :sku");
			paramMap.put("sku", order.getSku());
		}
		sql.append(aux);
		return jdbcTemplate.query(sql.toString(), paramMap, new OrderDetailRowMapper());
	}

	@Override
	public List<OrderDetail> getSalesProduct(Order order) {
		StringBuilder sql = new StringBuilder("SELECT DISTINCT(od.id), od.orden, od.id_producto_sku, od.cantidad, od.precio_individual, od.numero_guia, od.compania_envio FROM orden_detalles od LEFT JOIN ordenes o on od.orden = o.id LEFT JOIN productos_sku ps ON od.id_producto_sku = ps.id LEFT JOIN orden_historico oh ON oh.orden = o.id");
		StringBuilder aux = new StringBuilder("");
		Map<String,Object> paramMap = new HashMap<>();
		if(order.getSizeProduct()!=null && order.getSizeProduct().intValue()>0){
			aux.append(" AND ps.talla = :talla");
			paramMap.put("talla", order.getSizeProduct());
		}
		if(order.getProduct()!=null && order.getProduct().intValue()>0){			
			aux.append(" AND ps.producto = :producto");
			paramMap.put("producto", order.getProduct());
		}
		if(order.getSku()!=null && order.getSku().length()>0){
			aux.append(" AND ps.sku = :sku");
			paramMap.put("sku", order.getSku());
		}
		if(order.getSince()!=null && order.getSince().toString().length()>0){
			aux.append(" AND oh.actualizacion >= :desde");
			paramMap.put("desde", order.getSince());
		}
		if(order.getUpTo()!=null && order.getUpTo().toString().length()>0){
			aux.append(" AND oh.actualizacion <= :hasta");
			paramMap.put("hasta", order.getUpTo());
		}
		if(order.getClient()!=null && order.getClient().intValue()>0){
			aux.append(" AND o.usuario = :usuario");
			paramMap.put("usuario", order.getClient());
		}
		if(order.getStatus()!=null && order.getStatus().intValue()>0){
			aux.append(" AND o.estado = :estado");
			paramMap.put("estado", order.getStatus());
		}
		sql.append(" WHERE oh.estado >= :approved AND oh.estado <= :warning");
		paramMap.put("approved", order.getHistoric());
		paramMap.put("warning", order.getLastBoundQuery());
		sql.append(aux);
		sql.append(" ORDER BY ps.producto,od.id_producto_sku");
		return jdbcTemplate.query(sql.toString(), paramMap, new OrderDetailRowMapper());
	}

	@Override
	public Date getDateByOrderAndStatus(Integer order, Integer status) {
		String sql = "SELECT actualizacion FROM orden_historico WHERE orden = :orden AND estado = :status";
		MapSqlParameterSource paramMap = new MapSqlParameterSource("orden",order);
		paramMap.addValue("status", status);
		List<Date> dates = jdbcTemplate.query(sql, paramMap, new RowMapper<Date>(){

			@Override
			public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
				Date date;
				date = rs.getDate(1);
				return date;
			}
		});
		return dates.get(0);
	}

	@Override
	public List<Order> getTopOrdersSales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdNameTable> getTenderTypes() {
		String sql = "SELECT * FROM tipos_pago";
		return jdbcTemplate.query(sql, new IdNameTableRowMapper());
	}

	@Override
	public List<User> topFiveCustomer() {
		String sql = "SELECT u.*, SUM((od.precio_individual * od.cantidad)) AS gasto, SUM(od.cantidad) AS productos FROM orden_detalles od LEFT JOIN ordenes o ON od.orden = o.id LEFT JOIN usuarios u ON o.usuario = u.id WHERE o.estado >=:approved AND o.estado < :warning GROUP BY u.id ORDER BY gasto DESC LIMIT 5";
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("approved", OrderService.approvedOrder);
		paramMap.put("warning", OrderService.warning);		
		return jdbcTemplate.query(sql, paramMap, new UserRowMapper());
	}


}
