package com.prueba.pruebaTia.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

	private String sku;
	private String des;
	private Double price;
	private Double incremen;

	public Product() {

	}

	public Product(String sku, String des, Double price, Double incremen) {
		this.sku = sku;
		this.des = des;
		this.price = price;
		this.incremen = incremen;
	}

	    @Id
//	    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sku", nullable = false)
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Column(name = "des", nullable = false)
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Column(name = "price", nullable = false)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "incremen", nullable = false)
	public Double getIncremen() {
		return incremen;
	}

	public void setIncremen(Double incremen) {
		this.incremen = incremen;
	}

}
