package com.ducdmd152.springboot.dsnackerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ducdmd152.springboot.dsnackerstore.users.Authority;
import com.ducdmd152.springboot.dsnackerstore.users.User;
import com.ducdmd152.springboot.dsnackerstore.users.UserDetail;
import com.ducdmd152.springboot.dsnackerstore.users.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
			@ModelAttribute("USER") User user
			) {
		user.getUserDetail().setUser(user);
		Authority authority = new Authority("ROLE_CUSTOMER");
		user.addAuthority(authority);
		
		String plainPassword = user.getPassword();
		String encryptedPassword = passwordEncoder.encode(plainPassword);
		user.setPassword(encryptedPassword);
		
		userService.saveUser(user);
		
		return "redirect:/registration/showLogin?registered";
	}
}
