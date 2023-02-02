package com.ducdmd152.springboot.dsnackerstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ducdmd152.springboot.dsnackerstore.product.Product;
import com.ducdmd152.springboot.dsnackerstore.product.ProductService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list")
	public String listProducts(Model model) {
		List<Product> products = productService.getAvailableProducts();
		model.addAttribute("PRODUCTS", products);
		return "raw/shop/list-products";
	}
}
