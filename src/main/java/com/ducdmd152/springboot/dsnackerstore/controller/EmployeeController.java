package com.ducdmd152.springboot.dsnackerstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducdmd152.springboot.dsnackerstore.order.Order;
import com.ducdmd152.springboot.dsnackerstore.order.OrderDetail;
import com.ducdmd152.springboot.dsnackerstore.order.OrderDetailService;
import com.ducdmd152.springboot.dsnackerstore.order.OrderService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/showOrders")
	public String showOrders(
			Model model,
			@RequestParam(required = false) String status
			) {
		List<Order> orders = null;
		if(status==null) {
			orders = orderService.getOrders(); // order by date
		}
		else {
			orders = orderService.getOrdersWithStatus(status);
		}
		
		model.addAttribute("STATUS", status);
		model.addAttribute("ORDERS", orders);
		
		return "raw/employee/showOrders";
	}
	
	@GetMapping("/showOrderDetails")
	public String showOrderDetails(
			Model model,
			@RequestParam int orderId
			) {
		Order order = orderService.getOrder(orderId);
		
		model.addAttribute("ORDER", order);
		return "raw/employee/showOrderDetails";
	}
	
	@PostMapping("/setOrderStatus")
	public String setOrderStatus(
			Model model,
			@RequestParam int id,
			@RequestParam String status
			) {
		Order order = orderService.getOrder(id);
		order.setStatus(status);
		orderService.saveOrder(order);
		
		return "redirect:/employee/showOrderDetails?orderId=" + id;
	}
}
