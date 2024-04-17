package com.kitchen.Tiffin.controller;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
    @GetMapping("/")
	public String showhome() {
		return "home";
	}

    @GetMapping("/login")
	public String login() {
		return "home";
	}
	
	@GetMapping("/userpage")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "home";
	}
	
	@GetMapping("/adminpage")
	public String adminPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "Dashboard";
	}
}
