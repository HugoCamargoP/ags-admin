package com.arrowgs.agsadmin.entities;

public class GuideNumber {

	private Integer	id;
	private	Integer	order;
	private String	guideNumber;
	private	String	carrier;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getGuideNumber() {
		return guideNumber;
	}
	public void setGuideNumber(String guideNumber) {
		this.guideNumber = guideNumber;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

}
