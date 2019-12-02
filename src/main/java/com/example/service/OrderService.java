package com.example.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;

/**
 * 注文処理を実行するリポジトリ
 * 
 * @author shun053012
 */
@Service
@Transactional
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * オーダー情報を受け取りDBに追加を行うメソッド.
	 * @param form
	 * @param user
	 * @return
	 */
	public Order registerOrder(OrderForm form) {
		System.out.println("OrderServiceのForm" + form);
		Order order = orderRepository.load(Integer.parseInt(form.getId()));
		System.err.println(order);
		order.setId(Integer.parseInt(form.getId()));
//		order.setUserId(Integer.parseInt(form.getUserId()));
		order.setTotalPrice(Integer.parseInt(form.getTotalPrice()));
		order.setDestinationName(form.getDestinationName());
		order.setDestinationEmail(form.getDestinationEmail());
		order.setDestinationAddress(form.getDestinationAddress());
		order.setDestinationZipcode(form.getDestinationZipcode());
		order.setDestinationTel(form.getDestinationTel());

		LocalDate localDate = LocalDate.now();
		order.setOrderDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		String deliveryDateTime = form.getDeliveryDate() + " " + form.getDeliveryTime() + ":00:00";
		System.out.println(deliveryDateTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parsedDate = new Date();
		try {
			parsedDate = format.parse(deliveryDateTime);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		order.setDeliveryTime(timestamp);

		order.setDeliveryTime(timestamp);
		order.setPaymentMethod(Integer.parseInt(form.getPaymentMethod()));
//		order.setUser(form.getUser());
		if (Integer.parseInt(form.getPaymentMethod()) == 1) {
			order.setStatus(1);
		} else if (Integer.parseInt(form.getPaymentMethod()) == 2) {
			order.setStatus(2);
		}
//		order.setOrderItemList(form.getOrderItemList());
		orderRepository.update(order);
		return order;
	}
}
