package com.ducdmd152.springboot.dsnackerstore.order;

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

}
