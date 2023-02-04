package com.ducdmd152.springboot.dsnackerstore.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducdmd152.springboot.dsnackerstore.cart.CartModel;
import com.ducdmd152.springboot.dsnackerstore.product.ProductModel;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private ApplicationContext applicationContext;
	
	@GetMapping("/addToCart")
	public String addToCart(
			HttpServletRequest request,
			Model model,
			@RequestParam String productId) {
		//1. Cust goes to cart placement
        HttpSession session = request.getSession(true); /// default = true
        //2. Cust takes his/her cart
        CartModel cart = (CartModel) session.getAttribute("CART");
        if (cart == null) {
            cart = applicationContext.getBean(CartModel.class);
            System.out.println("Product Service: " + cart.getProductService());
        }
        //3. Cust chooses a specific item
        // productId
        
        //4. Cust drops item down
        cart.addItemToCart(productId, 1);
        session.setAttribute("CART", cart);
        
		return "redirect:/shop/list";
	}
	
	@GetMapping("/viewCart")
	public String addToCart(
			HttpServletRequest request,
			Model model) {
		//1. Cust goes to his/her cart placement
		HttpSession session = request.getSession(false);
        if (session != null) {
            //2. Cust takes his/her cart
            CartModel cart = (CartModel) session.getAttribute("CART");
            if(cart!=null) {                    
                //3. Cust takes items
                Map<String, Integer> items = cart.getItems();
                
                if (items != null) {
                    //4. System setup data to show the items in cart
                    ///4.1 get informations
                    cart.refresh(); /// to fix problems like changed of quantity in database
                    List<ProductModel> productsInCart = cart.getAllProductsInCart();
                    //4.2 set data to send for view
                    model.addAttribute("PRODUCTS_IN_CART", productsInCart);                        
                }
            }
        }
		return "raw/cart/show-cart";
	}
	
	@GetMapping("/removeOutCart")
	public String removeOutCart(
			HttpServletRequest request,
			Model model,
			@RequestParam String productId) {
		//1. Cust goes to his/her cart placement
        HttpSession session = request.getSession(false); // false for check scope timeout because user's problem
        if (session != null) {                
            //2. Cust takes his/her cart
            CartModel cart = (CartModel) session.getAttribute("CART");
            if(cart != null) {
            	cart.removeItemFromCart(productId);
            } // end cart has existed
        } // end session has existed
        
		return "redirect:/cart/viewCart";
	}
}
