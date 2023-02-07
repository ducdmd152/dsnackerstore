package com.ducdmd152.springboot.dsnackerstore.product;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

public class ProductModel implements Serializable { /// ~ a model use in bussiness logic-tier (not mapping), use to present product in cart,  and product in order prepared
    @NotNull(message="Sku is required.")
	private String sku;
    
    @NotNull(message="Sku is required.")
    private String name;
    
    
    private String description;
    
    @NotNull(message="Quantity is required.")
    @Min(value=0, message="Quantity is requried not less than 0.")
    private int quantity;
    
    @NotNull(message="Quantity is required.")
    @Min(value=0, message="Price is requried not less than 0.")
    private float price;
    
    @NotNull(message="Please, select the status of the product.")
    private boolean status = true;

    public ProductModel() {
    }
    
    
    
    public ProductModel(String sku,
			String name, String description,
			int quantity, float price) {
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
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

    public float getTotal() {
        return this.quantity*this.price;
    }

    
    public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return "[ " + sku + " " + name + " " + description + " " + quantity + " " + price + "]"; //To change body of generated methods, choose Tools | Templates.
    }
}