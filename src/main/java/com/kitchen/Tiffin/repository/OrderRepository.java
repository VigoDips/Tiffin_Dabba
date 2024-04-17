package com.kitchen.Tiffin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kitchen.Tiffin.model.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
