package com.example.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

//import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
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

	@RequestMapping("/showCartList")
	public String showCartList(Model model) {
	Integer userId = new BigInteger(session.getId()).intValue();
		 Order order = shoppingCartService.showCartList(userId);
		if(order == null) {
			model.addAttribute("message", "カートの中身が一つも入っていません。");
		}else{
			model.addAttribute("order",order);
		}
		return "cart_list";
	}

  //@RequestMapping("/deleteOrder")
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
//		
//		
//		
//	}
//	
//	
//
//}

}
