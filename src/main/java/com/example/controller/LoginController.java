package com.example.controller;

import java.math.BigInteger;

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
		
		
//		int userId = new BigInteger(session.getId(), 16).intValue();
//		Order order = orderRepository.load(user.getId());
//		Order order1 = orderRepository.load(userId);
		Order order = orderRepository.load(user.getId());
		session.setAttribute("order", order);
		return "forward:/";
	}
}
