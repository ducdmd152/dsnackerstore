package com.ducdmd152.springboot.dsnackerstore.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ducdmd152.springboot.dsnackerstore.cart.CartModel;
import com.ducdmd152.springboot.dsnackerstore.order.OrderDetailService;
import com.ducdmd152.springboot.dsnackerstore.product.Product;
import com.ducdmd152.springboot.dsnackerstore.product.ProductModel;
import com.ducdmd152.springboot.dsnackerstore.product.ProductService;

@Component
public class ProductUtil {
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderDetailService orderDetailService;
	
	public ProductModel productToProductModel(Product product) {
		ProductModel productModel = new ProductModel();
		productModel.setSku(product.getSku());
		productModel.setName(product.getName());
		productModel.setDescription(product.getDescription());
		productModel.setPrice(product.getPrice());
		productModel.setQuantity(product.getQuantity());
		productModel.setStatus(product.isStatus());
		productModel.setImg(product.getImg());
		return productModel;
	}
	
	public Product productModelToProduct(ProductModel productModel) {
		Product product = new Product();
		product.setSku(productModel.getSku());
		product.setName(productModel.getName());
		product.setDescription(productModel.getDescription());
		product.setPrice(productModel.getPrice());
		product.setQuantity(productModel.getQuantity());
		product.setStatus(productModel.isStatus());
		product.setImg(productModel.getImg());
		return product;
	}
	
	public ProductModel getProductSyncOrderedQuantity(String sku) {
		Product product = productService.getProduct(sku);
		ProductModel productModel = productToProductModel(product);
		
		// 2. Sync Sync data for real quantity with ordered order
		int orderedQuantity = orderDetailService.getOrderedQuantityOf(productModel.getSku());            
		int availableQuantity =  productModel.getQuantity() - orderedQuantity;
		
		productModel.setQuantity(availableQuantity);
		
		return productModel;
	}
	
	public List<ProductModel> getAvailableProductsSyncOrderedQuantity() {
        List<Product> products = productService.getAvailableProducts();
        List<ProductModel> result = null;
        if(products != null) {
        	// 1. Convert entities to models
        	result = new ArrayList<>();
        	for(Product product : products) {
        		ProductModel productModel = productToProductModel(product);
        		
        		result.add(productModel);
        	}
        	
        	// 2. Sync data for real quantity with ordered order
          for(ProductModel product : result) {
	    		int orderedQuantity = orderDetailService.getOrderedQuantityOf(product.getSku());            
	    		int availableQuantity =  product.getQuantity() - orderedQuantity;
	    		
	    		product.setQuantity(availableQuantity);
          }
        }
        
		return result;
	}
	
	public List<ProductModel> getProductsSyncOrderedQuantity() {
        List<Product> products = productService.getProducts();
        List<ProductModel> result = null;
        if(products != null) {
        	// 1. Convert entities to models
        	result = new ArrayList<>();
        	for(Product product : products) {
        		ProductModel productModel = productToProductModel(product);
        		
        		result.add(productModel);
        	}
        	
        	// 2. Sync data for real quantity with ordered order
          for(ProductModel product : result) {
	    		int orderedQuantity = orderDetailService.getOrderedQuantityOf(product.getSku());            
	    		int availableQuantity =  product.getQuantity() - orderedQuantity;
	    		
	    		product.setQuantity(availableQuantity);
          }
        }
        
		return result;
	}

	public List<ProductModel> getProductsSyncOrderedQuantitySyncCart(CartModel cart) {
        List<ProductModel> result = this.getAvailableProductsSyncOrderedQuantity();
        if(cart!=null) {
//        	cart.refresh(); // re-fix for proper with real quantity in DB
        	Map<String, Integer> items = cart.getItems();
            if (items != null) {
                for (ProductModel product : result) {
                    String sku = product.getSku();
                    if (items.containsKey(sku)) {
                    	int availableQuantity = product.getQuantity();
                        int quantityInCart = items.get(sku);

                        int restQuantiy = availableQuantity - quantityInCart;
                        
                        if(restQuantiy < 0) {
                        	restQuantiy = 0;
                        }

                        product.setQuantity(restQuantiy);
                    }
                }
            }
        }
		return result;
	}
	public Product syncEditedProductModelToProduct(ProductModel productModel) {
		
		int orderedQuantity = orderDetailService.getOrderedQuantityOf(productModel.getSku());            
		int availableQuantity =  productModel.getQuantity() + orderedQuantity;
		
		Product result = new Product();
		result = productModelToProduct(productModel);
		result.setQuantity(availableQuantity);
		
		return result;
	}
}
