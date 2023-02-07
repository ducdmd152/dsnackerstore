package com.ducdmd152.springboot.dsnackerstore.controller;

import java.util.List;

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

import com.ducdmd152.springboot.dsnackerstore.order.Order;
import com.ducdmd152.springboot.dsnackerstore.order.OrderService;
import com.ducdmd152.springboot.dsnackerstore.product.Product;
import com.ducdmd152.springboot.dsnackerstore.product.ProductModel;
import com.ducdmd152.springboot.dsnackerstore.product.ProductService;
import com.ducdmd152.springboot.dsnackerstore.utils.ProductUtil;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductUtil productUtil;
	
	@InitBinder
	public void trimValueOfParameters(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	// orders management
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

	// products management
	@GetMapping("/showProducts")
	public String showProducts(
			Model model
			) {
//		List<Product> products = productService.getProducts();
		List<ProductModel> products = productUtil.getProductsSyncOrderedQuantity();
		
		model.addAttribute("PRODUCTS", products);
		
		return "raw/employee/showProducts";
	}
	
	@GetMapping("/showCreateProduct")
	public String showCreateProduct(
			Model model
			) {
		Product product = new Product();
//		ProductModel product = productUtil.getProductSyncOrderedQuantity(id);
		
		model.addAttribute("PRODUCT", product);
		
		return "raw/employee/showCreateProduct";
	}
	
	@PostMapping("/createProduct")
	public String createProduct(
			Model model,
			@Valid @ModelAttribute("PRODUCT") Product product,
			BindingResult bindingResult
			) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("PRODUCT", product);
			return "raw/employee/showCreateProduct";
		}
		
		if(productService.checkExist(product.getSku())) {
			model.addAttribute("PRODUCT", product);
			model.addAttribute("DUPLICATED_SKU_ERROR", true);
			return "raw/employee/showCreateProduct";
		}
		
		productService.saveProduct(product);
		model.addAttribute("PRODUCT", product);
		
		return "redirect:/employee/showEditProduct?id=" + product.getSku();
	}
	
	@GetMapping("/showEditProduct")
	public String showEditProduct(
			Model model,
			@RequestParam String id
			) {
//		Product product = productService.getProduct(id);
		ProductModel product = productUtil.getProductSyncOrderedQuantity(id);
		
		model.addAttribute("PRODUCT", product);
		
		return "raw/employee/showEditProduct";
	}
	
	@PostMapping("/editProduct")
	public String editProduct(
			Model model,
			@Valid @ModelAttribute("PRODUCT") ProductModel productModel,
			BindingResult bindingResult
			) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("PRODUCT", productModel);
			return "raw/employee/showEditProduct";
		}
		
		Product product = productUtil.syncEditedProductModelToProduct(productModel);
		productService.saveProduct(product);
		
		return "redirect:/employee/showEditProduct?id=" + product.getSku();
	}
}
