package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.OrderHistoryService;

/**
 * 注文履歴を表示するコントローラクラス.
 * @author hiraokayuri
 *
 */
@Controller
@RequestMapping("")
public class ShowOrderHistoryController {
	@Autowired
	private OrderHistoryService orderHistoryService;

	/**
	 * 注文履歴を表示するメソッド.
	 * 
	 * @param model userId
	 * 
	 * @return 注文履歴ページ
	 */
//	@RequestMapping("/showOrderHistory")
//	public String showOrderHistory(Integer userId, Model model) {
//		List<Order> orderList = orderHistoryService.showOrdertList(userId);
//		if (orderList.isEmpty() ) {
//			model.addAttribute("message", "注文履歴はありません");
//		}else {
//			model.addAttribute("orderList", orderList);
//		}
//
//		return "order_history.html";
//	}
	
}
