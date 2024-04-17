package com.kitchen.Tiffin.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kitchen.Tiffin.services.UserService;
import com.kitchen.Tiffin.web.dto.UserRegistrationDTO;



@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	
	
	@Autowired
	private UserService userService;
	
	
	@ModelAttribute("user")
	public UserRegistrationDTO registrationDto() {
		return new UserRegistrationDTO();
	}
	@GetMapping
	public String showRegistrationForm() {
	          return "registrationform";
	}
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user")UserRegistrationDTO registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registrationform?success=true";
	
	}
	
	

}