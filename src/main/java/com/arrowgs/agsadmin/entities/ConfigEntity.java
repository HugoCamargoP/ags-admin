package com.arrowgs.agsadmin.entities;

public class ConfigEntity {

	private Integer id;
	private Integer iva;
	private Double	littleBox;
	private Double	mediumBox;
	private Double	bigBox;
	private Double	shipmentCost;
	private Double	dollarCost;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIva() {
		return iva;
	}
	public void setIva(Integer iva) {
		this.iva = iva;
	}
	public Double getLittleBox() {
		return littleBox;
	}
	public void setLittleBox(Double littleBox) {
		this.littleBox = littleBox;
	}
	public Double getMediumBox() {
		return mediumBox;
	}
	public void setMediumBox(Double mediumBox) {
		this.mediumBox = mediumBox;
	}
	public Double getBigBox() {
		return bigBox;
	}
	public void setBigBox(Double bigBox) {
		this.bigBox = bigBox;
	}
	public Double getShipmentCost() {
		return shipmentCost;
	}
	public void setShipmentCost(Double shipmentCost) {
		this.shipmentCost = shipmentCost;
	}
	public Double getDollarCost() {
		return dollarCost;
	}
	public void setDollarCost(Double dollarCost) {
		this.dollarCost = dollarCost;
	}
	
	

}
