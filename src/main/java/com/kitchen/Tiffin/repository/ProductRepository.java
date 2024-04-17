package com.kitchen.Tiffin.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.kitchen.Tiffin.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

	//List<Product> findAll();
	 

	

}
