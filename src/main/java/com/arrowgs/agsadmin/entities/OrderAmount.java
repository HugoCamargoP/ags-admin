package com.arrowgs.agsadmin.entities;

public class OrderAmount {

	private Integer id;
	private Integer order;
	private Double	amount;
	private String	detail;
	private Integer	tenderType;
	private String	variety;
	
	//Extra Data
	private String tenderTypeText;
	
	/*********** Getter and Setter id ***********/
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	/*********** Getter and Setter order ***********/
	public Integer getOrder() {
		return order;
	}
	
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	/*********** Getter and Setter amount ***********/
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	/*********** Getter and Setter detail ***********/
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	
	/*********** Getter and Setter tenderType ***********/
	public Integer getTenderType() {
		return tenderType;
	}

	public void setTenderType(Integer tenderType) {
		this.tenderType = tenderType;
	}

	
	/*********** Getter and Setter variety ***********/
	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	
	/*********** Getter and Setter tenderTypeText ***********/
	public String getTenderTypeText() {
		return tenderTypeText;
	}

	public void setTenderTypeText(String tenderTypeText) {
		this.tenderTypeText = tenderTypeText;
	}
	
	
}
