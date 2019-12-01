package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String showOrder(OrderForm form, Model model) {
		Order order = showOrderService.showOrder(Integer.parseInt(form.getId()));

		model.addAttribute("order", order);

		return "order_confirm";
	}

}
