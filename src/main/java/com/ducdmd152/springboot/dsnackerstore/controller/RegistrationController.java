package com.ducdmd152.springboot.dsnackerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ducdmd152.springboot.dsnackerstore.users.User;
import com.ducdmd152.springboot.dsnackerstore.users.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/showLogin")
	public String showLogin(Model model) {
		return "raw/registration/login";
	}
	
	@GetMapping("/showRegister")
	public String showRegister(Model model) {
		User user = new User();
		model.addAttribute("USER", user);
		return "raw/registration/register";
	}
	
	@PostMapping("/performRegister")
	public String performRegister(Model model, 
			@ModelAttribute("USER") User user) {
		System.out.println("User: " + user);
		System.out.println(user.getUserDetail());
		System.out.println(user.getAuthorities());
		userService.saveUser(user);
		
		model.addAttribute("REGISTERED", true);
		
		return "redirect:/registration/showLogin";
	}
}
