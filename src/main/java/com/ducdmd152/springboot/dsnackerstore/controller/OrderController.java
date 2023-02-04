package com.ducdmd152.springboot.dsnackerstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducdmd152.springboot.dsnackerstore.order.Order;
import com.ducdmd152.springboot.dsnackerstore.order.OrderDetail;
import com.ducdmd152.springboot.dsnackerstore.utils.OrderUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderUtil orderUtil;
	
	@PostMapping("/makeOrder")
	public String makeOrder(
			Model model,
			@Valid @ModelAttribute("ORDER") Order order,
			BindingResult bindingOrderResult,
			@RequestParam String orderDetailsJSON
			) {
		List<OrderDetail> orderDetails = orderUtil.getOrderDetailsFromJSONString(orderDetailsJSON) ;
		
		for(OrderDetail orderDetail : orderDetails) {
			order.addOrderDetail(orderDetail);
		}
		
		if(orderUtil.checkValidOrder(order)) {
//			orderService.saveOrder(order)
			model.addAttribute("ORDER", order);
			return "raw/order/orderSuccess";
		}
		else {
			return "raw/order/orderFail";
		}
	}
	
	
}
