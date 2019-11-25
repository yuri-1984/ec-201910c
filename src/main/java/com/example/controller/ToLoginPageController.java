package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログイン画面を表示するコントローラクラス.
 * @author yuichi
 *
 */
@Controller
public class ToLoginPageController {

	/**
	 * ログイン画面を表示するメソッド.
	 * @return ログイン画面
	 */
	@RequestMapping("/toLoginPage")
	public String toLoginPage() {
		return "login.html";
	}
	
}
