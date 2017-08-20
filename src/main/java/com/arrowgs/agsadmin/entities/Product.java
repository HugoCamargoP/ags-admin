package com.arrowgs.agsadmin.entities;

import java.util.List;

public class Product {

	private Integer id;
	private String 	description;	
	
	//Extra Data
	private List<ProductDetail> productDetails;
	private List<SkuProduct> skuProduct;
	
	//Filter Data
	private String	sku;
	private Integer	talla; 
	
	/*******Getter and Setter Sku*******/
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	/*******Getter and Setter description*******/
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	/*******Getter and Setter productDetail*******/
	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}

	/*******Getter and Setter skuProduct*******/
	public List<SkuProduct> getSkuProduct() {
		return skuProduct;
	}

	public void setSkuProduct(List<SkuProduct> skuProduct) {
		this.skuProduct = skuProduct;
	}

	
	/*******Getter and Setter sku*******/
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	/*******Getter and Setter talla*******/
	public Integer getTalla() {
		return talla;
	}

	public void setTalla(Integer talla) {
		this.talla = talla;
	}

	
}
