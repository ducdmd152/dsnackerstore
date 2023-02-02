package com.ducdmd152.springboot.dsnackerstore.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "sku")
	private String sku;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "status")
	private boolean status;

	public Product() {
	}

	public Product(String sku, String name, String description, int quantity, float price, boolean status) {
		super();
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + ", status=" + status + "]";
	}
}
