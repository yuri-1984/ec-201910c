package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ShowOrderService;

/**
 * 注文内容を操作するコントローラー.
 * @author shun053012
 *
 */


@RequestMapping("/showorder")
public class ShowOrderController {
	@Autowired
	private ShowOrderService showOrderservice;
	
	/**
	 * 注文内容確認画面を表示.
	 * @return　注文内容確認画面
	 */
	@RequestMapping("")
	   public String index() {
		return "cart_list";
		
	}
	
	/**
	 * 注文内容確認画面を表示する.
	 * 
	 * @param userId
	 * @param status
	 * @param model
	 * @return　注文内容確認画面
	 */
	@RequestMapping("/showorder1")
	public String ShowOrder(Integer userId,Model model) {
		if(userId==null) {
			return "login";
		}else {
			Object order = showOrderservice.showOrder(userId);
			model.addAttribute("order",order);
			return "order_confirm";
		}
	}

}
