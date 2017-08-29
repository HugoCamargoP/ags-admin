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

	
	/***************Extra Data****************/
	private List<OrderDetail> orderDetail;
	private String userText;

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

	
	/***********Getter and Setter userText********/
	
	public String getUserText() {
		return userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}
	
	
	
}
