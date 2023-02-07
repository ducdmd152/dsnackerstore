package com.ducdmd152.springboot.dsnackerstore.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

	List<Order> findAllByStatus(String status);

}
