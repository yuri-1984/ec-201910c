package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ShowOrderService;

/**
 * 注文内容を操作するコントローラー.
 * @author shun053012
 *
 */
@Controller
public class ShowOrderController {
	
	@Autowired
	private ShowOrderService showOrderservice;
	
	/**
	 * 注文内容確認画面を表示する.
	 * @param userId
	 * @param status
	 * @param model
	 * @return　注文内容確認画面
	 */
<<<<<<< HEAD
	@RequestMapping("/showConfirmOrder")
	public String ShowConfirmOrder(Integer userId,Integer status,Model model) {
		if(userId == null) {
=======
	@RequestMapping("/showorder1")
	public String ShowOrder(Integer userId,Model model) {
		if(userId==null) {
>>>>>>> feature/login
			return "login";
		}else {
			Object order = showOrderservice.showOrder(userId);
			model.addAttribute("order",order);
			return "order_confirm";
		}
	}

}
