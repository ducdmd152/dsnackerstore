package com.ducdmd152.springboot.dsnackerstore.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ducdmd152.springboot.dsnackerstore.product.Product;
import com.ducdmd152.springboot.dsnackerstore.product.ProductModel;
import com.ducdmd152.springboot.dsnackerstore.product.ProductService;
import com.ducdmd152.springboot.dsnackerstore.utils.ProductUtil;

@Component
@Scope("prototype")
public class CartModel {
	private Map<String, Integer> items;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductUtil productUtil;
	

	
    public CartModel() {
	}

	public ProductService getProductService() {
		return productService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/// khong co set vi khong up xot hang loat, them tung item
    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String sku, int quantity) {
        if (sku == null) {
            return;
        }

        if (sku.trim().isEmpty()) {
            return;
        }

        if (quantity == 0) {
            return;
        }

        if (this.items == null) {
            this.items = new HashMap<>();
        } // items have not existed

        if (this.items.containsKey(sku)) {
            quantity += this.items.get(sku);
        }

        // update items
        this.items.put(sku, quantity); /// put existed key?
    }

    public void removeItemFromCart(String sku) {
        if (sku == null) {
            return;
        }
        if (sku.trim().isEmpty()) {
            return;
        }

        if (this.items == null) {
            return;
        }

        if (this.items.containsKey(sku)) {
            this.items.remove(sku);
            if (this.items.isEmpty()) { /// chu dong huy doi tuong empty
                this.items = null;
            }
        }
    }

    public void refresh() { // sync the data in web-tier with persistence-tier
        if (this.items == null) {
            return;
        }
        
        for (String sku : this.items.keySet()) {
            
            ProductModel productModel = productUtil.getProductSyncOrderedQuantity(sku);
            
            int availableQuantity = productModel.getQuantity();
            int quantityInCart = this.items.get(sku);          
            
            int updatedQuantityInCart = Integer.min(availableQuantity, quantityInCart);
            
            if (updatedQuantityInCart == 0) { // remove
                this.removeItemFromCart(sku);
            } else { // update
                this.items.put(sku, updatedQuantityInCart);
            }
        }
    }

    public List<ProductModel> getSelectedProductsInCart(String[] selectedItems) {
        if(selectedItems==null) {
            return null;
        }
        if(selectedItems.length==0) {
            return null;
        }
        
        if(this.items==null) {
            return null;
        }
        
        List<ProductModel> result = new ArrayList<>();
        
        for (String sku : selectedItems) {
            if(this.items.containsKey(sku)==false) {
                continue;
            }
            
            Product entity = productService.getProduct(sku);

            String name = entity.getName();
            String description = entity.getDescription();
            float price = entity.getPrice();
            int quantity = this.items.get(sku);

            ProductModel productInCart = new ProductModel(sku, name, description, quantity, price);
            
            result.add(productInCart);
        }
        
        if(result.isEmpty()) {
            result=null;
        }
        
        return result;
    }
    
    public List<ProductModel> getAllProductsInCart() {        
        if(this.items==null) {
            return null;
        }
        
        List<ProductModel> result = new ArrayList<>();
        
        for (String sku : this.items.keySet()) {
        	System.out.println(productService);
            Product entity = productService.getProduct(sku);

            String name = entity.getName();
            String description = entity.getDescription();
            float price = entity.getPrice();
            int quantity = this.items.get(sku);

            ProductModel productInCart = new ProductModel(sku, name, description, quantity, price);
            
            result.add(productInCart);
        }
        
        return result;
    }
}
