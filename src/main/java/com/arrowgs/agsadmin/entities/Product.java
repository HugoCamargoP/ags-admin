package com.arrowgs.agsadmin.entities;

import java.util.List;

public class Product {

	private Integer id;
	private String 	description;	
	
	//Extra Data
	private List<ProductDetail> productDetails;
	private List<SkuProduct> skuProduct;
	
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

	
}
