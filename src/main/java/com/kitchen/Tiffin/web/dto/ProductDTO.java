package com.kitchen.Tiffin.web.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class ProductDTO {

	@NotEmpty(message="The Name Is Required.")
	private String name;
	
	@Min(0)
	private double price;
	
	@NotEmpty(message="The Brand Is Required.")
	private String category;
	
	@NotEmpty(message="Quantity Is Required.")
	private String quantity;
	
	private MultipartFile imageFileName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public MultipartFile getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(MultipartFile imageFileName) {
		this.imageFileName = imageFileName;
	}
}
