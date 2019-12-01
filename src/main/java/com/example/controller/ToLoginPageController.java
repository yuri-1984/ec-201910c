package com.example.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログイン画面を表示するコントローラクラス.
 * @author yuichi
 *
 */
@Controller
public class ToLoginPageController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * ログイン画面を表示するメソッド.
	 * @return ログイン画面
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/toLoginPage")
	public String toLoginPage() throws ServletException, IOException {
		session.setAttribute("referer", request.getHeader("REFERER"));
		return "login";
	}
	
	@RequestMapping("/toLoginPageError")
	public String toLoginPageError(Model model) throws ServletException, IOException {
		model.addAttribute("error", "メールアドレス、またはパスワードが間違っています");
		return "login";
	}
	
	
	
}
