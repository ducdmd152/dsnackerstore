package com.ducdmd152.springboot.dsnackerstore.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailJpaRepository extends JpaRepository<OrderDetail, Integer> {
	public List<OrderDetail> findAllBySku(String sku);
}
