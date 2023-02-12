package com.ducdmd152.springboot.dsnackerstore.product;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

public class ProductModel
	extends Product
	implements Serializable { /// ~ a model use in bussiness logic-tier (not mapping), use to present product in cart,  and product in order prepared
    public ProductModel() {
    }
    
    public ProductModel(String sku, String name, String description, int quantity, float price) {
		super(sku, name, description, quantity, price, true);
	}
    public float getTotal() {
        return this.getQuantity()*this.getPrice();
    }

}