package com.ducdmd152.springboot.dsnackerstore.product;

import java.util.List;

import javax.validation.Valid;

public interface ProductService {
	public List<Product> getProducts();
	public List<Product> getAvailableProducts();

//	public void saveProduct(Product product);

	public Product getProduct(String sku);
	public void saveProduct(Product product);

//	public void deleteProduct(String sku);
}
