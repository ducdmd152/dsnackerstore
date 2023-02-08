package com.ducdmd152.springboot.dsnackerstore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String showHome(Model model,
			HttpServletRequest request
			) {
		if(request.isUserInRole("ROLE_CUSTOMER")) {
			return "customer";
		}
		
		return "guest";
	}
	
	@GetMapping("/fail")
	public String fail() {
		return "raw/exceptions/fault";
	}
}
