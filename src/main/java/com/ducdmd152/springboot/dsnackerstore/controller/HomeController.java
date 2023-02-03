package com.ducdmd152.springboot.dsnackerstore.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String showHome(Model model,
			Principal principal
			) {
//		model.addAttribute("username", principal.getName());
		return "home";
	}
	
	@GetMapping("/fail")
	public String fail() {
		return "raw/exceptions/fault";
	}
}
