package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;

/**
 * 注文処理を実行するリポジトリ
 * @author shun053012
 *return 注文内容
 */
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order order(OrderForm form) {
		Order order = orderRepository.load(form.getId());
		order.setId(form.getId());
		order.setUserId(form.getUserId());
		order.setStatus(form.getStatus());
		order.setTotalPrice(form.getTotalPrice());
		order.setDestinationName(form.getDestinationName());
		order.setDestinationEmail(form.getDestinationEmail());
		order.setDestinationAddress(form.getDestinationAddress());
		order.setDestinationZipcode(form.getDestinationZipcode());
		order.setDestinationTel(form.getDestinationTel());
		order.setDeliveryTime(form.getDeliveryTime());
		order.setPaymentMethod(form.getPaymentMethod());
		order.setOrderItemList(form.getOrderItemList());
		orderRepository.update(order);
		return order;

	}

}
