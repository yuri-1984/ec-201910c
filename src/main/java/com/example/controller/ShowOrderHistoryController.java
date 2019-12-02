package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
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
	@RequestMapping("/showOrderHistory")
	public String showOrderHistory( Model model ,@AuthenticationPrincipal LoginUser loginUser) {
		List<Order> orderList = orderHistoryService.seachOrderList(loginUser.getUser().getId());
		if (orderList == null) {
			model.addAttribute("message", "注文履歴はありません");
		}else {
			model.addAttribute("orderList", orderList);
		}
		
		System.err.println(orderList );
		System.out.println(orderList);

		return "order_history.html";
	}
}
