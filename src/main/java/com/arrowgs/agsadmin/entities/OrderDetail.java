package com.arrowgs.agsadmin.entities;

public class OrderDetail {

	private Integer idDetail;
	private Integer idOrder;
	private Integer idProductSku;
	private Integer quantity;
	private Double 	individualPrice;
	
	/*Extra data*/
	private SkuProduct product;
	private String  url;
	private Double	amount;
	
	/*******Getter and Setter idDetail*********/
	public Integer getIdDetail(){
		return this.idDetail;
	}
		
	public void setIdDetail(Integer idDetail){
		this.idDetail = idDetail;
	}
	
	/*******Getter and Setter idOrder*********/	
	public Integer getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}	

	/*******Getter and Setter idProduct*******/
	public Integer getIdProductSku() {
		return this.idProductSku;
	}

	public void setIdProductSku(Integer idProductSku) {
		this.idProductSku = idProductSku;
	}
		
	/*******Getter and Setter Quantity*********/
	public Integer getQuantity() {
		return this.quantity;
	}
		
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
	/*******Getter and Setter individualPrice*********/
	public Double getIndividualPrice() {
		return individualPrice;
	}

	public void setIndividualPrice(Double individualPrice) {
		this.individualPrice = individualPrice;
	}

	/*******Getter and Setter product*********/
	public SkuProduct getProduct() {
		return product;
	}

	public void setProduct(SkuProduct product) {
		this.product = product;
	}

	/*******Getter and Setter url*********/
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	/*******Getter and Setter amount*********/
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
}
