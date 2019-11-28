package com.example.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderItemForm;
import com.example.service.ShowOrderService;
import com.example.service.TestDataService;

/**
 * 注文内容を操作するコントローラー.
 * @author shun053012
 *
 */
@Controller
@RequestMapping("")
public class ShowOrderController {
	
	@Autowired
	private ShowOrderService showOrderservice;
	
	@Autowired
	private TestDataService testDataService;
	
	
	/**
	 * 注文内容確認画面を表示する.
	 * @param userId
	 * @param status
	 * @param model
	 * @return　注文内容確認画面
	 */

	//テスト用
	@RequestMapping("/aaaa")
	public String aaa() {
		return "order_confirm";
	}

	@RequestMapping("/showorder")
	public String ShowOrder(@Validated OrderItemForm form, BindingResult result, Integer userId,Model model) {
           if(result.hasErrors()) {
        	   return "order_confirm";
           }
		
			Order order= testDataService.testOrder();
            System.out.println(order);
//			Order order = showOrderservice.showOrder(userId);
			model.addAttribute("order",order);
			return "redirect:/showorder1";
		}
	
	@RequestMapping("/showorder1")
	public String ShowFinish() {
		return "order_finished";
	}
	
	}


