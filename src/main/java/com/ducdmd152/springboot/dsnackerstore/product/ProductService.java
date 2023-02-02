package com.ducdmd152.springboot.dsnackerstore.product;

import java.util.List;

public interface ProductService {
	public List<Product> getProducts();
	public List<Product> getAvailableProducts();

//	public void saveProduct(Product product);

//	public Product getProduct(String sku);

//	public void deleteProduct(String sku);
}
