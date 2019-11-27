package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.OrderService;

/**
 * 注文を受け付けて注文完了処理を行うコントローラクラス.
 * @author yuichi
 *
 */
@Controller
public class OrderController {
	@ModelAttribute
	public OrderForm form() {
		return new OrderForm();
	}
	
	@Autowired
	private OrderService orderService;

	/**
	 * 注文完了ページを表示するメソッド.
	 * @return 注文完了画面
	 */
	@RequestMapping("/showOrderFinished")
	public String index(){
		return "order_confirm";
	}
	
	/**
	 * 注文処理を行うメソッド.
	 * @param form リクエストパラメータ
	 * @return 注文完了画面.
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form,BindingResult result,Model model) {
		if(result.hasErrors()) {
			
			return index();
			
		}
		
		Order order=orderService.order(form);
		model.addAttribute("order",order);
	
		return "forward:/showOrderFinished";
	}
}
