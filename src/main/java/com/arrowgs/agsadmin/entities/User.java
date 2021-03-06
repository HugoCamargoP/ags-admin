package com.arrowgs.agsadmin.entities;

public class User {
	
	private String 	email;
	private String 	name;
	private String 	password;
	private String 	type;
	private Integer	id;
	
	
	/*PDF Extra Data */
	private Double	amount;
	private	Integer	quantity;
	
	/********Getter and Setter name****************/
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	/********Getter and Setter email******************/
	public String getEmail(){
		return this.email;
	}
		
	public void setEmail(String email){
		this.email = email;
	}
	
	/********Getter and Setter password**************/
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	/********Getter and Setter type*****************/
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}

	/********Getter and Setter id****************/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/********Getter and Setter amount*****************/
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
	/********Getter and Setter quantity*****************/
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	

}
