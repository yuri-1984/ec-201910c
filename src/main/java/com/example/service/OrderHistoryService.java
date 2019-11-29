package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * 注文履歴情報を操作するサービスクラス.
 * @author hiraokayuri
 */
@Service
@Transactional
public class OrderHistoryService {
	@Autowired
	private OrderRepository orderRepository;
	

	public List<Order> showOrdertList(Integer userId) {
		List<Order> orderList = orderRepository.findByuserId(userId);
		
		return orderList;
	}
	
	
	
	

}
