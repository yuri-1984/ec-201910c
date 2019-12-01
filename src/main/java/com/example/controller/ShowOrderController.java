package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.ShowOrderService;

/**
 * 注文内容を操作するコントローラー.
 * 
 * @author shun053012
 *
 */
@Controller
@RequestMapping("")
public class ShowOrderController {

	@Autowired
	private ShowOrderService showOrderService;

	/**
	 * 注文内容確認画面を表示する.
	 * 
	 * @param userId
	 * @param status
	 * @param model
	 * @return 注文内容確認画面
	 */
	@RequestMapping("/showorder")
	public String showOrder(OrderForm form, Model model,@AuthenticationPrincipal LoginUser loginUser) {
		Order order = showOrderService.showOrder(loginUser.getUser().getId());

		model.addAttribute("order", order);

		return "order_confirm";
	}
	@RequestMapping("/deleteOrder2")
	public String deleteOrder2(Integer orderItemId) {
		showOrderService.deleteByOrderItemId(orderItemId);

		return "redirect:/showorder";

	}
}
