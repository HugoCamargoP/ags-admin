package com.arrowgs.agsadmin.entities;

import java.util.Date;

public class OrderRecord {

	private Integer id;
	private Integer order;
	private Integer state;
	private Date	update;
	
	//Extra Data
	private String	stateText;

	/************** Getter and Setter id **************/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	/************** Getter and Setter order **************/	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	/************** Getter and Setter state **************/
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/************** Getter and Setter update **************/
	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}
	
	/************** Getter and Setter stateText **************/
	public String getStateText() {
		return stateText;
	}

	public void setStateText(String stateText) {
		this.stateText = stateText;
	}
}
