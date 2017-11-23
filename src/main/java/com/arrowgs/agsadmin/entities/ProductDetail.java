package com.arrowgs.agsadmin.entities;

public class ProductDetail {

	private Integer id;
	private Integer product;
	private String url;
		
	private Integer side;
	
	/************** Getter and Setter id **************/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	/************** Getter and Setter product **************/
	public Integer getProduct() {
		return product;
	}
	public void setProduct(Integer product) {
		this.product = product;
	}
	
	/************** Getter and Setter url **************/
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	/************** Getter and Setter side **************/
	public Integer getSide() {
		return side;
	}
	public void setSide(Integer side) {
		this.side = side;
	}
}
