package com.ducdmd152.springboot.dsnackerstore.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceJpaRepositoryImpl 
		implements OrderService {
	@Autowired
	private OrderJpaRepository orderJpaRepository;
	
	@Override
	public void saveOrder(Order order) {
		orderJpaRepository.save(order);
	}

	@Override
	public List<Order> getOrders() {
		return orderJpaRepository.findAllByOrderByDateBuyDesc();
	}

	@Override
	public List<Order> getOrdersWithStatus(String status) {
		return orderJpaRepository.findAllByStatusOrderByDateBuyDesc(status);
	}

	@Override
	public Order getOrder(int id) {
		Order order = null;
		Optional<Order> result = orderJpaRepository.findById(id);
		
		if(result.isPresent()) {
			order = result.get();
		}
		
		return order; 
	}

	@Override
	public List<Order> getOrdersByUser(String username) {
		return orderJpaRepository.findAllByUsernameOrderByDateBuyDesc(username);
	}

	@Override
	public List<Order> getOrdersByUserWithStatus(String username, String status) {
		return orderJpaRepository.findAllByUsernameAndStatusOrderByDateBuyDesc(username, status);
	}
}
