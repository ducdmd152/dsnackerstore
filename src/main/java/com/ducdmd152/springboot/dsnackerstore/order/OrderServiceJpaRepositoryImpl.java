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
		return orderJpaRepository.findAll();
	}

	@Override
	public List<Order> getOrdersWithStatus(String status) {
		return orderJpaRepository.findAllByStatus(status);
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

}
