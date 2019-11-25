package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * 注文内容を操作するサービス.
 * @author shun053012
 *
 */

@Service
public class ShowOrderService {
	
	/**
	 * 注文内容を検索する.
	 * @author shun053012
	 *
	 */
	@Autowired
	private OrderRepository repository;

	
	public Order showOrder(Integer userId,Integer status) {
		
		Order order = repository.findByUserIdAndStatus(userId, status);
		return order;
		
		
	}

}
