package com.ducdmd152.springboot.dsnackerstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	// add an initbinder ... to pre-process requests
	// goal is to convert trim input strings
	// resolve the issue for our validation

	@InitBinder
	public void trimValueOfParameters(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showLogin")
	public String showLogin(Model model) {
		return "raw/registration/login";
	}

	@GetMapping("/showRegister")
	public String showRegister(Model model) {
		User user = new User();
		UserDetail userDetail = new UserDetail();
		model.addAttribute("USER", user);
		model.addAttribute("USER_DETAIL", userDetail);
//		return "raw/registration/register";
		return "official/registration/register";
	}

	@PostMapping("/performRegister")
	public String performRegister(
			Model model,
			@Valid @ModelAttribute("USER") User user,
			BindingResult bindingUserResult,
			@Valid @ModelAttribute("USER_DETAIL") UserDetail userDetail,
			BindingResult bindingUserDetailResult) {
//		System.out.println(bindingUserResult.hasErrors());
//		System.out.println(bindingUserDetailResult.hasErrors());
		if(bindingUserResult.hasErrors() || bindingUserDetailResult.hasErrors()) {			
//			return "raw/registration/register";
			return "official/registration/register";
		}
		else if (userService.checkExist(user.getUsername())){
			model.addAttribute("DUPLICATED_USERNAME_ERROR", true);
//			return "raw/registration/register";
			return "official/registration/register";
		}
		else {
			user.setUserDetail(userDetail);
			userDetail.setUser(user);
			Authority authority = new Authority("ROLE_CUSTOMER");
			user.addAuthority(authority);

			String plainPassword = user.getPassword();
			String encryptedPassword = passwordEncoder.encode(plainPassword);
			user.setPassword(encryptedPassword);

			userService.saveUser(user);

			return "redirect:/registration/showLogin?registered";
		}
	}
}
