package com.ducdmd152.springboot.dsnackerstore.product;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductJpaRepository
	extends JpaRepository<Product, String> {

}
