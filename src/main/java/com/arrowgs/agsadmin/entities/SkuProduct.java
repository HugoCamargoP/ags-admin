package com.arrowgs.agsadmin.entities;

public class SkuProduct {

	private Integer id;	
	private Integer product;
	private String	sku;
	private Integer size;
	private Double 	price;
	private Integer	stock;
	
	/* Extra Data */
	private String sizeText;
	
	/*Extra Extra Data*/
	private String productDescr;
	
	/*******Getter and Setter price***********/
	public Double getPrice(){
		return this.price;
	}
	
	public void setPrice(Double price){
		this.price = price;
	}

	/*******Getter and Setter stock*******/
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/*******Getter and Setter id*******/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*******Getter and Setter product*******/
	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	/*******Getter and Setter sku*******/
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	/*******Getter and Setter size*******/
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	/*******Getter and Setter sizeText***********/
	public String getSizeText() {
		return sizeText;
	}

	public void setSizeText(String sizeText) {
		this.sizeText = sizeText;
	}

	/*******Getter and Setter productDescr***********/
	public String getProductDescr() {
		return productDescr;
	}

	public void setProductDescr(String productDescr) {
		this.productDescr = productDescr;
	}	
}
