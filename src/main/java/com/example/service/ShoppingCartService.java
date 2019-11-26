package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderTopping;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

//import com.example.domain.Order;
//import com.example.repository.OrderRepository;

/**
 * レポジトリーを操作するサービスクラス.
 * 操作するレポジトリー：
 * OrderRepository,
 * OrderItemRepository,
 * OrderToppingRepository,
 * @author yuichi
 */
@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	/**
	 * ショッピングカートの中身を表示するメソッド.
	 * @param userId ユーザーID
	 * @return オーダー情報
	 */
	public Order showCartList(Integer userId) {
		Order order = orderRepository.findByUserIdAndStatus(userId, 0);
		return order;
	}
	
	/**
	 * 注文トッピング情報をDBに追加するメソッド.
	 * @param orderTopping 注文トッピング情報
	 */
	public void insertOrderTopping(OrderTopping orderTopping) {
		orderToppingRepository.insert(orderTopping);
	}
}
