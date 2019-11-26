package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

@Service
public class ShoppingCartService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Order showOrder(Integer userId) {
		Order order = orderRepository.findByUserIdAndStatus(userId);
	}

}
