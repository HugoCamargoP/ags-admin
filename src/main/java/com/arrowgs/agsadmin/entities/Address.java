package com.arrowgs.agsadmin.entities;

public class Address {

	private Integer id;
	private Integer  user;
	private Integer country;
	private String  detail1;
	private String  detail2;
	private String  city;
	private String  state;
	private String  zip;
	private String  phone;
	
	
	/* Extra Data */
	private String countryName;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getUser() {
		return user;
	}
	
	public void setUser(Integer user) {
		this.user = user;
	}
	
	
	public Integer getCountry() {
		return country;
	}
	
	public void setCountry(Integer country) {
		this.country = country;
	}
	
	
	public String getDetail1() {
		return detail1;
	}
	
	public void setDetail1(String detail1) {
		this.detail1 = detail1;
	}
	
	
	public String getDetail2() {
		return detail2;
	}
	
	public void setDetail2(String detail2) {
		this.detail2 = detail2;
	}
	
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	} 
	
	
}
