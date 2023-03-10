package com.ducdmd152.springboot.dsnackerstore.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

	List<Order> findAllByStatus(String status);

	List<Order> findAllByUsername(String username);

	List<Order> findAllByUsernameAndStatus(String username, String status);

	List<Order> findAllByStatusOrderByDateBuyDesc(String status);

	List<Order> findAllByOrderByDateBuyDesc();

	List<Order> findAllByUsernameOrderByDateBuyDesc(String username);

	List<Order> findAllByUsernameAndStatusOrderByDateBuyDesc(String username, String status);

}
