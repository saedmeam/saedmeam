package com.prueba.pruebaTia.dto;

public class ProductDTO {

	private String sku;
	private String des;
	private Double price;
	private Double incremen;
	private Double incrementPrice;
	private Integer maxDuesNo;

	public ProductDTO() {

	}

	public ProductDTO(String sku, String des, Double price, Double incremen, Double incrementPrice, Integer maxDuesNo) {
		this.sku = sku;
		this.des = des;
		this.price = price;
		this.incremen = incremen;
		this.incrementPrice = incrementPrice;
		this.maxDuesNo = maxDuesNo;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getIncremen() {
		return incremen;
	}

	public void setIncremen(Double incremen) {
		this.incremen = incremen;
	}

	public Double getIncrementPrice() {
		return incrementPrice;
	}

	public void setIncrementPrice(Double incrementPrice) {
		this.incrementPrice = incrementPrice;
	}

	public Integer getMaxDuesNo() {
		return maxDuesNo;
	}

	public void setMaxDuesNo(Integer maxDuesNo) {
		this.maxDuesNo = maxDuesNo;
	}

}
