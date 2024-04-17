package com.kitchen.Tiffin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication

@ComponentScan(basePackages= {"com.kitchen.Tiffin.controller", "com.kitchen.Tiffin.services","com.kitchen.Tiffin.config"})
public class TiffinApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiffinApplication.class, args);
	}

}
