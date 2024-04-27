package com.kitchen.Tiffin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kitchen.Tiffin.model.CartItem;
import com.kitchen.Tiffin.model.Product;
import com.kitchen.Tiffin.services.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService; 
	
	@GetMapping("/addToCart/{proId}")
	public String addToCart(@PathVariable int proId) {
		CartItem.cart.add(productService.getProductById(proId).get());
		return "redirect:/home";
	}
	
	@GetMapping("/cart")
	public String cartGet(Model model) {
		model.addAttribute("cartcount",CartItem.cart.size());
		model.addAttribute("total",CartItem.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart",CartItem.cart);
		return "cart";
	}
}
