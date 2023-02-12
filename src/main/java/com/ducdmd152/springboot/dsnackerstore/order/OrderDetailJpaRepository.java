package com.ducdmd152.springboot.dsnackerstore.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ducdmd152.springboot.dsnackerstore.product.Product;

public interface OrderDetailJpaRepository extends JpaRepository<OrderDetail, Integer> {
	public List<OrderDetail> findAllByProduct(Product product);
}
