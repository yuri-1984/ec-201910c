package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.OrderForm;
import com.example.service.OrderService;

@Controller
@RequestMapping("/sample-credit-card-web-api")
public class CreditController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/credit-card")
	public String creditPayment(
			@Validated OrderForm form,
			BindingResult result,
			User user,
			Model model) {
		Order order = orderService.registerOrder(form);
		model.addAttribute("order",order);
		    return "order_finished";
		
	}
	
	public String test() {
		return null;
	}

}
