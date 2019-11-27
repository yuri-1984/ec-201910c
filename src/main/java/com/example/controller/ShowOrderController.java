package com.example.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

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
@RequestMapping("/showorder")
public class ShowOrderController {
	
	@Autowired
	private ShowOrderService showOrderservice;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 注文内容確認画面を表示する.
	 * @param userId
	 * @param status
	 * @param model
	 * @return　注文内容確認画面
	 */



	@RequestMapping("/showorder1")
	public String ShowOrder(Integer userId,Model model) {

		System.out.println(userId);
		if(userId == new BigInteger(session.getId()).intValue()){
        return "login";
		}else {
			System.out.println(2);
			Object order = showOrderservice.showOrder(userId);
			model.addAttribute("order",order);
			return "order_confirm";
		}
	}

}
