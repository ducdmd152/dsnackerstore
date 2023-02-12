package com.ducdmd152.springboot.dsnackerstore.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ducdmd152.springboot.dsnackerstore.product.Product;
import com.ducdmd152.springboot.dsnackerstore.product.ProductService;

@Service
public class OrderDetailServiceJpaRepositoryImpl implements OrderDetailService {
	@Autowired
	private OrderDetailJpaRepository orderDetailJpaRepository;
	@Autowired
	private ProductService productService;
	
	@Override
	public int getOrderedQuantityOf(String sku) {
		int result = 0;
		
		Product product = productService.getProduct(sku);
		List<OrderDetail> orderDetails = orderDetailJpaRepository.findAllByProduct(product);
		
		if(orderDetails!=null) {
			for(OrderDetail orderDetail : orderDetails) {
				String status = orderDetail.getOrder().getStatus();
				if(status.equals("pending") || status.equals("confirmed")) {
					result += orderDetail.getQuantity();
				}
			}
		}
		
		return result;
	}
	
}
