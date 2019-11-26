package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.OrderForm;

/**
 * 注文を受け付けて注文完了処理を行うコントローラクラス.
 * @author yuichi
 *
 */
@Controller
public class OrderController {

	/**
	 * 注文完了ページを表示するメソッド.
	 * @return 注文完了画面
	 */
	@RequestMapping("/showOrderFinished")
	public String showOrderFinished() {
		return "order_finished.html";
	}
	
	/**
	 * 注文処理を行うメソッド.
	 * @param form リクエストパラメータ
	 * @return 注文完了画面.
	 */
	@RequestMapping("/order")
	public String order(OrderForm form) {
	
		//ここにDB処理などを書く//
		
		return "forward:/showOrderFinished";
	}
}
