package com.arrowgs.agsadmin.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product {

	private Integer id;
	private String 	descriptionEs;
	private String 	descriptionEn;
	private String 	descriptionFr;	
	private String	title;
	private Integer department;
	private Date	releaseDate;
	private String 	strReleaseDate;

	//Extra Data
	private List<ProductDetail> productDetails;
	private List<SkuProduct> 	skuProduct;
	private Integer 			sales;
	private List<OrderDetail>	ordersDetails;
	private String				departmentText;
	private String				userText;
	private String 				description;
	
	//Filter Data
	private String	sku;
	private Integer	talla; 
	private Double	greaterThan;
	private Double	lessThan;
	
	//Report Data Extra
	private Date	since;
	private Date	upTo;
	
	/*******Getter and Setter Sku*******/
	public Integer getId(){
		return this.id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	/*******Getter and Setter description*******/
	public String getDescriptionEs(){
		return this.descriptionEs;
	}
	
	public void setDescriptionEs(String descriptionEs) {
		this.descriptionEs = descriptionEs;
	}

	
	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionFr() {
		return descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
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

	@JsonIgnore
	public Date getReleaseDate() {
		return releaseDate;
	}

	@JsonIgnore
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}

	public Date getUpTo() {
		return upTo;
	}

	public void setUpTo(Date upTo) {
		this.upTo = upTo;
	}
	
	
	public String getStrReleaseDate() {
		String date = null;
		if(releaseDate!=null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.format(releaseDate);
		}
		return date;
	}

	public void setStrReleaseDate(String strReleaseDate) {
		this.strReleaseDate = strReleaseDate;
		if(this.strReleaseDate!=null){
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try{
				releaseDate = format.parse(this.strReleaseDate);
			}catch(Exception e){
				
			}
		}
	}

	
}
