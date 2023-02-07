package com.ducdmd152.springboot.dsnackerstore.order;

import java.util.List;

import javax.validation.Valid;

public interface OrderService {

	public void saveOrder(Order order);

	public List<Order> getOrders();

	public List<Order> getOrdersWithStatus(String status);

	public Order getOrder(int id);
	
}
