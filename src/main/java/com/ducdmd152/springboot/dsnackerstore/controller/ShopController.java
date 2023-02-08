package com.ducdmd152.springboot.dsnackerstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ducdmd152.springboot.dsnackerstore.cart.CartModel;
import com.ducdmd152.springboot.dsnackerstore.product.ProductModel;
import com.ducdmd152.springboot.dsnackerstore.product.ProductService;
import com.ducdmd152.springboot.dsnackerstore.utils.ProductUtil;

@Controller
@RequestMapping("/shop")
public class ShopController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductUtil productUtil;
	
	@RequestMapping("/list")
	public String listProducts(Model model, HttpServletRequest request) {
		// 1. Get session & cart (if exist)
		HttpSession session = request.getSession(false);
		CartModel cart = null;
		if(session!=null) {
			cart = (CartModel) session.getAttribute("CART");
		}
		
		// 2. Get compatible info of products for the user
		List<ProductModel> products = productUtil.getProductsSyncOrderedQuantitySyncCart(cart);
		
		// 3. turn to view
		model.addAttribute("PRODUCTS", products);
//		return "raw/shop/list-products";
		return "official/shop/shop-list";
	}
}
