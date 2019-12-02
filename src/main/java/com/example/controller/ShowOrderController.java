package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.ShoppingCartService;
import com.example.service.ShowOrderService;

/**
 * 注文内容を操作するコントローラー.
 * 
 * @author shun053012
 *
 */
@Controller
public class ShowOrderController {

	@Autowired
	private ShowOrderService showOrderService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * 注文内容確認画面を表示する.
	 * 
	 * @param userId
	 * @param status
	 * @param model
	 * @return 注文内容確認画面
	 */
	@RequestMapping("/showorder")
	public String showOrder(OrderForm form, Model model,@AuthenticationPrincipal LoginUser loginUser) {
		Order order = showOrderService.showOrder(loginUser.getUser().getId());
		if (order == null || order.getOrderItemList().size() == 0) {
			return "redirect:/showCartList";
		}
		form.setDestinationName(loginUser.getUser().getName());
		form.setDestinationEmail(loginUser.getUser().getEmail());
		form.setDestinationZipcode(loginUser.getUser().getZipcode());
		form.setDestinationAddress(loginUser.getUser().getAddress());
		form.setDestinationTel(loginUser.getUser().getTelephone());
		
		
		model.addAttribute("orderForm", form);
		model.addAttribute("order", order);

		return "order_confirm";
	}
	@RequestMapping("/deleteOrder2")
	public String deleteOrder2(Integer orderItemId) {
		shoppingCartService.deleteByOrderItemId(orderItemId);

		return "redirect:/showorder";

	}
}
