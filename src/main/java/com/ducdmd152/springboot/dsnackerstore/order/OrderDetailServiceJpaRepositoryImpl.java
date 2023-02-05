package com.ducdmd152.springboot.dsnackerstore.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceJpaRepositoryImpl implements OrderDetailService {
	@Autowired
	private OrderDetailJpaRepository orderDetailJpaRepository;
	@Override
	public int getOrderedQuantityOf(String sku) {
		int result = 0;
		
		List<OrderDetail> orderDetails = orderDetailJpaRepository.findAllBySku(sku);
		
		if(orderDetails!=null) {
			for(OrderDetail orderDetail : orderDetails) {
				result += orderDetail.getQuantity();
			}
		}
		
		return result;
	}
	
}
