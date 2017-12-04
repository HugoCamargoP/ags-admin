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
	private String 	productDescrEs;
	private String 	productDescrEn;
	private String 	productDescrFr;
	private String 	productTitle;
	private Integer individualSales;
	
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

	public String getProductDescrEs() {
		return productDescrEs;
	}

	public void setProductDescrEs(String productDescrEs) {
		this.productDescrEs = productDescrEs;
	}

	public String getProductDescrEn() {
		return productDescrEn;
	}

	public void setProductDescrEn(String productDescrEn) {
		this.productDescrEn = productDescrEn;
	}

	public String getProductDescrFr() {
		return productDescrFr;
	}

	public void setProductDescrFr(String productDescrFr) {
		this.productDescrFr = productDescrFr;
	}
	
	/*******Getter and Setter productTitle***********/
	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	/*******Getter and Setter individualSales***********/
	public Integer getIndividualSales() {
		return individualSales;
	}

	public void setIndividualSales(Integer individualSales) {
		this.individualSales = individualSales;
	}	
}
