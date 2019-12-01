package com.example.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

//import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.form.OrderItemForm;
import com.example.service.ShoppingCartService;

/**
 * ショッピングカートの操作を行うコントローラー.
 * 
 * @author hiraokayuri
 *
 */
@Controller
@RequestMapping("")
public class ShoppingCartController {
	@Autowired
	private HttpSession session;
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
	
    @RequestMapping("/thanks")
    public String index() {
    	return "order_finished";
    }
	/**
	 * カートを追加するのボタンを押したら注文情報が追加されるメソッド.
	 * 
	 * @param orderItemform
	 * @param orderform
	 * @return
	 */
	@RequestMapping("/insertOrderItem")
	public String insertOrderItem(OrderItemForm orderItemform) {
		System.out.println("フォームの内容" + orderItemform);
		// 修正必要
		Integer userId = (Integer)(session.getAttribute("sessionId"));
		shoppingCartService.addItem(userId, orderItemform);

		return "redirect:/showCartList";
	}

	/**
	 * ショッピングカートの中身を表示するメソッド.
	 * @param orderItemform
	 * @param model
	 * @return　中身が空ならメッセージを返し、中身があれば注文内容を渡す.
	 * 
	 */
	@RequestMapping("/showCartList")
	public String showCartList(OrderItemForm orderItemform, Model model) {
//		Integer userId = Integer.valueOf(session.getId());	
//		sessionIdを10進数の数字に変換

		int userId = new BigInteger(session.getId(), 16).intValue();
		session.setAttribute("sessionId", userId);

		Order order = shoppingCartService.showCartList(userId);
		if (order == null || order.getOrderItemList().size()==0) {
			model.addAttribute("message", "カートの中身が空です。");

		} else {
			model.addAttribute("order", order);

		}
		return "cart_list";

	}

	/**
	 * カートの中身を削除するメソッド.
	 * 
	 * @param orderItemId
	 * @return
	 */
	@RequestMapping("/deleteOrder")
	public String deleteOrder(Integer orderItemId) {
		shoppingCartService.deleteByOrderItemId(orderItemId);

		return "redirect:/showCartList";

	}

}
