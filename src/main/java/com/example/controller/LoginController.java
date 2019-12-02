package com.example.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.User;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.service.LoginService;
import com.example.service.ShoppingCartService;

@Controller
@RequestMapping("")
public class LoginController {

	@Autowired
	private LoginService service;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;

	@RequestMapping("/loginAfterSuccess")
	public String login(@AuthenticationPrincipal LoginUser loginUser, Model model) throws ServletException, IOException {
		Integer loginId = loginUser.getUser().getId();
		String email = loginUser.getUser().getEmail();
		String password = loginUser.getUser().getPassword();
		User user = service.login(email, password);
		System.out.println(loginId + "ログインid");
		
//		Integer userId ;
//		if(loginUser != null) {
//			 userId = loginUser.getUser().getId();
//			 shoppingCartService.addItem(userId, orderItemform);
//		}

		Integer sessionId = (Integer) session.getAttribute("userId");
//		Integer sessionId = new BigInteger(session.getId(), 16).intValue();

		System.out.println(sessionId + "sessionId");
		
		Order loginOrder = orderRepository.findByUserIdAndStatus(loginId, 0);
		if (!(sessionId == null)) {
			System.out.println("処理されたのは"+ 1);
			
			if(loginOrder == null) {
				System.out.println(1);
				orderRepository.loginUpdate(loginId, sessionId);
				System.out.println(2);
				System.out.println(2);
				
			}else {
				//order_itemsのorder
				System.out.println(sessionId + "こここAAA");
				Order order = orderRepository.findByUserIdAndStatus(sessionId, 0);
				Order orderNew = orderRepository.findByUserIdAndStatus(loginId, 0);
				System.out.println(order);
				orderItemRepository.updateLogin(orderNew.getId(), order.getId());
				orderItemRepository.deleteById(order.getId());
				System.out.println(loginId);
				System.out.println(sessionId);
				System.out.println("処理されたのは"+ 3);
			}
			
			
		}

		String url = (String)session.getAttribute("referer");
		String urlPass = (url).substring(21);
		System.out.println(url);
		System.out.println(urlPass);
		
		if(urlPass.equals("/showRegisterUserPage")) {
			System.out.println(1);
			return "forward:/";
		}else if(urlPass.equals("/registerUser")) {
			return "forward:/";
		}else if(urlPass.equals("/toLoginPage")) {
			return "forward:/";
		}else if(urlPass.equals("/showCartList")) {
			return "forward:/showOrderConfirm";
		}else {
			System.out.println(2);
			return "forward:" + urlPass;
		}
//		return "forward:" + urlPass;
	}
}
