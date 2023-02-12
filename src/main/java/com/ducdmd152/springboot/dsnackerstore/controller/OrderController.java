package com.ducdmd152.springboot.dsnackerstore.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducdmd152.springboot.dsnackerstore.cart.CartModel;
import com.ducdmd152.springboot.dsnackerstore.order.Order;
import com.ducdmd152.springboot.dsnackerstore.order.OrderDetail;
import com.ducdmd152.springboot.dsnackerstore.order.OrderService;
import com.ducdmd152.springboot.dsnackerstore.utils.OrderUtil;
import com.ducdmd152.springboot.dsnackerstore.utils.SessionUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderUtil orderUtil;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SessionUtil scopeUtil;
	
	@InitBinder
	public void trimValueOfParameters(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@PostMapping("/makeOrder")
	public String makeOrder(
			Model model,
			@Valid @ModelAttribute("ORDER") Order orderFromView,
			BindingResult bindingOrderResult,
			@RequestParam int preparedOrderKey,
			HttpSession session,
			Principal principal
			) {
		// case user's input errors
		if(bindingOrderResult.hasErrors()) {
			// send to view
			model.addAttribute("ORDER", orderFromView);
			model.addAttribute("PREPARED_ORDER_KEY", preparedOrderKey);
//			return "raw/cart/checkout";
			return "official/cart/checkout";
		}
		
		// case session errors
		HashMap<Integer, Order> preparedOrders = (HashMap<Integer, Order>) session.getAttribute("PREPARED_ORDERS");
		
		
		if(preparedOrders == null) {
			model.addAttribute("ERROR_MASSAGE", "The order is NOT in prepered orders"
					+ ", may be this order is done successfully or canceled.");
//			return "raw/order/orderFail";
			return "official/order/orderFail";
		}
		
		Order order = preparedOrders.get(preparedOrderKey);
		if(order == null) {
			model.addAttribute("ERROR_MASSAGE", "The order is NOT in prepered orders"
					+ ", may be this order is done successfully or canceled.");
//			return "raw/order/orderFail";
			return "official/order/orderFail";
		}
		
		// update order from user's input
		order.setName(orderFromView.getName());
		order.setPhone(orderFromView.getPhone());
		order.setAddress(orderFromView.getAddress());
		
		// case system's errors
		if(orderUtil.checkValidOrder(order)) {
			model.addAttribute("ERROR_MASSAGE", "The order is NOT proper"
					+ ", may be someone else bought before you that make haven't enough quantiy for your order.\n"
					+ "Please try again.");
			// update the order's info
			// record time for order
			order.setDateBuy(new Timestamp(System.currentTimeMillis()));
			// set status
			order.setStatus("pending");
			// set username
			if(principal != null) {
				order.setUsername(principal.getName());
			}
			
			
			// save into database
			orderService.saveOrder(order);
			
			// remove bought products out the cart
			CartModel cart = (CartModel) session.getAttribute("CART");
			if(cart!=null) {
				for(OrderDetail orderDetail : order.getOrderDetails()) {
					cart.removeItemFromCart(orderDetail.getProduct().getSku());
				}
			}
			
			// remove out the preparedOrders in session
			preparedOrders.remove(preparedOrderKey);
			
			// send to view
//			model.addAttribute("ORDER", order);
			return "redirect:/order/showOrder?id=" + order.getId();
		}
		else {
//			return "raw/order/orderFail";
			return "official/order/orderFail";
		}
	}
	
	@GetMapping("/showOrder")
	public String showOrder(Model model, @RequestParam int id) {
		Order order = orderService.getOrder(id);
		
		model.addAttribute("ORDER", order);
//		return "raw/order/orderSuccess";
		return "official/order/orderSuccess";
	}
}
