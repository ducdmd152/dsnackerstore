package com.ducdmd152.springboot.dsnackerstore.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// @Transactional // remove since JpaRepository provide already
public class ProductServiceJpaRepositoryImpl 
	implements ProductService {
	@Autowired
	private ProductJpaRepository productJpaRepository;

	@Override
	public List<Product> getProducts() {
		return productJpaRepository.findAll();
	}
	
	@Override
	public List<Product> getAvailableProducts() {
		return productJpaRepository.findAllByStatus(true);
	}
	
	@Override
	public List<Product> getProductsByStatus(boolean status) {
		return productJpaRepository.findAllByStatus(status);
	}	

	@Override
	public Product getProduct(String sku) {
		Optional<Product> result = productJpaRepository.findById(sku);
		Product product = null;
		
		if(result.isPresent()) {
			product =  result.get();
		}
		else {
			// we didn't find the employee
//			throw new RuntimeException("Did not find employee id - " + theId);
			// do nothing to return null
		}
		return product;
	}

	@Override
	public void saveProduct(Product product) {
		productJpaRepository.save(product);
	}

	@Override
	public boolean checkExist(String sku) {
		Optional<Product> result = productJpaRepository.findById(sku);
		return result.isPresent();
	}


}
