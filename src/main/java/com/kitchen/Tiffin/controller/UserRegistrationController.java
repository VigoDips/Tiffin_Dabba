package com.kitchen.Tiffin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kitchen.Tiffin.model.CartItem;
import com.kitchen.Tiffin.services.UserService;
import com.kitchen.Tiffin.web.dto.UserRegistrationDTO;

@Controller
public class UserRegistrationController {

	
	@Autowired
	UserDetailsService userDetailsService;
	
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserRegistrationDTO registrationDto) {
        return "registrationform";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDto, Model model) {
        try {
            userService.save(registrationDto);
            return "redirect:/registration?success=true";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during registration. Please try again.");
            return "registrationform";
        }
    }
    @GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("user-page")
	public String userPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		model.addAttribute("cartcount",CartItem.cart.size());
		return "home";
	}
	
	@GetMapping("admin-page")
	public String adminPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "dashboard";
	}
}
