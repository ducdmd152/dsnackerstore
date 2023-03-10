package com.ducdmd152.springboot.dsnackerstore.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductJpaRepository
	extends JpaRepository<Product, String> {
	public List<Product> findAllByStatus(boolean status);
}
