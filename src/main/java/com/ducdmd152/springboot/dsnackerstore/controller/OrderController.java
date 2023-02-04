package com.ducdmd152.springboot.dsnackerstore.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
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
import com.ducdmd152.springboot.dsnackerstore.order.OrderService;
import com.ducdmd152.springboot.dsnackerstore.utils.OrderUtil;
import com.ducdmd152.springboot.dsnackerstore.utils.ScopeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderUtil orderUtil;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ScopeUtil scopeUtil;
	
	@PostMapping("/makeOrder")
	public String makeOrder(
			Model model,
			@Valid @ModelAttribute("ORDER") Order orderFromView,
			BindingResult bindingOrderResult,
			@RequestParam int preparedOrderKey,
			HttpSession session
			) {
		
		HashMap<Integer, Order> preparedOrders = (HashMap<Integer, Order>) session.getAttribute("PREPARED_ORDERS");
		
		if(preparedOrders == null) {
			model.addAttribute("ERROR_MASSAGE", "The order is NOT in prepered orders"
					+ ", may be this order is done successfully or canceled.");
			return "raw/order/orderFail";
		}
		
		Order order = preparedOrders.get(preparedOrderKey);
		
		if(order == null) {
			model.addAttribute("ERROR_MASSAGE", "The order is NOT in prepered orders"
					+ ", may be this order is done successfully or canceled.");
			return "raw/order/orderFail";
		}
		
		// update order from user's input
		order.setName(orderFromView.getName());
		order.setPhone(orderFromView.getPhone());
		order.setAddress(orderFromView.getAddress());
		if(orderUtil.checkValidOrder(order)) {
			model.addAttribute("ERROR_MASSAGE", "The order is NOT proper"
					+ ", may be someone else bought before you that make haven't enough quantiy for your order.\n"
					+ "Please try again.");
			// record time for order
			order.setDateBuy(new Timestamp(System.currentTimeMillis()));
			
			// save into database
			orderService.saveOrder(order);
			
			// remove out the preparedOrders in session
			preparedOrders.remove(preparedOrderKey);
			
			// send to view
			model.addAttribute("ORDER", order);
			return "raw/order/orderSuccess";
		}
		else {
			return "raw/order/orderFail";
		}
	}
	
//	@PostMapping("/makeOrder")
//	public String makeOrder(
//			Model model,
//			@Valid @ModelAttribute("ORDER") Order order,
//			BindingResult bindingOrderResult,
//			@RequestParam String orderDetailsJSON
//			) {
//		List<OrderDetail> orderDetails = orderUtil.getOrderDetailsFromJSONString(orderDetailsJSON) ;
//		
//		for(OrderDetail orderDetail : orderDetails) {
//			order.addOrderDetail(orderDetail);
//		}
//		
//		if(orderUtil.checkValidOrder(order)) {
//			order.setDateBuy(new Timestamp(System.currentTimeMillis()));
//			orderService.saveOrder(order);
//			model.addAttribute("ORDER", order);
//			return "raw/order/orderSuccess";
//		}
//		else {
//			return "raw/order/orderFail";
//		}
//	}
	
	
}
