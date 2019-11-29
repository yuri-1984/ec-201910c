package com.example.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.ShowOrderService;
import com.example.service.TestDataService;

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

	@Autowired
	private TestDataService testDataService;
	
	@Autowired
	private HttpSession session;

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
		System.err.println("cart_listからもらったユーザーID" + form.getUserId());
//		int userId = new BigInteger(session.getId(), 16).intValue();
		
//        if(result.hasErrors()) {
//        	return "forward:/showorder";
//        }
		Order order = showOrderService.showOrder(form.getUserId());
		System.err.println(order);

//			Order order= testDataService.testOrder();
		model.addAttribute("order", order);
		
		return "order_confirm";
	}


}
