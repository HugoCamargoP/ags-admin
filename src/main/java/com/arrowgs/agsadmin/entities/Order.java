package com.arrowgs.agsadmin.entities;

import java.util.Date;
import java.util.List;

public class Order {
	
	private Integer id;
	private Integer user;
	private Integer address;
	private Integer state;
	private Date creation;
	private String comment;
	private Integer	enable;
	private Integer facturacion;

	private Double totalAmount;
	
	/***************Extra Data****************/
	private List<OrderDetail> orderDetail;
	private List<OrderRecord> orderRecord;
	private List<OrderAmount> orderAmount;
	private String 	userText;
	private String 	statusText;
	private Date	statusDate;
	
	/***************Filter Data****************/
	private String	sku;
	private Integer product;
	private Integer sizeProduct;
	private Integer client;
	private Integer status;
	private Date	since;
	private Date	upTo;
	private Integer	historic;
	private Integer	lastBoundQuery;
	

	/***********Getter and Setter id**********/
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	/***********Getter and Setter user********/
	public Integer getUser(){
		return this.user;
	}
	
	public void setUser(Integer user){
		this.user = user;
	}
	
	/***********Getter and Setter state*******/
	public Integer getState(){
		return this.state;
	}
	
	public void setState(Integer state){
		this.state = state;
	}
	
	/***********Getter and Setter creation*****/
	public Date getCreation(){
		return this.creation;
	}
	
	public void setCreation(Date creation){
		this.creation = creation;
	}
	
	
	/***********Getter and Setter orderDetail**********/
	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	
	/***********Getter and Setter orderRecord**********/
	public List<OrderRecord> getOrderRecord() {
		return orderRecord;
	}

	public void setOrderRecord(List<OrderRecord> orderRecord) {
		this.orderRecord = orderRecord;
	}

	/***********Getter and Setter orderAmount**********/
	public List<OrderAmount> getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(List<OrderAmount> orderAmount) {
		this.orderAmount = orderAmount;
	}

	/***********Getter and Setter Address**********/
	public Integer getAddress() {
		return address;
	}
	
	public void setAddress(Integer address) {
		this.address = address;
	}
	
	
	/***********Getter and Setter comment**********/	

	public void setComment(String comment) {
		this.comment = comment;
	}	

	public String getComment() {
		return comment;
	}
	
	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer status) {
		this.enable = status;
	}	
	public Integer getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(Integer facturacion) {
		this.facturacion = facturacion;
	}

	/***********Getter and Setter totalAmount**********/
	
	public Double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/***********Getter and Setter userText********/
	
	public String getUserText() {
		return userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}

	
	/***********Getter and Setter creation*****/
	
	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/***********Getter and Setter sku FILTER********/
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	
	/***********Getter and Setter product FILTER********/
	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	
	/***********Getter and Setter sizeProduct FILTER********/
	public Integer getSizeProduct() {
		return sizeProduct;
	}

	public void setSizeProduct(Integer sizeProduct) {
		this.sizeProduct = sizeProduct;
	}

	
	/***********Getter and Setter client FILTER********/
	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	
	/***********Getter and Setter status FILTER********/
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/***********Getter and Setter since FILTER**********/
	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}

	/***********Getter and Setter upTo FILTER**********/
	public Date getUpTo() {
		return upTo;
	}

	public void setUpTo(Date upTo) {
		this.upTo = upTo;
	}

	
	/***********Getter and Setter historic**********/
	public Integer getHistoric() {
		return historic;
	}

	public void setHistoric(Integer historic) {
		this.historic = historic;
	}

	
	/***********Getter and Setter lastBoundQuery**********/
	public Integer getLastBoundQuery() {
		return lastBoundQuery;
	}

	public void setLastBoundQuery(Integer lastBoundQuery) {
		this.lastBoundQuery = lastBoundQuery;
	}
	

	/***********Getter and Setter StatusDate**********/
	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	
	
	
}
