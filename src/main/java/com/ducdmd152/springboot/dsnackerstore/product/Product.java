package com.ducdmd152.springboot.dsnackerstore.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "sku")
	@NotNull(message="Sku is required.")
	private String sku;
	
	@Column(name = "name")
	@NotNull(message="Sku is required.")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "quantity")
	@NotNull(message="Quantity is required.")
    @Min(value=0, message="Quantity is requried not less than 0.")
	private int quantity;
	
	@Column(name = "price")
    @NotNull(message="Quantity is required.")
    @Min(value=0, message="Price is requried not less than 0.")
	private float price;
	
	@Column(name = "status")
    @NotNull(message="Please, select the status of the product.")
	private boolean status = true;
	
	@Column(name = "img")
	private String img;

	public Product() {
	}

	public Product(String sku, String name, String description, int quantity, float price, boolean status) {
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
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + ", status=" + status + "]";
	}
}
