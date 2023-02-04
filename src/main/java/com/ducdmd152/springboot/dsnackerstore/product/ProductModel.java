package com.ducdmd152.springboot.dsnackerstore.product;

import java.io.Serializable;

public class ProductModel implements Serializable { /// ~ a model use in bussiness logic-tier (not mapping), use to present product in cart,  and product in order prepared
    private String sku;
    private String name;
    private String description;
    private int quantity;
    private float price;

    public ProductModel() {
    }

    public ProductModel(String sku, String name, String description, int quantity, float price) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return this.quantity*this.price;
    }

    @Override
    public String toString() {
        return "[ " + sku + " " + name + " " + description + " " + quantity + " " + price + "]"; //To change body of generated methods, choose Tools | Templates.
    }
}