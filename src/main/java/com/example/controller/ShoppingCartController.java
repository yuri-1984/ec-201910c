package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/showOrder")
	public String showOrder() {
		Integer userId = Integer.valueOf(session.getId());
		ShoppingCartService.showOrder(userId);

		return "Cart_list";
	}

	@RequestMapping("/deleteOrder")
	public String deleteOrder(Integer orderItemId){
		
		
		return showOrder();
			
	}
    @RequestMapping("/InsertOrderItem")
	public String InsertOrderItem(OrderItemForm) {
    	
    	
    	
    	return showOrder();
		
		
		
	}
	
	

}
