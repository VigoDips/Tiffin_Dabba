package com.kitchen.Tiffin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kitchen.Tiffin.model.Product;
import com.kitchen.Tiffin.repository.ProductRepository;
import com.kitchen.Tiffin.web.dto.ProductDTO;

import jakarta.validation.Valid;

@Controller

public class ProductsController {

	@Autowired
	private ProductRepository pro_repo;
	
	@GetMapping("/products")
	public String showproductList(Model model) {
		List<Product> products = pro_repo.findAll(Sort.by(Sort.Direction.DESC,"proId"));
		model.addAttribute("products",products);
		return "productform";
	}
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		ProductDTO productDto = new ProductDTO();
		model.addAttribute("productDto",productDto);
		return "createproduct";
	}
	
	@PostMapping("/create")
	public String createProduct(@Valid @ModelAttribute ProductDTO productDto,
			                                            BindingResult result) {
		
		if(productDto.getImageFileName().isEmpty()) {
			result.addError(new FieldError("productDto","imageFileName","The image file is required."));
		}
		
		if(result.hasErrors()) {
			return "createproduct";
		}
		
		
		MultipartFile image = productDto.getImageFileName();
		String storagefilename = image.getOriginalFilename();
		try {
			String uploadDir = "public/images/";
			Path uploadPath = Paths.get(uploadDir);
			
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			try (InputStream inputStream = image.getInputStream()){
				Files.copy(inputStream, Paths.get(uploadDir + storagefilename),StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			System.out.println("Exception : "+e.getMessage());
		}
		
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setCategory(productDto.getCategory());
		product.setQuantity(productDto.getQuantity());
		product.setImageFileName(storagefilename);
		
		pro_repo.save(product);
		
		return "redirect:/products";
	}
	
	@GetMapping("/edit")
	public String showEditPage(Model model,@RequestParam int proId) {
		
		try {
			Product product = pro_repo.findById(proId).get();
			model.addAttribute("product",product);
			
			ProductDTO productDto = new ProductDTO();
			productDto.setName(product.getName());
			productDto.setPrice(product.getPrice());
			productDto.setCategory(product.getCategory());
			productDto.setQuantity(product.getQuantity());
			
			model.addAttribute("productDto",productDto);
		}catch(Exception ex) {
			System.out.println("Exception : "+ex.getMessage());
			return "redirect:/products";
		}
		return "editform";
	}
	@PostMapping("/edit")
	public String updateProduct(Model model, @RequestParam int proId, @Valid @ModelAttribute ProductDTO productDto, BindingResult result) {
	    try {
	        if (result.hasErrors()) {
	            return "editform";
	        }

	        Product product = pro_repo.findById(proId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + proId));

	        // Update product fields with values from productDto
	        product.setName(productDto.getName());
	        product.setPrice(productDto.getPrice());
	        product.setCategory(productDto.getCategory());
	        product.setQuantity(productDto.getQuantity());

	        // Handle image update
	        MultipartFile image = productDto.getImageFileName();
	        if (image != null && !image.isEmpty()) {
	            String uploadDir = "public/images/";
	            Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());
	            try {
	                Files.delete(oldImagePath);
	            } catch (IOException ex) {
	                System.out.println("Error deleting old image: " + ex.getMessage());
	            }
	            String storageFilename = image.getOriginalFilename();
	            try (InputStream inputStream = image.getInputStream()) {
	                Files.copy(inputStream, Paths.get(uploadDir + storageFilename), StandardCopyOption.REPLACE_EXISTING);
	            } catch (IOException ex) {
	                System.out.println("Error copying new image: " + ex.getMessage());
	            }
	            product.setImageFileName(storageFilename);
	        }

	        // Save the updated product
	        pro_repo.save(product);
	    } catch (Exception e) {
	        System.out.println("Exception: " + e.getMessage());
	    }
	    return "redirect:/products";
	}
       @GetMapping("/delete")
       public String deleteProduct(@RequestParam int proId) {
    	   
    	   try {
    		   Product product = pro_repo.findById(proId).get();
    		   
    		   Path imagepath = Paths.get("public/images" + product.getImageFileName());
    		   
    		   try {
    			   Files.delete(imagepath);
    		   }catch(Exception ex) {
    			   System.out.println("Exception : "+ex.getMessage());
    		   }
    		   pro_repo.delete(product);
    		   
    	   } catch(Exception e) {
    		   System.out.println("Exception : "+ e.getMessage());
    	   }
    	   return "redirect:/products";
       }
	
}
