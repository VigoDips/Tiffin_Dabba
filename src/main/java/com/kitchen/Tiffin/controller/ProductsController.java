package com.kitchen.Tiffin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kitchen.Tiffin.model.Product;
import com.kitchen.Tiffin.repository.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductRepository pro_repo;
	
	@GetMapping({"","/"})
	public String showproductList(Model model) {
		List<Product> products = pro_repo.findAll(Sort.by(Sort.Direction.DESC,"proId"));
		model.addAttribute("products",products);
		return "productform";
	}
}
