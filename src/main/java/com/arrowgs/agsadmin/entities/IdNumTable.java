package com.arrowgs.agsadmin.entities;

import java.util.Date;

public class IdNumTable {

	private Integer	id;
	private Integer num;
	
	/*Extra Data*/
	private String	name;
	private Double	doubleAttribute;
	
	/*Report Data*/
	private Date	since;
	private Date	upTo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getDoubleAttribute() {
		return doubleAttribute;
	}
	public void setDoubleAttribute(Double doubleAttribute) {
		this.doubleAttribute = doubleAttribute;
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
	

}
