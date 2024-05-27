package com.kitchen.Tiffin.controller;






import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kitchen.Tiffin.model.CartItem;





@Controller
public class HomeController {
	
	
	
    @GetMapping("/home")
	public String showhome(Model model) {
    	model.addAttribute("cartcount",CartItem.cart.size());
		return "home";
	}

  
	
	
}
