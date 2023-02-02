package com.ducdmd152.springboot.dsnackerstore.product;

import java.util.List;

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
}
