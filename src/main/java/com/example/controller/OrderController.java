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
import com.example.domain.User;
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
	public String order(@Validated OrderForm form, BindingResult result,User user, Model model) {
        System.out.println("OrderControllerからもらったform"+form);
		if (result.hasErrors()) {

			return "forward:/showorder";
		} 
		if (Integer.parseInt(form.getPaymentMethod())==2) {
			Credit credit = new Credit();
			credit.setUserId(Integer.parseInt(form.getUserId()));
			credit.setOrderNumber(Integer.parseInt(form.getOrderNumber()));
			credit.setOrderAmount(Integer.parseInt(form.getOrderAmount()));
			credit.setCardNumber(Integer.parseInt(form.getCardNumber()));
			credit.setCardExpYear(Integer.parseInt(form.getCardExpYear()));
			credit.setCardExpMonth(Integer.parseInt(form.getCardExpMonth()));
			credit.setCardName(form.getCardName());
			credit.setCardCvv(Integer.parseInt(form.getCardCvv()));

			ReceiveCredit receiveCredit = new ReceiveCredit();

			Order order = orderService.order(form,user);
			System.out.println("orderControllerの中身"+order);
			model.addAttribute("order", order);
			checkService.service(credit, receiveCredit);
			if (checkService.service(credit, receiveCredit) == true) {
				return "redirect:/order_finished";
			} else if (checkService.service(credit, receiveCredit) == false) {
				result.rejectValue("error", null, "クレジットカード情報が不正です");
				return "order_confirm";
			}

		}
		Order order = orderService.order(form,user);
		model.addAttribute("order", order);
		return "redirect:/finished";
	}
	
	@RequestMapping("/finished")
	public String finished() {
		return "order_finished";
	}
}
