package com.arrowgs.agsadmin.entities;

import java.util.List;

public class Product {

	private Integer id;
	private String 	description;
	private String	title;
	private Integer department;
	
	//Extra Data
	private List<ProductDetail> productDetails;
	private List<SkuProduct> 	skuProduct;
	private Integer 			sales;
	private List<OrderDetail>	ordersDetails;
	private String				departmentText;
	private String				userText;
	
	//Filter Data
	private String	sku;
	private Integer	talla; 
	private Double	greaterThan;
	private Double	lessThan;
	
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

	
	/*******Getter and Setter title*******/
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	/*******Getter and Setter department*******/
	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
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

	
	/*******Getter and Setter GreaterThan*******/
	public Double getGreaterThan() {
		return greaterThan;
	}

	public void setGreaterThan(Double greaterThan) {
		this.greaterThan = greaterThan;
	}

	
	/*******Getter and Setter LessThan*******/
	public Double getLessThan() {
		return lessThan;
	}

	public void setLessThan(Double lessThan) {
		this.lessThan = lessThan;
	}

	
	/*******Getter and Setter Sales*******/
	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	
	/*******Getter and Setter OrdersDetails*******/
	public List<OrderDetail> getOrdersDetails() {
		return ordersDetails;
	}

	public void setOrdersDetails(List<OrderDetail> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}

	
	/*******Getter and Setter DepartmentText*******/
	public String getDepartmentText() {
		return departmentText;
	}

	public void setDepartmentText(String departmentText) {
		this.departmentText = departmentText;
	}

	public String getUserText() {
		return userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}

	
}
