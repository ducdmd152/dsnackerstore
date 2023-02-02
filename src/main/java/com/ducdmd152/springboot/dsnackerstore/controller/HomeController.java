package com.ducdmd152.springboot.dsnackerstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/fail")
	public String fail() {
		return "raw/exceptions/fault";
	}
}
