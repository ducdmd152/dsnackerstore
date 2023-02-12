package com.ducdmd152.springboot.dsnackerstore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducdmd152.springboot.dsnackerstore.order.Order;
import com.ducdmd152.springboot.dsnackerstore.order.OrderService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/showOrders")
	public String showOrders(
			Model model, 
			Principal principal,
			@RequestParam(required = false) String status) {
		String username = principal.getName();
		List<Order> orders = null;
		if(status==null) {
			orders = orderService.getOrdersByUser(username); // order by date
		}
		else {
			orders = orderService.getOrdersByUserWithStatus(username, status);
		}
		
		model.addAttribute("STATUS", status);
		model.addAttribute("ORDERS", orders);
//		return "raw/customer/showOrders";
		return "official/customer/showOrders";
	}
}
