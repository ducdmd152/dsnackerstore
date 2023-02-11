package com.ducdmd152.springboot.dsnackerstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducdmd152.springboot.dsnackerstore.cart.CartModel;
import com.ducdmd152.springboot.dsnackerstore.order.Order;
import com.ducdmd152.springboot.dsnackerstore.product.ProductModel;
import com.ducdmd152.springboot.dsnackerstore.utils.OrderUtil;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private OrderUtil orderUtil;

	@PostMapping("/addToCart")
	public String addToCart(HttpServletRequest request, Model model, @RequestParam String productId) {
		// 1. Cust goes to cart placement
		HttpSession session = request.getSession(true); /// default = true
		// 2. Cust takes his/her cart
		CartModel cart = (CartModel) session.getAttribute("CART");
		if (cart == null) {
			cart = applicationContext.getBean(CartModel.class);
		}
		// 3. Cust chooses a specific item
		// productId

		// 4. Cust drops item down
		cart.addItemToCart(productId, 1);
		session.setAttribute("CART", cart);
		
		return "redirect:/shop/list";
	}

	@GetMapping("/viewCart")
	public String addToCart(HttpServletRequest request, Model model) {
		// 1. Cust goes to his/her cart placement
		HttpSession session = request.getSession(false);
		if (session != null) {
			// 2. Cust takes his/her cart
			CartModel cart = (CartModel) session.getAttribute("CART");
			if (cart != null) {
				// 3. Cust takes items
				Map<String, Integer> items = cart.getItems();

				if (items != null) {
					// 4. System setup data to show the items in cart
					/// 4.1 get informations
//					cart.refresh(); /// to fix problems like changed of quantity in database 
					List<ProductModel> productsInCart = cart.getAllProductsInCart(); // already call refresh()
					// 4.2 set data to send for view
					model.addAttribute("PRODUCTS_IN_CART", productsInCart);
				}
			}
		}
//		return "raw/cart/show-cart";
		return "official/cart/show-cart";
	}

	@GetMapping("/removeOutCart")
	public String removeOutCart(HttpServletRequest request, Model model, @RequestParam String productId,
			@RequestParam(required = false) String requestFromUrl) {
		// 1. Cust goes to his/her cart placement
		HttpSession session = request.getSession(false); // false for check scope timeout because user's problem
		if (session != null) {
			// 2. Cust takes his/her cart
			CartModel cart = (CartModel) session.getAttribute("CART");
			if (cart != null) {
				cart.removeItemFromCart(productId);
			} // end cart has existed
		} // end session has existed

		if(requestFromUrl == null) {
			requestFromUrl = "/cart/viewCart";
		}
//		System.out.println(requestFromUrl);
		return "redirect:" + requestFromUrl;
	}

	@PostMapping("/performSelectedItems")
	public String performSelectedItems(Model model, @RequestParam(required = false) String[] checkedItems, @RequestParam String action) {
		if (checkedItems == null) {
			return "redirect:/cart/viewCart";
		} else if (action.equals("checkout")) {
			return "forward:/cart/checkout";
		} else if (action.equals("delete")) {
			return "forward:/cart/deleteSelectedItems";
		}

		return null;
	}

	@PostMapping("/checkout")
	public String performSelectedItems(HttpSession session, Model model,
			@RequestParam String[] checkedItems) {
		// 1. Get session & cart (if exist)
		CartModel cart = null;
		if (session != null) {
			cart = (CartModel) session.getAttribute("CART");
			if(cart != null) {
//				Order order = orderUtil.genOrderBy(cart, checkedItems);
//				String orderDetailsJSON = orderUtil.orderDetailsToJSON(order.getOrderDetails());
//				model.addAttribute("ORDER", order);
//				model.addAttribute("ORDER_DETAILS_JSON", orderDetailsJSON);
				
				Order order = orderUtil.genOrderBy(cart, checkedItems);
				
				// create a new prepared order, and save in session
				HashMap<Integer, Order> preparedOrders = (HashMap<Integer, Order>) session.getAttribute("PREPARED_ORDERS");
				if(preparedOrders==null) {
					preparedOrders= new HashMap<>();
					session.setAttribute("PREPARED_ORDERS", preparedOrders);
				}
				preparedOrders.put(order.hashCode(), order);
				
				// send to view
				model.addAttribute("ORDER", order);
				model.addAttribute("PREPARED_ORDER_KEY", order.hashCode());
				
//				return "raw/cart/checkout";
				return "official/cart/checkout";
			}
		}

		return null;
	}

	@PostMapping("/deleteSelectedItems")
	public String deleteSelectedItems(
			Model model,
			HttpSession session,
			@RequestParam String[] checkedItems
			) {
		CartModel cart = (CartModel) session.getAttribute("CART");
		if(cart != null) {
			for(String sku : checkedItems) {
				cart.removeItemFromCart(sku);
			}
		}
		
		return "redirect:/cart/viewCart";
	}
}
