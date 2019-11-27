package com.example.controller;

import java.math.BigInteger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

//import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.OrderForm;
import com.example.form.OrderItemForm;
import com.example.service.ShoppingCartService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

/**
 * ショッピングカートの操作を行うコントローラー.
 * 
 * @author hiraokayuri
 *
 */
@Controller
public class ShoppingCartController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ServletContext application;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@ModelAttribute
	public OrderItemForm setUpdateOrderItemForm() {
		return new OrderItemForm();
	}
	@ModelAttribute
	public OrderForm setUpdateOrderFomr() {
		return new OrderForm();
	}

	@RequestMapping("/showCartList")

	public String showCartList(OrderItemForm form, Model model) {
//		Integer userId = Integer.valueOf(session.getId());	
//		sessionIdを10進数の数字に変換
		int userId = new BigInteger(session.getId(),16).intValue();
		System.out.println(userId);

		 Order order = shoppingCartService.showCartList(userId);
		if(order == null) {
			model.addAttribute("message", "カートの中身が一つも入っていません。");
		} else {
			application.setAttribute("order", order);
		}
		
		//エラーが解消できませんでしたが、わからなかったのでエラーのままpushします（橋本）
		return "/cart_list";
		
	}
	@RequestMapping("/InsertOrderItem")
	public String InsertOrderItem(OrderItemForm orderItemform, OrderForm orderform ) {
		int userId = new BigInteger(session.getId(),16).intValue();
		shoppingCartService.addItem(userId, orderItemform,orderform);
		
		return "/showCartList";
	}
		
		
	@RequestMapping("/deleteOrder")
	public String deleteOrder(Integer orderItemId){
	   shoppingCartService.deleteByOrderItemId(orderItemId);
	   
		return "/showCartList";
			
	}

}

