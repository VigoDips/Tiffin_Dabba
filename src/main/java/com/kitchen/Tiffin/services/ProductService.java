package com.kitchen.Tiffin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchen.Tiffin.model.Product;
import com.kitchen.Tiffin.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository pro_repo;
	public List<Product> getAllProduct(){return pro_repo.findAll();}
	
	
	public Optional<Product> getProductById(int proId){
		return pro_repo.findById(proId);
	}
} 
