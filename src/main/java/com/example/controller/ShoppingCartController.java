package com.example.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

//import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
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
public class ShoppingCartController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * エラーチェック用.
	 * 
	 * @return 空のOrderItemFormオブジェクト
	 */
	@ModelAttribute
	public OrderItemForm setUpdateOrderItemForm() {
		return new OrderItemForm();
	}

	/**
	 * エラーチェック用.
	 * 
	 * @return 空のOrderFormオブジェクト
	 */
	@ModelAttribute
	public OrderForm setUpdateOrderForm() {
		return new OrderForm();
	}

	/**
	 * カートを追加するのボタンを押したら注文情報が追加されるメソッド.
	 * 
	 * @param orderItemform
	 * @param orderform
	 * @return
	 */
	@RequestMapping("/insertOrderItem")
	public String insertOrderItem(OrderItemForm orderItemform, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId;
		if (loginUser != null) {
			userId = loginUser.getUser().getId();
		} else if (session.getAttribute("userId") != null) {
			userId = (Integer) (session.getAttribute("userId"));
		} else {
			userId = new BigInteger(session.getId(), 16).intValue();
			session.setAttribute("userId", userId);
		}
		shoppingCartService.addItem(userId, orderItemform);
		return "redirect:/showCartList";
	}

	/**
	 * ショッピングカートの中身を表示するメソッド.
	 * 
	 * @param orderItemform
	 * @param model
	 * @return 中身が空ならメッセージを返し、中身があれば注文内容を渡す.
	 * 
	 */
	@RequestMapping("/showCartList")
	public String showCartList(OrderItemForm orderItemform, Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId;
		if (loginUser != null) {
			userId = loginUser.getUser().getId();
		} else if (session.getAttribute("userId") != null) {
			userId = (Integer) (session.getAttribute("userId"));
		} else {
			userId = new BigInteger(session.getId(), 16).intValue();
			session.setAttribute("userId", userId);
		}
		Order order = shoppingCartService.showCartList(userId);
		if (order == null || order.getOrderItemList().size() == 0) {
			model.addAttribute("message", "カートの中身が空です。");
		} else {
			model.addAttribute("order", order);
		}
		return "cart_list";
	}

	/**
	 * カートの中身を削除するメソッド.
	 * 
	 * @param orderItemId 注文した商品ID
	 * @return ショッピングカート画面
	 */
	@RequestMapping("/deleteOrder")
	public String deleteOrder(Integer orderItemId) {
		shoppingCartService.deleteByOrderItemId(orderItemId);
		return "redirect:/showCartList";
	}
}
