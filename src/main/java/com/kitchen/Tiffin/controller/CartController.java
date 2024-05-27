package com.kitchen.Tiffin.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kitchen.Tiffin.model.CartItem;
import com.kitchen.Tiffin.model.Order;
import com.kitchen.Tiffin.model.Product;
import com.kitchen.Tiffin.model.User;
import com.kitchen.Tiffin.services.OrderService;
import com.kitchen.Tiffin.services.ProductService;
import com.kitchen.Tiffin.services.UserService;

@Controller
public class CartController {

	@Autowired
	ProductService productService; 
	
	 @Autowired
	    UserService userService;
	 
	 @Autowired
	    OrderService orderService;
	
	@GetMapping("/addToCart/{proId}")
	public String addToCart(@PathVariable int proId) {
	    // Retrieve the product information by its ID
	    Product product = productService.getProductById(proId).orElse(null);
	    User user = userService.getCurrentUser();
	    
	    // Add the product to the cart (assuming CartItem.cart is a static List<Product>)
	    if (product != null) {
            // Check if the product is already in the cart
            Optional<Product> existingProduct = CartItem.cart.stream()
                                    .filter(p -> p.getProId() == product.getProId())
                                    .findFirst();

            if (existingProduct.isPresent()) {
                // If the product exists, update its quantity
                existingProduct.get().setQuantity(existingProduct.get().getQuantity() + 1);
            } else {
                // If the product does not exist, add it to the cart
            	  product.setQuantity(1);
                CartItem.cart.add(product);
            }
            
            Order order = new Order();
            order.setDate(new Date());
            order.setName(user.getFirstname() + " " + user.getLastname());
            order.setQuantity(product.getQuantity());
            order.setTotal(product.getPrice() * product.getQuantity());
            order.setProducts(product);
            order.setUser(user);
            orderService.saveOrder(order);
        }
        
	
	    
	    // Redirect the user back to the home page
	    return "redirect:/home";
	}

	
	@GetMapping("/cart")
	public String cartGet(Model model) {
		model.addAttribute("cartcount",CartItem.cart.size());
		model.addAttribute("total", CartItem.cart.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()).sum());
		model.addAttribute("totalQuantity", CartItem.cart.stream().mapToInt(Product::getQuantity).sum());
		model.addAttribute("cart",CartItem.cart);
		return "cart";
	}
}
