package com.example.controller;

//import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ショッピングカートの操作を行うコントローラー.
 * 
 * @author hiraokayuri
 *
 */
@Controller
public class ShoppingCartController {

//	@Autowired
//	private HttpSession session;

	@RequestMapping("/showCartList")
	public String showCartList() {
//		Integer userId = Integer.valueOf(session.getId());
//		ShoppingCartService.showOrder(userId);

		return "cart_list.html";
	}

}
