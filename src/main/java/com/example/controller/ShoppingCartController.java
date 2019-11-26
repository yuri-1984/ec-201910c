package com.example.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

//import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
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
	private ServletContext application;
	@Autowired
	private ShoppingCartService shoppingCartService;

	@RequestMapping("/showCartList")
	public String showCartList(OrderItemForm form, Model model) {
		Integer userId = Integer.valueOf(session.getId());
		Order order = shoppingCartService.showCartList(userId);
		if (order == null) {
			model.addAttribute("message", "カートの中身が一つも入っていません。");
		} else {
			application.setAttribute("order", order);
		}

		// order_toppingテーブルへの注文トッピングのINSERT
		// チェックボックスで選択されたトッピングの数だけ繰り返す
		for (int i = 0; i < form.getToppingId().length; i++) {
			OrderTopping orderTopping = new OrderTopping();
			Integer[] toppingId = form.getToppingId();
			orderTopping.setToppingId(toppingId[i]);
			orderTopping.setOrderItemId(form.getItemid());
			shoppingCartService.insertOrderTopping(orderTopping);
		}
		// OrderItemオブジェクトを作る
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(form.getItemid());
		orderItem.setOrderId(form.getOrderId());
		orderItem.setQuantity(form.getQuantity());
		orderItem.setSize(form.getSize());		
		//itemとorderトッピングリストはテーブル結合でとってきてorderItemにset
		//orderItemをリクエストスコープに追加してフォワード？
		//※価格の計算などはここまででは行っていない
		
		return "cart_list";
	}

// @RequestMapping("/deleteOrder")
//	public String deleteOrder(Integer orderItemId){
//		
//		
//		return showOrder();
//			
//	}
//    @RequestMapping("/InsertOrderItem")
//	public String InsertOrderItem(OrderItemForm) {
//    	
//    	
//    	
//    	return showOrder();	
//	}
//}

}
