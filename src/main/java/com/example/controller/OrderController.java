package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Credit;
import com.example.domain.Order;
import com.example.domain.ReceiveCredit;
import com.example.form.OrderForm;
import com.example.service.CheckService;
import com.example.service.OrderService;

/**
 * 注文を受け付けて注文完了処理を行うコントローラクラス.
 * 
 * @author yuichi
 *
 */
@Controller
@RequestMapping("")
public class OrderController {
	@ModelAttribute
	public OrderForm form() {
		return new OrderForm();
	}

	@Autowired
	private OrderService orderService;

	@Autowired
	private CheckService checkService;

//	/**
//	 * 注文完了ページを表示するメソッド.
//	 * 
//	 * @return 注文完了画面
//	 */
//	@RequestMapping("/showOrderFinished")
//	public String index() {
//		return "order_confirm";
//	}

	/**
	 * 注文処理を行うメソッド.
	 * 
	 * @param form リクエストパラメータ
	 * @return 注文完了画面.
	 */
	@RequestMapping("/order")
	public String order(@Validated OrderForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "forward:/showorder";
		} 
		if (form.getPaymentMethod()==2) {
			Credit credit = new Credit();
			credit.setUserId(form.getUserId());
			credit.setOrderNumber(form.getOrderNumber());
			credit.setOrderAmount(form.getOrderAmount());
			credit.setCardNumber(form.getCardNumber());
			credit.setCardExpYear(form.getCardExpYear());
			credit.setCardExpMonth(form.getCardExpMonth());
			credit.setCardName(form.getCardName());
			credit.setCardCvv(form.getCardCvv());

			ReceiveCredit receiveCredit = new ReceiveCredit();

			Order order = orderService.order(form);
			System.out.println(order);
			model.addAttribute("order", order);
			checkService.service(credit, receiveCredit);
			if (checkService.service(credit, receiveCredit) == true) {
				return "redirect:/order_finished";
			} else if (checkService.service(credit, receiveCredit) == false) {
				result.rejectValue("error", null, "クレジットカード情報が不正です");
				return "order_confirm";
			}

		}
		Order order = orderService.order(form);
		model.addAttribute("order", order);
		return "redirect:order_finished";
	}
}
