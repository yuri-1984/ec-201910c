package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.repository.OrderRepository;
import com.example.service.LoginService;

@Controller
@RequestMapping("")
public class LoginController {

	
	@Autowired
	private LoginService service;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@RequestMapping("/login")
	public String login(String email, String password, Model model) {
		User user = service.login(email, password);
		System.out.println(email);
		System.out.println(password);
		System.out.println(user);
		if (user == null) {
			return "forward:/toLoginPage";
		}
		int sessionId = (Integer)session.getAttribute("sessionId");
		Order order = orderRepository.findByUserIdAndStatus(sessionId, 0);
		orderRepository.updateLogin(user.getId(), order.getId());
		return "forward:/";
	}
}
